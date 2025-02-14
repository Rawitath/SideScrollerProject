/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Datas.Vector2;
import Scenes.Scene;
import java.awt.Graphics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author GA_IA
 */
public abstract class Entity{
    private static int entityCount = 0; 
    
    private int id;
    private Scene scene;
    private String name;
    private String tag;
    private Entity parent = null;
    private List<Entity> childs;
            
    private Vector2 position;
    private double rotation;
    private Vector2 scale;
    
    private boolean active;
    private boolean boundaryVisibled;

    public Entity(Scene s){
        scene = s;
        id = entityCount;
        childs = new CopyOnWriteArrayList<>();
        name = this.getClass().getTypeName();
        tag = "Default";
        
        setPosition(new Vector2());
        setRotation(0.0f);
        setScale(new Vector2(1.0f, 1.0f));
        setActive(true);
        setBoundaryVisibled(false);
        
        entityCount++;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isBoundaryVisibled() {
        return boundaryVisibled;
    }

    public void setBoundaryVisibled(boolean boundaryVisibled) {
        this.boundaryVisibled = boundaryVisibled;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public int getId() {
        return id;
    }

    public Scene getScene() {
        return scene;
    }
    
    public static int getEntityCount() {
        return entityCount;
    }
    
    public Entity getParent(){
        return parent;
    }

    public void setParent(Entity parent) {
        if(this.parent != null){
            this.parent.removeChild(this);
            parent.addChild(this);
        }
        else{
            this.parent = parent;
        }
    }

    public List<Entity> getChilds() {
        return childs;
    }
    
    public void addChild(Entity e){
        if(e.getParent() == null){
            e.setParent(this);
        }
        childs.add(e);
    }
    public void removeChild(Entity e){
        childs.remove(e);
        e.setParent(null);
    }

    public Vector2 getPosition() {
        if(parent != null){
            return position.add(parent.getPosition());
        }
        return position;
    }
    
    public void setPosition(Vector2 position) {
        if(parent != null){
            this.position = position.add(parent.getPosition().negative());
        }
        else{
            this.position = position;
        }
    }
    public Vector2 getLocalPosition() {
        return position;
    }
    public void setLocalPosition(Vector2 position){
        this.position =  position;
    }
    
    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public Vector2 getScale() {
        if(parent != null){
            return scale.multiply(parent.getScale());
        }
        return scale;
    }
    public Vector2 getLocalScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }
    
    public abstract void start();
    public abstract void update();
    public abstract void fixedUpdate();
    
    public void draw(Graphics g, Vector2 posOffset, Vector2 scaleOffset, float zoom) {
        if(isBoundaryVisibled()){
             Vector2 pos = getPosition().multiply(Vector2.negativeY()).multiply(zoom).add(posOffset);
        Vector2 scale = getScale().multiply(scaleOffset);
        g.drawRect(Math.round(pos.getX() - scale.getX() / 2), Math.round(pos.getY() - scale.getY() / 2), Math.round(scale.getX()), Math.round(scale.getY()));
        }
    }
}
