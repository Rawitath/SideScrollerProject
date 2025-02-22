/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Datas.Vector2;
import Scenes.Scene;
import java.awt.Dimension;

/**
 *
 * @author GA_IA
 */
public class Camera extends Entity{
    private Dimension screenSize;
    private float zoom;

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
               screenSize.width / 2 - getPosition().getX(),
               screenSize.height / 2 - getPosition().getY()
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
    
}
