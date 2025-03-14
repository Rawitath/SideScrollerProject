/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Debugger.DebugManager;
import Main.Scenes.Example.ExampleScene;
import Engine.EngineFrame;
import Maps.MapBuilder;
import Physics.Time;
import Scenes.SceneManager;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Main {
    public static void main(String[] args) {
        //Uncomment to use Debug Mode
//        DebugManager.useDebug();
//         MapBuilder.setUseEditor(true);
        
        //Scene added here
        SceneManager.addScene(new ExampleScene()); // This Scene will be loaded first
        
        EngineFrame window = new EngineFrame("2D Side Scrollbruh", 1280, 720);
        Time.setEngine(window.getEngine());
        SceneManager.setRenderingPanel(window.getRenderingPanel());
        SceneManager.setInputManager(window.getInputManager());
        window.setIconImage(FileReader.readImage("res/icon/aronadaingai.jpg"));
        window.start();
    }
}
