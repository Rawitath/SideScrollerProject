/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine;

import Datas.Vector2;
import Entities.Camera;
import Entities.Entity;
import Physics.Collidable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author GA_IA
 */
public class RenderingPanel extends JPanel implements EngineLoopable{
    
    private Queue<Entity> startEntities;
    private List<Entity> updateEntities;
    private List<Collidable> collidables;
    private Camera currentCamera;
    
    public RenderingPanel(JFrame frame, Color bg) {
        setPreferredSize(frame.getSize());
        setBackground(bg);
        setDoubleBuffered(true);
        
        startEntities = new LinkedList<>();
        updateEntities = new ArrayList<>();
        collidables = new ArrayList<>();
    }
    
    public void addEntities(Entity e){
        startEntities.add(e);
    }
    public void removeEntities(Entity e){
        updateEntities.remove(e);
    }
    public void addCollidable(Collidable e){
        collidables.add(e);
    }
    public void removeCollidable(Collidable e){
        collidables.remove(e);
    }
    public void clearEntities(){
        startEntities.clear();
        updateEntities.clear();
    }
    public void clearCollidable(){
        collidables.clear();
    }
    public Camera getCurrentCamera() {
        return currentCamera;
    }

    public void setCurrentCamera(Camera currentCamera) {
        this.currentCamera = currentCamera;
    }
    
    @Override
    public void update() {
        if(!startEntities.isEmpty()){
            Entity e = startEntities.poll();
            if(e.isActive()){
                e.start();
                for(var child : e.getChilds()){
                    if(child.isActive()){
                        child.start();
                    }
                }
            }
            updateEntities.add(e);
        }
        for(var e : updateEntities){
            if(e.isActive()){
                e.update();
                for(var child : e.getChilds()){
                    if(child.isActive()){
                        child.update();
                    }
                }
            }
        }
    }

    @Override
    public void fixedUpdate() {
        //O(n^2)
        for(var c : collidables){
            for(int i = 0; i < collidables.size(); i++){
                if(collidables.get(i) == c){
                    continue;
                }
                if(c.sendCollider().intersect(collidables.get(i).sendCollider())){
                    c.onColliderStay(collidables.get(i).sendCollider());
                }
            }
        }
        for(var e : updateEntities){
            if(e.isActive()){
                e.fixedUpdate();
                for(var child : e.getChilds()){
                   if(child.isActive()){
                       child.fixedUpdate();
                   }
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(var e : updateEntities){
            if(e.isActive()){
                e.draw(g, currentCamera.getPositionOffset(), currentCamera.getScaleOffset(), currentCamera.getZoom());
                for(var child : e.getChilds()){
                    if(child.isActive()){
                        child.draw(g, currentCamera.getPositionOffset(), currentCamera.getScaleOffset(), currentCamera.getZoom());
                    }
                }
            }
        }
    }

    @Override
    public void render() {
        repaint();
    }

    public void add(Entity e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
