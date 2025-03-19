/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Datas.Vector2;
import Datas.Vector2Int;
import Engine.Window.WindowControlable;
import Engine.Window.WindowEventManager;
import Scenes.Scene;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;


/**
 *
 * @author GA_IA
 */
public class EditorController{
    
    private EditorWindow editor;
    private SelectorBox selector;
    
    private Scene currentScene;
    
    // Concept of 2D Resizable Array - GA_IA
    private Map<Integer, Map<Integer, TileEntity>> tileGrid;
    private List<BufferedImage> usedImages;
    private Map<BufferedImage, Integer> imageUsage;
    
    private int maxColumn = 0;
    private int maxRow = 0;
    
    private MapFile currentMap = null;
    private boolean isSaved = true;
    
    public EditorController(Scene s){     
        currentScene = s;
        
        tileGrid = new HashMap<>();
        imageUsage = new HashMap<>();
        usedImages = new ArrayList<>();
        
        this.editor = new EditorWindow(this);
        this.selector = new SelectorBox(s, this);
        currentScene.addEntity(selector);
        
        if(currentMap == null){
            selector.setActive(false);
        }
        else{
            selector.setActive(true);
            selector.setScale(Vector2.one().multiply(getMap().getTileRatio()));
        }
    }
    public void updateScreen(){
        if(currentMap == null){
            selector.setActive(false);
        }
        else{
            selector.setActive(true);
            selector.setScale(Vector2.one().multiply(getMap().getTileRatio()));
        }
        for(Integer ck : tileGrid.keySet()){
            for(Integer rk : tileGrid.get(ck).keySet()){
                TileEntity tile = tileGrid.get(ck).get(rk);
                tile.setPosition(new Vector2(
                    getMap().columnToWorldX(ck),
                    getMap().rowToWorldY(rk)
                ));
                tile.setScale(Vector2.one().multiply(getMap().getTileRatio()));
                tile.setSpriteSize(new Vector2Int(
                        (int)(tile.getSprite().getWidth() * getMap().getImageSizeMultiplier()),
                        (int)(tile.getSprite().getHeight() * getMap().getImageSizeMultiplier())
                ));
            }
        }
    }
    
