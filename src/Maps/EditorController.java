/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Datas.Vector2;
import Datas.Vector2Int;
import Scenes.Scene;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * @author GA_IA
 */
public class EditorController implements WindowListener{
    
    private EditorWindow editor;
    private SelectorBox selector;
    
    private Scene currentScene;
    
    // Concept of 2D Resizable Array - GA_IA
    private Map<Integer, Map<Integer, TileEntity>> tileColumn;
    private List<BufferedImage> usedImages;
    
    public EditorController(Scene s){
        
        currentScene = s;
        tileColumn = Collections.synchronizedMap(new HashMap<>());
        usedImages = new ArrayList<>();
        
        this.editor = new EditorWindow(this);
        this.selector = new SelectorBox(s, this);
        currentScene.addEntity(selector);
        
        if(editor.getCurrentMap() == null){
            selector.setActive(false);
        }
        else{
            selector.setActive(true);
            selector.setScale(Vector2.one().multiply(getMap().getTileRatio()));
        }
    }
    public void updateScreen(){
        if(editor.getCurrentMap() == null){
            selector.setActive(false);
        }
        else{
            selector.setActive(true);
            selector.setScale(Vector2.one().multiply(getMap().getTileRatio()));
        }
        for(Integer ck : tileColumn.keySet()){
            for(Integer rk : tileColumn.get(ck).keySet()){
                TileEntity tile = tileColumn.get(ck).get(rk);
                tile.setPosition(new Vector2(
                    getMap().columnToWorldX(tile.getTileFile().getColumn()),
                    getMap().rowToWorldY(tile.getTileFile().getRow())
                ));
                tile.setScale(Vector2.one().multiply(getMap().getTileRatio()));
                tile.setSpriteSize(new Vector2Int(
                        (int)(tile.getSprite().getWidth() * getMap().getImageSizeMultiplier()),
                        (int)(tile.getSprite().getHeight() * getMap().getImageSizeMultiplier())
                ));
            }
        }
    }
    public void placeTile(Vector2 mousePos){
        if(editor.getSelectedTile() != -1){
            int tileID = -1;
            for(int i = 0; i < usedImages.size() + 1; i++){
                if(i < usedImages.size()){
                    if(!usedImages.get(i).equals(editor.getTiles()[editor.getSelectedTile()])){
                        continue;
                    }
                    else{
                        tileID = i;
                        break;
                    }
                }
                //Not in list
                usedImages.add(editor.getTiles()[editor.getSelectedTile()]);
                tileID = usedImages.size() - 1;
                break;
            }
            
            int column = getMap().worldXToColumn(mousePos.getX());
            int row = getMap().worldYToRow(mousePos.getY());
            
            //Check if already placed
            if(tileColumn.containsKey(column)){
                if(tileColumn.get(column).containsKey(row)){
                    if(tileColumn.get(column).get(row).getTileFile().getTile() == tileID){
                        return;
                    }
                }
            }
            
            //Placing Tile
            TileFile tileFile = new TileFile();
            tileFile.setTileSheet(tileID);
            tileFile.setColumn(column);
            tileFile.setRow(row);
            
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
            
            if(tileColumn.containsKey(column)){
                tileColumn.get(column).put(row, tile);
                return;
            }
            tileColumn.put(column, new HashMap<>());
            tileColumn.get(column).put(row, tile);
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
    public MapFile getMap(){
        return editor.getCurrentMap();
    }
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if(e.getSource().equals(editor)){
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
