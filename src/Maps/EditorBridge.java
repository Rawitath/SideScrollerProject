/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Datas.Vector2;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class EditorBridge {
    
    private EditorWindow editor;
    private SelectorBox selector;
    
    private Scene currentScene;
    
    public EditorBridge(Scene s){
        
        currentScene = s;
        
        this.editor = new EditorWindow(this);
        this.selector = new SelectorBox(s, this);
        currentScene.addEntity(selector);
        setSelectorState();
    }
    public void setSelectorState(){
        if(editor.getCurrentMap() == null){
            selector.setActive(false);
        }
        else{
            selector.setActive(true);
            selector.setScale(Vector2.one().multiply(getMap().getTileRatio()));
        }
    }
    public void placeTile(Vector2 mousePos){
        if(editor.getSelectedTile() != -1){
            TileEntity tile = new TileEntity(currentScene);
            tile.setSprite(editor.getTiles()[editor.getSelectedTile()]);
            currentScene.addEntity(tile);
            tile.setPosition(new Vector2(
                    getMap().columnToWorldX(getMap().worldXToColumn(mousePos.getX())),
                    getMap().rowToWorldY(getMap().worldYToRow(mousePos.getY()))
            ));
        }
    }
    public void setSelectorPosition(Vector2 mousePos){
        if(getMap() != null){
            selector.setPosition(new Vector2(
                        getMap().columnToWorldX(getMap().worldXToColumn(mousePos.getX())),
                        getMap().rowToWorldY(getMap().worldYToRow(mousePos.getY()))
                ));
        }
    }
    public MapFile getMap(){
        return editor.getCurrentMap();
    }
}
