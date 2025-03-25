/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Main;

import Datas.Vector2;
import Inputs.KeyControlable;
import Physics.Collider;
import Scenes.Scene;
import java.awt.event.KeyEvent;

/**
 *
 * @author GA_IA
 */
public class Lucy extends PhysicableEntity implements KeyControlable{
    
    private float speed = 18.0f;
    private float jumpForce = 27f;
    
    public Lucy(Scene s) {
        super(s);
        setName("Lucy");
        setTag("Player");
    }

    @Override
    public void start() {
        super.start();
        getScene().getCamera().setZoom(60f);
    }

    @Override
    public void update() {
        super.update();
        getScene().getCamera().setPosition(getPosition().multiply(Vector2.negativeY()));
    }
    
    @Override
    public void onKeyPressed(KeyEvent e, int keyCode) {
        if(keyCode == KeyEvent.VK_D){
            if(getVelocity().getX() < 0){
                setVelocity(Vector2.zero());
            }
            else{
                setVelocity(Vector2.right(), speed);
                setFlip(Vector2.negativeX());
            }
        }
        if(keyCode == KeyEvent.VK_A){
            if(getVelocity().getX() > 0){
                setVelocity(Vector2.zero());
            }
            else{
                setVelocity(Vector2.left(), speed);
                setFlip(Vector2.one());
            }
        }
        if(keyCode == KeyEvent.VK_SPACE){
            if(isGrounded()){
                addVelocity(Vector2.up(), jumpForce);
            }
        }
    }
    
    @Override
    public void onKeyReleased(KeyEvent e, int keyCode) {
        if(getVelocity().getX() == 0){
            if(keyCode == KeyEvent.VK_D){
                setVelocity(Vector2.left(), speed);
                setFlip(Vector2.one());
            }
            else if(keyCode == KeyEvent.VK_A){
                setVelocity(Vector2.right(), speed);
                setFlip(Vector2.negativeX());
            }
            return;
        }
        if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_A){
            setVelocity(Vector2.zero());
        }
    }

    @Override
    public void onKeyTyped(KeyEvent e, int keyCode) {
        
    }

    @Override
    public void onColliderEnter(Collider other) {
        super.onColliderEnter(other);
    }
    
}
