
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scenes;

import Debugger.DebugManager;
import Debugger.Debuggable;
import Engine.RenderingPanel;
import Entities.Entity;
import Inputs.InputManager;
import Inputs.KeyControlable;
import Inputs.MouseControlable;
import Physics.Collidable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author GA_IA
 */
public class SceneManager {
    private static int sceneCount = 0;
    private static List<Scene> scenes = new CopyOnWriteArrayList<>();
    private static Scene currentScene = null;
    private static RenderingPanel renderingPanel;
    private static InputManager inputManager;

    public static RenderingPanel getRenderingPanel() {
        return renderingPanel;
    }

    public static void setRenderingPanel(RenderingPanel renderingPanel) {
        SceneManager.renderingPanel = renderingPanel;
    }

    public static InputManager getInputManager() {
        return inputManager;
    }

    public static void setInputManager(InputManager inputManager) {
        SceneManager.inputManager = inputManager;
    }

    
    public static Scene getCurrentScene() {
        return currentScene;
    }
    
    public static int getSceneCount() {
        return sceneCount;
    }

    public static void addScene(Scene s){
        s.assignID(sceneCount);
        scenes.add(s);
        sceneCount++;
    }
    
    public static <T extends Scene> T getScene(String name){
        for(var scene : scenes){
            if(scene.getName().equals(name)){
                return (T) scene;
            }
        }
        throw new RuntimeException("Scene \""+ name +"\" does not exist.");
    }
    
    public static <T extends Scene> T getScene(int id){
        for(var scene : scenes){
            if(scene.getId() == id){
                return (T) scene;
            }
        }
        System.err.println("Scene with id: \""+ id +"\" does not exist.");
        return null;
    }
    
    public static void loadScene(int sceneID){
        renderingPanel.setRunning(false);
        if(currentScene != null){
            currentScene.unload();
            renderingPanel.clearCollidable();
            renderingPanel.clearEntities();
            inputManager.clearKeyControlable();
        }
        currentScene = getScene(sceneID);
        if(currentScene.getCamera() == null){
            System.err.println(currentScene.getName() + " has no Camera");
            return;
        }
        currentScene.getCamera().setScreenSize(renderingPanel.getSize());
        renderingPanel.setCurrentCamera(currentScene.getCamera());
        currentScene.getUIView().setScreenSize(renderingPanel.getSize());
        renderingPanel.setCurrentUIView(currentScene.getUIView());
        addToRender(currentScene.getCamera());
        addToRender(currentScene.getUIView());
        currentScene.load();
        renderingPanel.setRunning(true);
    }
    public static void addToRender(Entity e){
        assignDebugManager(e);
        addEntity(e);
            assignInputManager(e);
            assignCollidable(e);
    }
    public static void removeFromRender(Entity e){
        removeDebugManager(e);
            removeEntity(e);
            removeInputManager(e);
            removeCollidable(e);
    }
    private static void addEntity(Entity e){
        renderingPanel.addEntities(e);
        for(var child : e.getChilds()){
            addEntity(child);
        }
    }
    private static void removeEntity(Entity e){
        renderingPanel.removeEntities(e);
        for(var child : e.getChilds()){
            removeEntity(child);
        }
    }
    private static void assignDebugManager(Entity e){
        if(e instanceof Debuggable){
            DebugManager.addDebugObject((Debuggable) e);
        }
        for(var child : e.getChilds()){
            assignDebugManager(child);
        }
    }
    private static void removeDebugManager(Entity e){
        if(e instanceof Debuggable){
            DebugManager.removeDebugObject((Debuggable) e);
        }
        for(var child : e.getChilds()){
            removeDebugManager(child);
        }
    }
    private static void assignInputManager(Entity e){
        if(e instanceof KeyControlable){
            inputManager.addKeyControlable((KeyControlable) e);
        }
        if(e instanceof MouseControlable){
            inputManager.addMouseControlable((MouseControlable) e);
        }
        for(var child : e.getChilds()){
            assignInputManager(child);
        }
    }
    private static void removeInputManager(Entity e){
        if(e instanceof KeyControlable){
            inputManager.removeKeyControlable((KeyControlable) e);
        }
        if(e instanceof MouseControlable){
            inputManager.removeMouseControlable((MouseControlable) e);
        }
        for(var child : e.getChilds()){
            removeInputManager(child);
        }
    }
    private static void assignCollidable(Entity e){
        if(e instanceof Collidable){
                renderingPanel.addCollidable((Collidable) e);
                
            }
        for(var child : e.getChilds()){
                    assignCollidable(child);
                }
    }
    private static void removeCollidable(Entity e){
        if(e instanceof Collidable){
                renderingPanel.removeCollidable((Collidable) e);
                
            }
        for(var child : e.getChilds()){
                    removeCollidable(child);
                }
    }
}
