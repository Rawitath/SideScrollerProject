/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Physics;

import Datas.Vector2;
import Entities.Entity;
import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author GA_IA
 */
public class Collider{
    private Entity entity;
    private Vector2 center;
    private Vector2 bound;
    private List<Collider> collidedObject;
    
    public Collider(Entity entity, Vector2 center, Vector2 bound){
        this.entity = entity;
        this.center = center;
        this.bound = bound;
        
        collidedObject = new CopyOnWriteArrayList<>();
        
    }

    public List<Collider> getCollidedObject() {
        return collidedObject;
    }

    public void addCollidedObject(Collider c) {
        collidedObject.add(c);
    }
    public void removeCollidedObject(Collider c) {
        collidedObject.remove(c);
    }
    
    public Collider(Entity entity){
        this(entity, Vector2.zero(), Vector2.one());
    }
    
    public boolean intersect(Collider other){
        // I n s p i r a t i o n from Rectangle.java
        float tw = entity.getScale().getX() * bound.getX();
        float th = entity.getScale().getY() * bound.getY();
        float rw = other.getEntity().getScale().getX() * other.getBound().getX();
        float rh = other.getEntity().getScale().getY() * other.getBound().getY();
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        float tx = entity.getPosition().getX() + center.getX() - tw / 2;
        float ty = entity.getPosition().getY() + center.getY() - th / 2;
        float rx = other.getEntity().getPosition().getX() + other.getCenter().getX() - rw / 2;
        float ry = other.getEntity().getPosition().getY() + other.getCenter().getY() - rh / 2;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    public Entity getEntity() {
        return entity;
    }
    
    public Vector2 getCenter() {
        return center;
    }

    public void setCenter(Vector2 center) {
        this.center = center;
    }
    
    public Vector2 getBound() {
        return bound;
    }

    public void setBound(Vector2 bound) {
        this.bound = bound;
    }
    
    
}
