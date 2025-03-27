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
    
    private float minimumStep;

    public PhysicableEntity(Scene s) {
        super(s);
        velocity = Vector2.zero();
        lockDirection = Vector2.zero();
        getCollider().setSolid(true);
        minimumStep = 0.15f;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {
        setPosition(getPosition().translate(velocity.add(lockDirection.multiply(Math.abs(velocity.getX())).negative()), Time.fixedDeltaTime()));
        if(!grounded){
            addVelocity(Vector2.down(), Constants.gravityValue);
        }
    }
    
    public abstract void onGroundTouch(Collider ground);
    public abstract void onGroundExit(Collider ground);
    
    private void checkGroundTouch(Collider other){
        if(velocity.getY() < 0f){
            velocity = velocity.multiply(Vector2.right());
            grounded = true;
            groundObject = other;
            groundPosition = other.getEntity().getPosition();
            onGroundTouch(other);
        }
    }
    private void checkGroundExit(){
        onGroundExit(groundObject);
        groundObject = null;
        grounded = false;
        groundPosition = null;
    }

    public float getMinimumStep() {
        return minimumStep;
    }

    public void setMinimumStep(float minimumStep) {
        this.minimumStep = minimumStep;
    }

    @Override
    public void onColliderEnter(Collider other) {
        if(getCollider().isSolid() && other.isSolid()){
            if(!grounded){
                //Check the bottom is colliding with top of other
                if((getPosition().getY() + getCollider().getCenter().getY() - getCollider().getBoundScale().getY() / 2
                        <=
                    (other.getEntity().getPosition().getY() + other.getCenter().getY() + other.getBoundScale().getY() / 2)
                        &&
                        getPosition().getY() + getCollider().getCenter().getY() - getCollider().getBoundScale().getY() / 2
                        >
                    (other.getEntity().getPosition().getY() + other.getCenter().getY() + other.getBoundScale().getY() / 2) - minimumStep)){
                    if((getPosition().getX() + getCollider().getCenter().getX() - getCollider().getBoundScale().getX() / 2) -
                        (other.getEntity().getPosition().getX() + other.getCenter().getX() + other.getBoundScale().getX() / 2) < minimumStep * 2 &&
                            (getPosition().getX() + getCollider().getCenter().getX() + getCollider().getBoundScale().getX() / 2) -
                        (other.getEntity().getPosition().getX() + other.getCenter().getX() - other.getBoundScale().getX() / 2) > minimumStep * 2){
                        checkGroundTouch(other);
                    }
                    
                }
                //Check the top is colliding with bottom of other
                if(getPosition().getY() + getCollider().getCenter().getY() + getCollider().getBoundScale().getY() / 2
                        >=
                        other.getEntity().getPosition().getY() + other.getCenter().getY() - other.getBoundScale().getY() / 2
                        &&
                        getPosition().getY() + getCollider().getCenter().getY() + getCollider().getBoundScale().getY() / 2
                        <
                    (other.getEntity().getPosition().getY() + other.getCenter().getY() - other.getBoundScale().getY() / 2) + minimumStep){
                    velocity = velocity.multiply(Vector2.right());
                }
            }
        }
    }

    @Override
    public void onColliderStay(Collider other) {
        if(getCollider().isSolid() && other.isSolid()){
            //For being on multiple tile
            if(!grounded){
                //Check the bottom is colliding with top of other
                if((getPosition().getY() + getCollider().getCenter().getY() - getCollider().getBoundScale().getY() / 2
                        <=
                    (other.getEntity().getPosition().getY() + other.getCenter().getY() + other.getBoundScale().getY() / 2)
                        &&
                        getPosition().getY() + getCollider().getCenter().getY() - getCollider().getBoundScale().getY() / 2
                        >
                    (other.getEntity().getPosition().getY() + other.getCenter().getY() + other.getBoundScale().getY() / 2) - minimumStep)){
                    checkGroundTouch(other);
                }
            }
            
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
            if(groundObject != other && lockObject != other){
                if(groundObject == null || Math.abs((other.getEntity().getPosition().getY() + other.getCenter().getY() + other.getBoundScale().getY() / 2) - 
                    (groundObject.getEntity().getPosition().getY() + groundObject.getCenter().getY() + groundObject.getBoundScale().getY() / 2)) > minimumStep){
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
    }

    @Override
    public void onColliderExit(Collider other) {
        if(getCollider().isSolid() && other.isSolid()){
            if(groundObject == other){
                checkGroundExit();
            }
            if(lockObject == other){
                lockDirection = Vector2.zero();
                lockObject = null;
            }
        }
    }

    public Vector2 getVelocity() {
        return velocity;
    }
    
    public void addVelocity(Vector2 direction, float magnitude) {
        this.velocity = velocity.add(direction.multiply(magnitude));
    }
    
    public void setVelocity(Vector2 direction, float magnitude) {
        this.velocity = direction.multiply(magnitude);
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
