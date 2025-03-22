/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine;

import Engine.Window.ControllableWindow;
import Engine.Window.WindowControlable;
import Engine.Window.WindowEventManager;
import Inputs.InputManager;
import Scenes.SceneManager;
import java.awt.Color;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author GA_IA
 */
public class EngineFrame extends ControllableWindow implements WindowControlable{
    private MainEngine engine;
    private RenderingPanel canvas;
    private InputManager inputManager;
    
    public EngineFrame(String title, int width, int height, Color bg){
        super(0);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(width, height);
        setTitle(title);
        setResizable(false);   
        
        engine = new MainEngine(1, 60);
        
        canvas = new RenderingPanel(this, bg);
        add(canvas);
        
        inputManager = new InputManager();
        canvas.addKeyListener(inputManager);
        canvas.addMouseListener(inputManager);
        canvas.addMouseMotionListener(inputManager);
        canvas.addMouseWheelListener(inputManager);
        canvas.setFocusable(true);
        
        setVisible(true);
        
        WindowEventManager.getInstance().addControlable(this);
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

    @Override
    public void onWindowOpened(WindowEvent e) {

    }

    @Override
    public void onWindowClosing(WindowEvent e) {
        if(e.getSource().equals(this)){
            WindowEventManager.getInstance().removeControlable(this);
            System.exit(0);
        }
    }

    @Override
    public void onWindowClosed(WindowEvent e) {
        
    }

    @Override
    public void onWindowIconified(WindowEvent e) {

    }

    @Override
    public void onWindowDeiconified(WindowEvent e) {

    }

    @Override
    public void onWindowActivated(WindowEvent e) {

    }

    @Override
    public void onWindowDeactivated(WindowEvent e) {

    }
    
    
}
