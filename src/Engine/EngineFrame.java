/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author GA_IA
 */
public class EngineFrame extends JFrame{
    
    private Engine engine;
    
    public EngineFrame(String title, int width, int height, Color bg){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setBackground(bg);
        setTitle(title);
        setResizable(false);        
        setVisible(true);   
    }
    public EngineFrame(String title, int width, int height){
        this(title, width, height, Color.RED);
    }
    public EngineFrame(String title){
        this(title, 640, 480);
    }
    public EngineFrame(int width, int height){
        this("System Frame", width, height);
    }
    public EngineFrame(){
        this(640, 480);
    }
    
    public void start(){
        engine = new Engine();
        engine.start();

    }
    
}
