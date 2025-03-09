/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Datas.Vector2;
import Datas.Vector2Int;
import Debugger.DebugManager;
import Debugger.Debuggable;
import Entities.UI.UISlider;
import Inputs.MouseControlable;
import Scenes.Scene;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author GA_IA
 */
public abstract class Entity implements Debuggable, MouseControlable{
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
    
    private Vector2Int positionOnScreen;
    private Vector2Int sizeOnScreen;
    
    private Point mousePoint;
    
    private boolean clickable;
    private boolean onDebug;
    
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
        
        positionOnScreen = new Vector2Int();
        sizeOnScreen = new Vector2Int();
        
        mousePoint = new Point();
        
        entityCount++;
    }
    public void onAddedToParent(){
        
    }
    public void onRemoveFromParent(){
        
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
        e.onAddedToParent();
    }
    public void removeChild(Entity e){
        e.onRemoveFromParent();
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
        if(parent != null){
            if(parent.getScale().getX() == 0 && parent.getScale().getY() == 0){
                this.scale = Vector2.zero();
                return;
            }
            else if(parent.getScale().getX() == 0){
                this.scale = scale.multiply(new Vector2(0f,parent.getScale().getY()));
                return;
            }
            else if(parent.getScale().getY() == 0){
                this.scale = scale.multiply(new Vector2(parent.getScale().getY(), 0f));
                return;
            }
            this.scale = scale.multiply(new Vector2(1 / parent.getScale().getX(), 1 / parent.getScale().getY()));
        }
        else{
            this.scale = scale;
        }
    }
    public void setLocalScale(Vector2 scale){
        this.scale = scale;
    }
    
    public abstract void start();
    public abstract void update();
    
    public void lateUpdate(){
        if(DebugManager.isDebug()){
            if(mousePoint.x >= positionOnScreen.getX() - sizeOnScreen.getX() / 2 &&
                mousePoint.x < positionOnScreen.getX() + sizeOnScreen.getX() / 2 &&
                mousePoint.y >= positionOnScreen.getY() - sizeOnScreen.getY() / 2 &&
                mousePoint.y < positionOnScreen.getY() + sizeOnScreen.getY() / 2){
                    clickable = true;
            }
            else{
                clickable = false;
            }
            
            if(onDebug){
                DebugManager.debugEntity(this);
            }
           
        }
    }
    public abstract void fixedUpdate();
    public void remove(){}
    
    public void draw(Graphics g, Vector2 posOffset, Vector2 scaleOffset, float zoom) {
        if(isBoundaryVisibled()){
             Vector2 pos = getPosition().multiply(Vector2.negativeY()).multiply(zoom).add(posOffset);
        Vector2 scale = getScale().multiply(scaleOffset);
        positionOnScreen = new Vector2Int(Math.round(pos.getX() - scale.getX() / 2), Math.round(pos.getY() - scale.getY() / 2));
        sizeOnScreen = new Vector2Int(Math.round(scale.getX()), Math.round(scale.getY()));
        g.drawRect(positionOnScreen.getX(), positionOnScreen.getY(), sizeOnScreen.getX(), sizeOnScreen.getY());
        }
    }

    public Vector2Int getPositionOnScreen() {
        return positionOnScreen;
    }

    public Vector2Int getSizeOnScreen() {
        return sizeOnScreen;
    }
    
    @Override
    public void onDebugActivate() {
        setBoundaryVisibled(true);
    }

    @Override
    public void onMouseClicked(MouseEvent e) {
        
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        if(clickable){
            onDebug = true;
        }
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

    }

    @Override
    public void onMouseDragged(MouseEvent e) {

    }

    @Override
    public void onMouseMoved(MouseEvent e) {
        mousePoint = e.getPoint();
    }
    
}
