/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Datas.Vector2;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class Camera extends Entity{
    private Vector2 bound;

    public Camera(Scene s) {
        super(s);
        bound = new Vector2(1280, 720);
    }

    public Vector2 getBound() {
        return bound;
    }

    public void setBound(Vector2 bound) {
        this.bound = bound;
    }
    
    public Vector2 getPositionOffset(){
        Vector2 output = new Vector2();
        output.add(new Vector2(
                (bound.getX() / 2) - getPosition().getX(),
                (bound.getY() / 2) - getPosition().getY()
        ));
        return output;
    }
    public Vector2 getScaleOffset(){
        Vector2 output = new Vector2();
        return output;
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