    public boolean placeTile(Vector2 mousePos, boolean overrideTile){
        boolean isReplaced = false;
        if(currentMap != null){
            if(editor.getSelectedTile() != -1){
                int tileID = -1;
                for(int i = 0; i < usedImages.size() + 1; i++){
                    if(i < imageUsage.size()){
                        if(!usedImages.get(i).equals(editor.getTiles()[editor.getSelectedTile()])){
                            continue;
                        }
                        else{
                            imageUsage.put(usedImages.get(i), imageUsage.get(usedImages.get(i)) + 1);
                            tileID = i;
                            break;
                        }
                    }
                    //Not in list
                    usedImages.add(editor.getTiles()[editor.getSelectedTile()]);
                    imageUsage.put(usedImages.get(i), 1);
                    tileID = imageUsage.size() - 1;
                    break;
                }
                int column = getMap().worldXToColumn(mousePos.getX());
                int row = getMap().worldYToRow(mousePos.getY());
                
                if(column > maxColumn){
                    maxColumn = column;
                }
                if(row > maxRow){
                    maxRow = row;
                }

                //Check if already placed
                if(tileGrid.containsKey(column) && tileGrid.get(column).containsKey(row)){
                    if(tileGrid.get(column).get(row).getTileFile().getTile() == tileID){
                        return isReplaced;
                    }
                    if(overrideTile){
                        removeTile(mousePos);
                        isReplaced = true;
                    }
                    else{
                        return isReplaced;
                    }
                }   

                //Placing Tile
                TileFile tileFile = new TileFile();
                tileFile.setTileSheet(tileID);

                TileEntity tile = new TileEntity(currentScene);
                tile.setSprite(editor.getTiles()[editor.getSelectedTile()]);
                currentScene.addEntity(tile);

                tile.setPosition(new Vector2(getMap().columnToWorldX(column), getMap().rowToWorldY(row)));

                tile.setScale(Vector2.one().multiply(getMap().getTileRatio()));
                tile.setSpriteSize(new Vector2Int(
                        (int)(tile.getSprite().getWidth() * getMap().getImageSizeMultiplier()),
                        (int)(tile.getSprite().getHeight() * getMap().getImageSizeMultiplier())
                ));
                tile.setTileFile(tileFile);

                if(tileGrid.containsKey(column)){
                    tileGrid.get(column).put(row, tile);
                    return isReplaced;
                }
                tileGrid.put(column, new HashMap<>());
                tileGrid.get(column).put(row, tile);
            }
            isSaved = false;
            editor.notifySave();
        }
        return isReplaced;
    }
    public void removeTile(Vector2 mousePos){ 
        if(currentMap != null){
            if(editor.getTiles() != null && editor.getTiles().length > 0){
                int column = getMap().worldXToColumn(mousePos.getX());
                int row = getMap().worldYToRow(mousePos.getY());

                //Check if empty
                if(!tileGrid.containsKey(column)){
                    return;
                }
                if(!tileGrid.get(column).containsKey(row)){
                    return;
                }

                TileEntity tile = tileGrid.get(column).get(row);
                BufferedImage image = usedImages.get(tile.getTileFile().getTile());

                currentScene.removeEntity(tile);

                imageUsage.put(image
                        , imageUsage.get(image) - 1);
                if(imageUsage.get(image) <= 0){
                    imageUsage.remove(image);
                    usedImages.remove(image);
                }

                tileGrid.get(column).remove(row);
                if(tileGrid.get(column).keySet().size() <= 0){
                    tileGrid.remove(column);
                }
            }
        isSaved = false;
        editor.notifySave();
        }
    }
    
    public void readMap(){
        if(currentMap != null){
            for(int i = 0; i < currentMap.getTiles().length; i++){
                for(int j = 0; j < currentMap.getTiles()[i].length; i++){
                    if(currentMap.getTiles()[i][j] != null){
                        TileEntity tile = new TileEntity(currentScene);
                        tile.setTileFile(currentMap.getTiles()[i][j]);
                        tile.setSprite(currentMap.getUsedImages().get(tile.getTileFile().getTile()));
                        currentScene.addEntity(tile);
                        if(tileGrid.containsKey(i)){
                        tileGrid.get(i).put(j, tile);
                        continue;
                        }
                        tileGrid.put(i, new HashMap<>());
                        tileGrid.get(i).put(j, tile);
                    }
                }
            }
            updateScreen();
        }
    }
    public void writeMap(){
        if(currentMap != null){
            currentMap.setTiles(new TileFile[maxColumn][maxRow]);
            for(Integer ck : tileGrid.keySet()){
                for(Integer rk : tileGrid.get(ck).keySet()){
                    TileEntity tile = tileGrid.get(ck).get(rk);
                    currentMap.getTiles()[ck][rk] = tile.getTileFile();
                }
            }
            currentMap.setUsedImages(usedImages);
        }
    }
    
    public void setSelectorPosition(Vector2 mousePos){
        if(getMap() != null){
            selector.setScale(Vector2.one().multiply(getMap().getTileRatio()));
            selector.setPosition(new Vector2(
                        getMap().columnToWorldX(getMap().worldXToColumn(mousePos.getX())),
                        getMap().rowToWorldY(getMap().worldYToRow(mousePos.getY()))
                ));
        }
    }
    public void moveSelectorOnTop(){
        //Inefficient jud jud
        currentScene.removeEntity(this.selector);
        currentScene.addEntity(selector);
    }
    
    public MapFile getMap(){
        return currentMap;
    }

    public void setMap(MapFile currentMap) {
        this.currentMap = currentMap;
    }
    
    public boolean isSaved() {
        return isSaved;
    }

    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }
}
