/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Datas.Vector2;
import Debugger.DebugManager;
import Inputs.KeyControlable;
import Inputs.MouseControlable;
import Scenes.Scene;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author GA_IA
 */
public class Camera extends Entity implements KeyControlable, MouseControlable{
    private Dimension screenSize;
    private float zoom;
    private float debugSpeed = 1;

    public Camera(Scene s) {
        super(s);
        screenSize = new Dimension();
        zoom = 1f;
    }

    public Dimension getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Dimension screenSize) {
        this.screenSize = screenSize;
    }
    
    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }
    
    public Vector2 getPositionOffset(){
        Vector2 output = new Vector2();
        return output.add(new Vector2(
               screenSize.width / 2 - getPosition().getX() * zoom,
               screenSize.height / 2 - getPosition().getY() * zoom
        ));
    }
    public Vector2 getScaleOffset(){
        Vector2 output = new Vector2();
        return output.add(zoom);
    }
    
    
    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {

    }

    @Override
    public void onDebugActivate() {
        super.onDebugActivate();
    }

    @Override
    public void onKeyPressed(KeyEvent e, int keyCode) {
        if(DebugManager.isDebug()){
            if(keyCode == KeyEvent.VK_DOWN){
            setPosition(getPosition().translate(Vector2.down(), debugSpeed));
            }
            if(keyCode == KeyEvent.VK_UP){
                setPosition(getPosition().translate(Vector2.up(), debugSpeed));
            }
            if(keyCode == KeyEvent.VK_LEFT){
                setPosition(getPosition().translate(Vector2.left(), debugSpeed));
            }
            if(keyCode == KeyEvent.VK_RIGHT){
                setPosition(getPosition().translate(Vector2.right(), debugSpeed));
            }
            if(keyCode == 46){
                zoom -= 0.1f;
            }
            if(keyCode == 47){
                zoom += 0.1f;
            }
            if(keyCode == 59){
                if(debugSpeed > 0.1f){
                    debugSpeed -= 0.1f;
                }
                System.out.println(debugSpeed);
            }
            if(keyCode == 222){
                debugSpeed += 0.1f;
                System.out.println(debugSpeed);
            }
        }   
    }

    @Override
    public void onKeyReleased(KeyEvent e, int keyCode) {

    }

    @Override
    public void onKeyTyped(KeyEvent e, int keyCode) {

    }

    @Override
    public void onMouseClicked(MouseEvent e) {
        
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        
    }

    @Override
    public void onMouseEntered(MouseEvent e) {
        
    }

    @Override
    public void onMouseExited(MouseEvent e) {
        
    }

    @Override
    public void onMouseWheelMoved(MouseWheelEvent e) {
        zoom -= e.getWheelRotation();
    }
}
