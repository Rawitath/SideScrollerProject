/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scenes;

import Entities.Camera;
import Entities.Entity;
import Entities.UIView;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author GA_IA
 */
public abstract class Scene {
    private Integer id = null;
    private String name;
    private List<Entity> entities;
    private Camera mainCamera = null;
    private UIView mainUIView = null;
    
    public Scene(){
        entities = new CopyOnWriteArrayList<>();
        name = this.getClass().getTypeName();
        mainCamera = new Camera(this);
        mainUIView = new UIView(this);
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
        for(var e : entities){
            removeEntity(e);
        }
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
        SceneManager.addToRender(e);
    }
    
    public void removeEntity(Entity e){
        SceneManager.removeFromRender(e);
        entities.remove(e);
    }

    public List<Entity> getEntities() {
        return entities;
    }
    
    public Camera getCamera(){
        return mainCamera;
    }

    public UIView getUIView() {
        return mainUIView;
    }
    
}
