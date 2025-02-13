/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine;

import Inputs.InputManager;
import Scenes.SceneManager;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author GA_IA
 */
public class EngineFrame extends JFrame{
    
    private MainEngine engine;
    private RenderingPanel canvas;
    private InputManager inputManager;
    
    public EngineFrame(String title, int width, int height, Color bg){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setTitle(title);
        setResizable(false);   
        
        engine = new MainEngine();
        
        canvas = new RenderingPanel(this, bg);
        add(canvas);
        
        inputManager = new InputManager();
        canvas.addKeyListener(inputManager);
        canvas.setFocusable(true);
        
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

    public MainEngine getEngine() {
        return engine;
    }
    
    public RenderingPanel getRenderingPanel(){
        return canvas;
    }

    public InputManager getInputManager() {
        return inputManager;
    }
    
    public void start(){
        engine.start();
        engine.addLoopable(canvas);
        SceneManager.loadScene(0);
    }
    
    
}
