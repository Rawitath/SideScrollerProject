/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Datas.Vector2;
import Physics.Collidable;
import Physics.Collider;
import Scenes.Scene;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author GA_IA
 */
public abstract class CollidableEntity extends SpriteEntity implements Collidable{
    
    private Collider collider;
    private boolean colliderVisibled;
    
    public CollidableEntity(Scene s) {
        super(s);
        collider = new Collider(this);
        setColliderVisibled(false);
    }

    public Collider getCollider() {
        return collider;
    }

    public boolean isColliderVisibled() {
        return colliderVisibled;
    }

    public void setColliderVisibled(boolean colliderVisibled) {
        this.colliderVisibled = colliderVisibled;
    }
    
    @Override
    public Collider sendCollider() {
        return getCollider();
    }

    @Override
    public void draw(Graphics g, Vector2 posOffset, Vector2 scaleOffset, float zoom) {
        super.draw(g, posOffset, scaleOffset, zoom);
        if(isColliderVisibled()){
             Vector2 pos = getPosition().multiply(Vector2.negativeY()).add(collider.getCenter()).multiply(zoom).add(posOffset);
        Vector2 scale = getScale().multiply(collider.getBound()).multiply(scaleOffset);
        g.setColor(new Color(0.0f, 1.0f, 0.0f, 0.4f));
        g.fillRect(Math.round(pos.getX() - scale.getX() / 2), Math.round(pos.getY() - scale.getY() / 2), Math.round(scale.getX()), Math.round(scale.getY()));
        }
    }
    
}
