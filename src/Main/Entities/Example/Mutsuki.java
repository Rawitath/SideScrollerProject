/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Example;

import Datas.Constants;
import Datas.Vector2;
import Entities.CollidableEntity;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;
import Scenes.SceneManager;
import Utilities.FileReader;
import java.awt.event.KeyEvent;
import Inputs.KeyControlable;

/**
 *
 * @author GA_IA
 */
public class Mutsuki extends CollidableEntity{
    
    
    
    private Vector2 direction;
    private float speed = 18f;
    private float fallAcceration = 0f;
    private boolean grounded = false;

    public Mutsuki(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/mutsuki.png"));
        setTag("Enemy");
        direction = Vector2.zero();
    }

    @Override
    public void start() {
        setPosition(new Vector2(-10f, 0f));
        getCollider().setBound(new Vector2(4.2f, 4.2f));
//        setColliderVisibled(true);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void fixedUpdate() {
        setPosition(getPosition().translate(direction, speed * Time.fixedDeltaTime()));
        setPosition(getPosition().translate(Vector2.down(), fallAcceration * Time.fixedDeltaTime()));
        if(!grounded){
            fallAcceration += Constants.gravityValue;
        }
    }

    @Override
    public void onColliderEnter(Collider other) {
        if(other.getEntity().getTag().equals("Ground")){
            fallAcceration = 0f;
            grounded = true;
        }
    }

    @Override
    public void onColliderStay(Collider other) {
        
        
    }

    @Override
    public void onColliderExit(Collider other) {
        if(other.getEntity().getTag().equals("Ground")){
            grounded = false;
        }
    }
//        @Override
//    public void onKeyPressed(KeyEvent e, int keyCode) {
//        if(keyCode == KeyEvent.VK_D){
//            direction = Vector2.left();
//            
//        }
//        if(keyCode == KeyEvent.VK_A){
//            direction = Vector2.right();
//        }
//    }
//
//    @Override
//    public void onKeyReleased(KeyEvent e, int keyCode) {
//        if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_A){
//            direction = Vector2.zero();
//        }
//    }
//
//    @Override
//    public void onKeyTyped(KeyEvent e, int keyCode) {
//        
//    }
//    
}
