/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine;

import Entities.Camera;
import Entities.Entity;
import Entities.UI.UIEntity;
import Entities.UI.UIView;
import Physics.Collidable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
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
    private Queue<UIEntity> uis;
    private Camera currentCamera;
    private UIView currentUIView;
    
    public RenderingPanel(JFrame frame, Color bg) {
        setPreferredSize(frame.getSize());
        setBackground(bg);
        setDoubleBuffered(true);
        
        startEntities = new ConcurrentLinkedQueue<>();
        updateEntities = new CopyOnWriteArrayList<>();
        collidables = new CopyOnWriteArrayList<>();
        
        uis = new ConcurrentLinkedQueue<>();
    }
    
    public int getEntitiesSize(){
        return updateEntities.size();
    }
    public int getCollidableSize(){
        return collidables.size();
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
        uis.clear();
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

    public UIView getCurrentUIView() {
        return currentUIView;
    }

    public void setCurrentUIView(UIView currentUIView) {
        this.currentUIView = currentUIView;
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
            if(c.sendCollider().getEntity().isActive()){
                for(int i = 0; i < collidables.size(); i++){
                if(collidables.get(i) == c){
                    continue;
                }
                if(collidables.get(i).sendCollider().getEntity().isActive()){
                    if(c.sendCollider().intersect(collidables.get(i).sendCollider())){
                        if(!c.sendCollider().getCollidedObject().contains(collidables.get(i).sendCollider())){
                            c.sendCollider().addCollidedObject(collidables.get(i).sendCollider());
                            c.onColliderEnter(collidables.get(i).sendCollider());
                        }
                        else{
                            c.onColliderStay(collidables.get(i).sendCollider());
                        }
                    }
                    else{
                        if(c.sendCollider().getCollidedObject().contains(collidables.get(i).sendCollider())){
                            c.onColliderExit(collidables.get(i).sendCollider());
                            c.sendCollider().removeCollidedObject(collidables.get(i).sendCollider());
                        }
                    }
                }
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
                if(e instanceof UIEntity){
                    uis.add((UIEntity) e);
                    continue;
                }
                e.draw(g, currentCamera.getPositionOffset(), currentCamera.getScaleOffset(), currentCamera.getZoom());
                for(var child : e.getChilds()){
                    if(child.isActive()){
                        child.draw(g, currentCamera.getPositionOffset(), currentCamera.getScaleOffset(), currentCamera.getZoom());
                    }
                }
            }
        }
        while(!uis.isEmpty()){
            uis.poll().draw(g, currentUIView.getPositionOffset(), currentUIView.getScaleOffset(), currentUIView.getZoom());
        }
    }

    @Override
    public void render() {
        repaint();
    }
}
