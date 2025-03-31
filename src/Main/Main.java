/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Debugger.DebugManager;
import Main.Scenes.Example.ExampleScene;
import Engine.EngineFrame;
import Main.ChapterFive.Scenes.ChapterFiveScene;
import Main.ChapterFour.Scenes.ChapterFourScene;
import Main.ChapterOne.Scenes.ChapterOneScene;
import Main.StartMenu.Scene.StartMenuScene;
import Main.ChapterThree.Scenes.ChapterThreeScene;
import Main.ChapterTwo.Scenes.ChapterTwoScene;
import Maps.EditorScene;
import Maps.MapBuilder;
import Physics.Time;
import Scenes.SceneManager;
import Utilities.FileReader;
import java.awt.Color;

/**
 *
 * @author GA_IA
 */
public class Main {
    public static void main(String[] args) {
        //Enable hardware acceleration 
        System.setProperty("sun.java2d.opengl", "true");
        
        //Uncomment to use Debug Mode
//        DebugManager.useDebug();

        //Uncomment to use MapEditor
//        MapBuilder.setUseEditor(true);
        
        //Scene added here
//        SceneManager.addScene(new EditorScene()); // This Scene will be loaded first
//        SceneManager.addScene(new StartMenuScene()); // This Scene will be loaded first
//        SceneManager.addScene(new ChapterOneScene()); // This Scene will be loaded first
//        SceneManager.addScene(new ChapterTwoScene()); // This Scene will be loaded first
//        SceneManager.addScene(new ChapterThreeScene()); // This Scene will be loaded first
//        SceneManager.addScene(new ChapterFourScene()); // This Scene will be loaded first
        SceneManager.addScene(new ChapterFiveScene()); // This Scene will be loaded first
        SceneManager.addScene(new ExampleScene()); // This Scene will be loaded first
        
        EngineFrame window = new EngineFrame("2D Side Scrollbruh", 1280, 720, Color.BLACK);
        Time.setEngine(window.getEngine());
        SceneManager.setRenderingPanel(window.getRenderingPanel());
        SceneManager.setInputManager(window.getInputManager());
        window.setIconImage(FileReader.readImage("res/icon/aronadaingai.jpg"));
        window.start();
    }
}
