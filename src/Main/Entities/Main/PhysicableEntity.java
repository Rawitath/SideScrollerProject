/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Main;

import Datas.Constants;
import Datas.Vector2;
import Entities.CollidableEntity;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public abstract class PhysicableEntity extends CollidableEntity{
    private Vector2 velocity;
    
    private Vector2 lockDirection;
    private Collider lockObject;
    
    private boolean grounded = false;
    private Collider groundObject = null;
    private Vector2 groundPosition = null;

    public PhysicableEntity(Scene s) {
        super(s);
        velocity = Vector2.zero();
        lockDirection = Vector2.zero();
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {
        setPosition(getPosition().translate(velocity.add(lockDirection.multiply(velocity).negative()), Time.fixedDeltaTime()));
        if(!grounded){
            addVelocity(Vector2.down(), Constants.gravityValue);
        }
    }
    
    private void onGroundTouch(Collider other){
        if(velocity.getY() >= 0f){
            velocity = velocity.multiply(Vector2.right());
            grounded = true;
            groundObject = other;
            groundPosition = other.getEntity().getPosition();
        }
    }
    private void onGroundExit(){
        groundObject = null;
        grounded = false;
        groundPosition = null;
    }

    @Override
    public void onColliderEnter(Collider other) {
        if(other.isSolid()){
            if(!grounded){
                //Check the bottom is colliding with top of other
                if((getPosition().getY() + getCollider().getCenter().getY() - getCollider().getBound().getY() / 2
                        <=
                    other.getEntity().getPosition().getY() + other.getCenter().getY() + other.getBound().getY() / 2
                    
                        &&
                        getPosition().getY() + getCollider().getCenter().getY() - getCollider().getBound().getY() / 2
                        >
                    (other.getEntity().getPosition().getY() + other.getCenter().getY() + other.getBound().getY() / 2) - 0.15f)){
                    onGroundTouch(other);
                }
                //Check the top is colliding with bottom of other
                if(getPosition().getY() + getCollider().getCenter().getY() + getCollider().getBound().getY() / 2
                        >=
                    other.getEntity().getPosition().getY() + other.getCenter().getY() - other.getBound().getY() / 2
                        &&
                        getPosition().getY() + getCollider().getCenter().getY() + getCollider().getBound().getY() / 2
                        <
                    (other.getEntity().getPosition().getY() + other.getCenter().getY() - other.getBound().getY() / 2) + 0.15f){
                    velocity = velocity.multiply(Vector2.right());
                }
            }
        }
    }

    @Override
    public void onColliderStay(Collider other) {
        if(other.isSolid()){
            //Check if standing on top of solid object
            if(groundObject == other){
                if (!groundPosition.equals(other.getEntity().getPosition())){
                    Vector2 drift = getPosition().add(groundPosition.add(other.getEntity().getPosition().negative()).multiply(Vector2.one().negative()));
                    if(!lockDirection.equals(Vector2.zero())){
                        drift = drift.multiply(Vector2.up());
                    }
                    setPosition(drift);
                }
                groundPosition = other.getEntity().getPosition();
            }
            //Check if approch right or left of other, lockDirection will lock if approch is true
            if(groundObject != other){
                if(getPosition().getX() + getCollider().getCenter().getX() > other.getEntity().getPosition().getX() + other.getCenter().getX()){
                    lockDirection = Vector2.left();
                    lockObject = other;
                    if(other.getEntity().getTag().equals("Pushable")){
                        other.getEntity().setPosition(other.getEntity().getPosition().translate(velocity.multiply(Vector2.right()), Time.fixedDeltaTime()));
                    }
                }
                else if(getPosition().getX() + getCollider().getCenter().getX() < other.getEntity().getPosition().getX() + other.getCenter().getX()){
                    lockDirection = Vector2.right();
                    lockObject = other;
                    if(other.getEntity().getTag().equals("Pushable")){
                        other.getEntity().setPosition(other.getEntity().getPosition().translate(velocity.multiply(Vector2.right()), Time.fixedDeltaTime()));
                    }
                }
           }                      
        }
    }

    @Override
    public void onColliderExit(Collider other) {
        if(other.isSolid()){
            if(groundObject == other){
                onGroundExit();
            }
            if(lockObject == other){
                lockDirection = Vector2.zero();
                lockObject = null;
            }
        }
        if(other.getEntity().getTag().equals("Enemy")){
            
        }
    }

    public Vector2 getVelocity() {
        return velocity;
    }
    
    public void addVelocity(Vector2 direction, float magnitude) {
        this.velocity = velocity.add(direction.multiply(magnitude));
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public Collider getGroundObject() {
        return groundObject;
    }
    
}
