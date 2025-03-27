/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Main;

import Animations.Animator;
import Datas.Vector2;
import Inputs.KeyControlable;
import Main.Entities.Main.Animation.LucyBreath;
import Main.Entities.Main.Animation.LucyFall;
import Main.Entities.Main.Animation.LucyJump;
import Main.Entities.Main.Animation.LucyRun;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;
import java.awt.event.KeyEvent;

/**
 *
 * @author GA_IA
 */
public class Lucy extends PhysicableEntity implements KeyControlable{
    
    private float speed = 9.0f;
    private float jumpForce = 16f;
    
    private Animator animator;
    
    private int maxJump = 2;
    private int currentJump = 0;
    
    public Lucy(Scene s) {
        super(s);
        setName("Lucy");
        setTag("Player");
        
        animator = new Animator();
        animator.setAnimation(new LucyBreath());
    }

    @Override
    public void start() {
        super.start();
        setPosition(getPosition().add(new Vector2(0, 1f)));
        getScene().getCamera().setZoom(60f);
        //setAnchor(new Vector2(0, -0.05f));
        setSpriteSize(getSpriteSize().multiply(1.65f));
        
        getCollider().setBound(getCollider().getBound().multiply(new Vector2(0.8f, 1.2f)));
        getCollider().setCenter(new Vector2(0f, -0.05f));
    }

    @Override
    public void update() {
        super.update();
        getScene().getCamera().setPosition(getPosition().multiply(Vector2.negativeY()));
        
        if(!isGrounded()){
            if(getVelocity().getY() > 0){
                animator.setAnimation(new LucyJump());
            }
            else{
                animator.setAnimation(new LucyFall());
            }
        }
        else{
            if(Math.abs(getVelocity().getX()) > 0.001f){
                animator.setAnimation(new LucyRun());
            }
            else{
                animator.setAnimation(new LucyBreath());
            }
        }

        setSprite(animator.getFrame(Time.deltaTime()), true);
    }
    
    @Override
    public void onKeyPressed(KeyEvent e, int keyCode) {
        if(keyCode == KeyEvent.VK_D){
            if(getVelocity().getX() < 0){
                setVelocity(new Vector2(0, getVelocity().getY()));
            }
            else{
                setVelocity(getVelocity().add(Vector2.right().multiply(speed)));
                setFlip(Vector2.negativeX());
            }
        }
        if(keyCode == KeyEvent.VK_A){
            if(getVelocity().getX() > 0){
                setVelocity(new Vector2(0, getVelocity().getY()));
            }
            else{
                setVelocity(getVelocity().add(Vector2.right().multiply(-speed)));
                setFlip(Vector2.one());
            }
        }
        if(keyCode == KeyEvent.VK_SPACE){
            if(currentJump <= maxJump){
                setVelocity(new Vector2(getVelocity().getX(), jumpForce));
                currentJump++;
            }
        }
    }
    
    @Override
    public void onKeyReleased(KeyEvent e, int keyCode) {
        if(getVelocity().getX() == 0){
            if(keyCode == KeyEvent.VK_D){
                setVelocity(getVelocity().add(Vector2.right().multiply(-speed)));
                setFlip(Vector2.one());
            }
            else if(keyCode == KeyEvent.VK_A){
                setVelocity(getVelocity().add(Vector2.right().multiply(speed)));
                setFlip(Vector2.negativeX());
            }
            return;
        }
        if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_A){
            setVelocity(new Vector2(0, getVelocity().getY()));
        }
    }

    @Override
    public void onKeyTyped(KeyEvent e, int keyCode) {
        
    }

    @Override
    public void onColliderEnter(Collider other) {
        super.onColliderEnter(other);
    }

    @Override
    public void onGroundTouch(Collider ground) {
        currentJump = 0;
    }

    @Override
    public void onGroundExit(Collider ground) {
        
    }
    
}
