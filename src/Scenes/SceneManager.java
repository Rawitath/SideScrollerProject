/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scenes;

import Datas.Vector2;
import Engine.MainEngine;
import Engine.RenderingPanel;
import Entities.Entity;
import Inputs.InputManager;
import Inputs.KeyControlable;
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
        currentScene.load();
    }
    public static void addToRender(Entity e){
        renderingPanel.addEntities(e);
            assignInputManager(e);
            assignCollidable(e);
            for(var child : e.getChilds()){
                assignInputManager(child);
                assignCollidable(child);
            }
    }
    public static void removeFromRender(Entity e){
            renderingPanel.removeEntities(e);
            removeInputManager(e);
            removeCollidable(e);
            for(var child : e.getChilds()){
                removeInputManager(child);
                removeCollidable(child);
            }
    }
    private static void assignInputManager(Entity e){
        if(e instanceof KeyControlable){
                inputManager.addKeyControlable((KeyControlable) e);
            }
    }
    private static void removeInputManager(Entity e){
        if(e instanceof KeyControlable){
                inputManager.removeKeyControlable((KeyControlable) e);
            }
    }
    private static void assignCollidable(Entity e){
        if(e instanceof Collidable){
                renderingPanel.addCollidable((Collidable) e);
            }
    }
    private static void removeCollidable(Entity e){
        if(e instanceof Collidable){
                renderingPanel.removeCollidable((Collidable) e);
            }
    }
}
