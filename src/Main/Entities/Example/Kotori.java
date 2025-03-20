/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Example;

import Datas.Constants;
import Datas.Vector2;
import Datas.Vector2Int;
import Entities.CollidableEntity;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Kotori extends CollidableEntity{
    private Vector2 direction;
    private float speed = 14f;
    private float fallAcceration = 0f;
    private boolean grounded = false;
    
    public Kotori(Scene s) {
        super(s);
        this.direction = Vector2.zero();
        setSprite(FileReader.readImage("res/game/kotori.jpg"));
        setSpriteSize(new Vector2Int(500, 500));
        setTag("Enemy");
        getCollider().setSolid(true);
    }

    @Override
    public void start() {
        setPosition(new Vector2(-20.5f, -1f));
        getCollider().setBound(new Vector2(4.2f, 4.2f));
//        setColliderVisibled(true);
    }

    @Override
    public void update() {
        
    }
    
    float sec = 4f;
    float previous = Time.time();
    boolean reverse = false;
    @Override
    public void fixedUpdate() {
        var lucy = this.getScene().getEntity("Lucy");
        Vector2 lucyDistance = lucy.getPosition();
        Vector2 kotoriDistance = this.getPosition();
        
        float limit = 20f;
        
        // enemy move on X-axis
        if (Math.abs(lucyDistance.getX() - kotoriDistance.getX()) < limit){
            if (lucyDistance.getX() - 5 > kotoriDistance.getX()){
                this.setPosition(getPosition().translate(direction.add(direction.right()) , speed * Time.fixedDeltaTime()));
            }
            else if (lucyDistance.getX() + 5 < kotoriDistance.getX()){
                this.setPosition(getPosition().translate(direction.add(direction.left()) , speed * Time.fixedDeltaTime()));
            }
        }
        else {
            this.direction = direction.zero();
        }
//        if(Time.time() - previous < sec){
//            if(reverse){
//                setPosition(getPosition().translate(Vector2.up(), 3 * Time.fixedDeltaTime()));
//            }
//            else{
//                setPosition(getPosition().translate(Vector2.down(), 3 * Time.fixedDeltaTime()));
//            }
//        }
//        else{
//            previous = Time.time();
//            reverse = !reverse;
//        }
    }

    @Override
    public void onColliderEnter(Collider other) {
if(getPosition().getX() + getCollider().getCenter().getX() > other.getEntity().getPosition().getX() + other.getCenter().getX()){
                            
                            //Pushable
                            other.getEntity().setPosition(other.getEntity().getPosition().translate(Vector2.left(), speed * Time.fixedDeltaTime()));
                        }
                        else if(getPosition().getX() + getCollider().getCenter().getX() < other.getEntity().getPosition().getX() + other.getCenter().getX()){
                            
                            other.getEntity().setPosition(other.getEntity().getPosition().translate(Vector2.right(), speed * Time.fixedDeltaTime()));
                        }
    }

    @Override
    public void onColliderStay(Collider other) {
        
        
    }

    @Override
    public void onColliderExit(Collider other) {
        
    }
}
