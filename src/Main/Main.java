/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Engine.EngineFrame;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Main {
    public static void main(String[] args) {
        EngineFrame window = new EngineFrame("2D Side Scrollbruh");
        window.setIconImage(FileReader.readImage("res/icon/aronadaingai.jpg"));
        window.start();
    }
}
