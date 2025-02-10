/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine;

import Datas.Vector2;
import Entities.Camera;
import Entities.Entity;
import Entities.GraphicLoopable;
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
    private Camera currentCamera;
    
    public RenderingPanel(JFrame frame, Color bg) {
        setPreferredSize(frame.getSize());
        setBackground(bg);
        setDoubleBuffered(true);
        
        startEntities = new LinkedList<>();
        updateEntities = new ArrayList<>();
    }
    
    public void addEntities(Entity e){
        startEntities.add(e);
    }
    public void removeEntities(Entity e){
        updateEntities.remove(e);
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
            e.start();
            updateEntities.add(e);
        }
        for(var e : updateEntities){
            e.update();
        }
    }

    @Override
    public void fixedUpdate() {
        for(var e : updateEntities){
            e.fixedUpdate();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(var e : updateEntities){
            if(e instanceof GraphicLoopable){
                ((GraphicLoopable) e).draw(g, currentCamera.getPositionOffset(), currentCamera.getScaleOffset());
            }
        }
    }

    @Override
    public void render() {
        repaint();
    }
}
