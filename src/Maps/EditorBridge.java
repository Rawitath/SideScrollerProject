/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

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
        }
    }
    public TileEntity getTile(){
        if(editor.getSelectedTile() != -1){
            TileEntity tile = new TileEntity(currentScene);
            tile.setSprite(editor.getTiles()[editor.getSelectedTile()]);
            return tile;
        }
        return null;
    }
}
