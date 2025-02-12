/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Datas.Vector2;
import Scenes.Scene;
import java.awt.Graphics;

/**
 *
 * @author GA_IA
 */
public abstract class Entity{
    private static int entityCount = 0; 
    
    private int id;
    private Scene parentScene;
            
    private Vector2 position;
    private double rotation;
    private Vector2 scale;

    public Entity(Scene s){
        parentScene = s;
        id = entityCount;
        setPosition(new Vector2());
        setRotation(0.0f);
        setScale(new Vector2(1.0f, 1.0f));
        entityCount++;
    }

    public int getId() {
        return id;
    }

    public Scene getParentScene() {
        return parentScene;
    }
    
    public static int getEntityCount() {
        return entityCount;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }
    
    public abstract void start();
    public abstract void update();
    public abstract void fixedUpdate();
    
    public void draw(Graphics g, Vector2 posOffset, Vector2 scaleOffset) {
        Vector2 pos = getPosition().add(posOffset);
        Vector2 scale = getScale().multiply(scaleOffset);
        g.drawRect(Math.round(pos.getX() - scale.getX() / 2), Math.round(pos.getY() - scale.getY() / 2), Math.round(scale.getX()), Math.round(scale.getY()));
    }
}
