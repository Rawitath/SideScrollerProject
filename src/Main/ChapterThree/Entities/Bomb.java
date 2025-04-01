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
        setSpriteSize(getSpriteSize().multiply(2.4f));
        setAnchor(new Vector2(0, 0.3f));
        setName("Mage Bomb");
    }

    @Override
    public void update() {
        super.update();
        if(isIgnite){
            if(((bombDelay - (Time.time() - previous)) * 2) % 2 == 0){
                    setSprite(FileReader.readImage("res/game/animation/mage/blackbomb.png"), true);
                }
                else{
                    setSprite(FileReader.readImage("res/game/animation/mage/redbomb.png"), true);
                }
        }
    }
    
    

    @Override
    public void fixedUpdate() {
        super.fixedUpdate();
        if(isIgnite){
            if(Time.time() - previous >= bombDelay){
                setSprite(FileReader.readImage("res/game/animation/chapter/fireball.png"), true);
                for(Collider c : bombArea.getCollider().getCollidedObject()){
                    if(c.getEntity() instanceof Damagable d){
                        d.damageTaken(damage);
                    }
                }
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
                bombArea.setScale(new Vector2(bombArea.getScale().getX() * 3, bombArea.getScale().getY()));
                previous = Time.time();
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
