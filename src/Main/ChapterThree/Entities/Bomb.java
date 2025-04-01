/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Datas.Vector2;
import Main.Entities.Main.Damagable;
import Main.Entities.Main.PhysicableEntity;
import Main.Entities.Main.Pushable;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Bomb extends PhysicableEntity implements Pushable{

    private boolean isIgnite = false;
    
    private float bombDelay = 4f; 
    private float previous;
    private BombArea bombArea = null;
    private int damage;
    
    public Bomb(Scene s, int damage) {
        super(s);
        this.damage = damage;
        
        setSprite(FileReader.readImage("res/game/animation/mage/bomb.png"));
        setSpriteSize(getSpriteSize().multiply(2f));
    }

    @Override
    public void fixedUpdate() {
        super.fixedUpdate();
        if(isIgnite){
            if(Time.time() - bombDelay < bombDelay){
                
            }
            else{
                for(Collider c : bombArea.getCollider().getCollidedObject()){
                    if(c.getEntity() instanceof Damagable d){
                        d.damageTaken(damage);
                    }
                }
                removeChild(bombArea);
                getScene().removeEntity(this);
            }
        }
    }
    
    @Override
    public void onColliderEnter(Collider other) {
        super.onColliderEnter(other);
        if(!isIgnite){
            if(other.getEntity().getTag().equals("Mage Fire")){
                isIgnite = true;
                bombArea = new BombArea(getScene());
                addChild(bombArea);
            }
        }
        
    }
    
    @Override
    public void onGroundTouch(Collider ground) {
        setVelocity(new Vector2(0, getVelocity().getY()));
    }

    @Override
    public void onGroundExit(Collider ground) {

    }
    
}
