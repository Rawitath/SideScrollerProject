/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Engine.EngineFrame;
import Main.Scenes.*;
import Scenes.SceneManager;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Main {
    public static void main(String[] args) {
        SceneManager.addScene(new MainScene());
        SceneManager.addScene(new SecondScene());
        EngineFrame window = new EngineFrame("2D Side Scrollbruh", 1280, 720);
        SceneManager.setRenderingPanel(window.getRenderingPanel());
        SceneManager.setInputManager(window.getInputManager());
        window.setIconImage(FileReader.readImage("res/icon/aronadaingai.jpg"));
        window.start();
    }
}
