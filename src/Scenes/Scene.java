/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scenes;

import Entities.Camera;
import Entities.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GA_IA
 */
public abstract class Scene {
    private Integer id = null;
    private String name;
    private List<Entity> entities;
    private Camera mainCamera = null;
    
    public Scene(){
        entities = new ArrayList<>();
        name = this.getClass().getTypeName();
        mainCamera = new Camera(this);
    }
    
    public final void assignID(int id){
        if(this.id != null){
            System.err.println(name +"'s ID has already been set.");
            return;
        }
        this.id = id;
    }
    
    public abstract void load();
    
    public void unload(){
        entities.clear();
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addEntity(Entity e){
        entities.add(e);
    }
    
    public void removeEntity(Entity e){
        entities.remove(e);
    }

    public List<Entity> getEntities() {
        return entities;
    }
    
    public Camera getCamera(){
        return mainCamera;
    }
}
