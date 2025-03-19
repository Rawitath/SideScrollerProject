/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Datas.Vector2;
import Datas.Vector2Int;
import Scenes.Scene;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author GA_IA
 */
public class EditorController{
    
    private EditorWindow editor;
    private SelectorBox selector;
    
    private Scene currentScene;
    
    // Concept of 2D Resizable Array - GA_IA
    private Map<Integer, Map<Integer, TileDisplayEntity>> tileGrid;
    private List<BufferedImage> usedImages;
    private Map<BufferedImage, Integer> imageUsage;
    
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
                TileDisplayEntity tile = tileGrid.get(ck).get(rk);
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

                TileDisplayEntity tile = new TileDisplayEntity(currentScene);
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

                TileDisplayEntity tile = tileGrid.get(column).get(row);
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
            if(currentMap.getTiles() != null){
            for(int i = 0; i < currentMap.getTiles().length; i++){
                for(int j = 0; j < currentMap.getTiles()[i].length; j++){
                    if(currentMap.getTiles()[i][j] != null){
                        TileDisplayEntity tile = new TileDisplayEntity(currentScene);
                        tile.setTileFile(currentMap.getTiles()[i][j]);
                        BufferedImage tileImage = currentMap.getUsedImages().get(tile.getTileFile().getTile());
                        tile.setSprite(tileImage);
                        for(int k = 0; k < usedImages.size() + 1; k++){
                            if(i < imageUsage.size()){
                                if(!usedImages.get(i).equals(tileImage)){
                                    continue;
                                }
                                else{
                                    imageUsage.put(usedImages.get(i), imageUsage.get(usedImages.get(i)) + 1);
                                    break;
                                }
                            }
                            //Not in list
                            usedImages.add(tileImage);
                            imageUsage.put(usedImages.get(i), 1);
                            break;
                        }
                        currentScene.addEntity(tile);
                        if(tileGrid.containsKey(i - currentMap.getColumnOffset())){
                            tileGrid.get(i - currentMap.getColumnOffset()).put(j - currentMap.getRowOffset(), tile);
                            continue;
                        }
                        tileGrid.put(i - currentMap.getColumnOffset(), new HashMap<>());
                        tileGrid.get(i - currentMap.getColumnOffset()).put(j - currentMap.getRowOffset(), tile);
                    }
                }
            }
            }
            updateScreen();
        }
    }
    public void writeMap(){
        if(currentMap != null){
            List<Integer> columnList = new ArrayList<>();
            columnList.addAll(tileGrid.keySet());
            Collections.sort(columnList);
            TileFile[][] tileFiles = new TileFile[(columnList.getLast() - columnList.getFirst()) + 1][];
            int minRow = 0;
            int maxRow = 0;
            for(int i = 0; i < columnList.size(); i++){
                List<Integer> rowList = new ArrayList<>();
                rowList.addAll(tileGrid.get(columnList.get(i)).keySet());
                Collections.sort(rowList);
                
                //Check for size to allocate array
                minRow = rowList.getFirst() < minRow ? rowList.getFirst() : minRow;
                maxRow = rowList.getLast() > maxRow ? rowList.getLast() : maxRow;
            }
            for(int i = 0; i < tileFiles.length; i++){
                tileFiles[i] = new TileFile[(maxRow - minRow) + 1];
                for(Integer row : tileGrid.get(i + columnList.getFirst()).keySet()){
                        tileFiles[i][row - minRow] = tileGrid.get(i + columnList.getFirst()).get(row).getTileFile();
                }
            }
            currentMap.setColumnOffset(columnList.getFirst());
            currentMap.setRowOffset(minRow);
            currentMap.setTiles(tileFiles);
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
