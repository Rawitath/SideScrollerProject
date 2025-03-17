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
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author GA_IA
 */
public class EditorController implements WindowListener{
    
    private EditorWindow editor;
    private SelectorBox selector;
    
    private Scene currentScene;
   
    private List<TileEntity> placedTiles;
    
    public EditorController(Scene s){
        
        currentScene = s;
        
        placedTiles = new CopyOnWriteArrayList<>();
        
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
        for(TileEntity tile : placedTiles){
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
    public void placeTile(Vector2 mousePos){
        if(editor.getSelectedTile() != -1){
            TileEntity tile = new TileEntity(currentScene);
            TileFile tileFile = new TileFile();
            tile.setSprite(editor.getTiles()[editor.getSelectedTile()]);
            currentScene.addEntity(tile);
            tile.setPosition(new Vector2(
                    getMap().columnToWorldX(getMap().worldXToColumn(mousePos.getX())),
                    getMap().rowToWorldY(getMap().worldYToRow(mousePos.getY()))
            ));
            
            tileFile.setRow(getMap().worldYToRow(mousePos.getY()));
            tileFile.setColumn(getMap().worldXToColumn(mousePos.getX()));
            
            tile.setScale(Vector2.one().multiply(getMap().getTileRatio()));
            tile.setSpriteSize(new Vector2Int(
                    (int)(tile.getSprite().getWidth() * getMap().getImageSizeMultiplier()),
                    (int)(tile.getSprite().getHeight() * getMap().getImageSizeMultiplier())
            ));
            tile.setTileFile(tileFile);
            placedTiles.add(tile);
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
