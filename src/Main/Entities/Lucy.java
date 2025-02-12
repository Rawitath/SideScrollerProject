/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities;

import Datas.Vector2;
import Entities.CollidableEntity;
import Entities.SpriteEntity;
import Inputs.KeyControlable;
import Physics.Collider;
import Scenes.Scene;
import Scenes.SceneManager;
import Utilities.FileReader;
import java.awt.event.KeyEvent;

/**
 *
 * @author GA_IA
 */
public class Lucy extends CollidableEntity implements KeyControlable{

    private Vector2 direction;
    private float speed = 0.01f;
    
    public Lucy(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/lucypixel.png"));
        direction = Vector2.zero();
    }

    @Override
    public void start() {
        setPosition(new Vector2(9f, 0f));
        getCollider().setBound(new Vector2(4.2f, 7f));
        setColliderVisibled(true);
    }

    @Override
    public void update() {
        
    }
    @Override
    public void fixedUpdate() {
        setPosition(getPosition().translate(direction, speed));
    }

    @Override
    public void onKeyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_D){
            direction = Vector2.right();
            setFlip(Vector2.negativeX());
            
        }
        if(keyCode == KeyEvent.VK_A){
            direction = Vector2.left();
            setFlip(Vector2.one());
        }
        if(keyCode == KeyEvent.VK_F){
            
        }
    }

    @Override
    public void onKeyReleased(int keyCode) {
        if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_A){
            direction = Vector2.zero();
        }
    }

    @Override
    public void onKeyTyped(int keyCode) {
        
    }

    @Override
    public void onColliderEnter(Collider other) {

    }

    @Override
    public void onColliderStay(Collider other) {
//        getScene().removeEntity(other.getEntity());
        SceneManager.loadScene(1);
    }

    @Override
    public void onColliderExit(Collider other) {

    }
    
}
