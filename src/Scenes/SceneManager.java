/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scenes;

import Datas.Vector2;
import Engine.MainEngine;
import Engine.RenderingPanel;
import Inputs.InputManager;
import Inputs.KeyControlable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GA_IA
 */
public class SceneManager {
    private static int sceneCount = 0;
    private static List<Scene> scenes = new ArrayList<>();
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
        }
        currentScene = getScene(sceneID);
        currentScene.load();
        if(currentScene.getCamera() == null){
            System.err.println(currentScene.getName() + " has no Camera");
            return;
        }
        currentScene.getCamera().setScreenSize(renderingPanel.getPreferredSize());
        renderingPanel.setCurrentCamera(currentScene.getCamera());
        for(var e : currentScene.getEntities()){
            renderingPanel.addEntities(e);
            if(e instanceof KeyControlable){
                inputManager.addKeyControlable((KeyControlable) e);
            }
        }
    }
}
