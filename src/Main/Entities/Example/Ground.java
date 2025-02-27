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
import Inputs.KeyControlable;
import Scenes.SceneManager;
import java.awt.event.KeyEvent;

/**
 *
 * @author GA_IA
 */
public class Ground extends CollidableEntity{
    private Vector2 direction;
    private float speed = 18f;
    private float upSpeed = 0f;
    private boolean lucy;
    private boolean enemy;
    
    public Ground(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/ground.png"));
        setPosition(new Vector2(0, -16f));
        setScale(new Vector2(20f ,getScale().getY()));
        getCollider().setBound(new Vector2(20f ,3f));
        direction = Vector2.zero();
        setTag("Ground");
//        setColliderVisibled(true);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {
        setPosition(getPosition().translate(direction, speed * Time.fixedDeltaTime()));
        setPosition(getPosition().translate(Vector2.zero(), upSpeed * Time.fixedDeltaTime()));
    }

    @Override
    public void onColliderEnter(Collider other) {
    }

    @Override
    public void onColliderStay(Collider other) {
        
        
    }

    @Override
    public void onColliderExit(Collider other) {
    }
    
    @Override
    public void onKeyPressed(KeyEvent e, int keyCode) {
        if(keyCode == KeyEvent.VK_D){
            direction = Vector2.left();
            
        }
        if(keyCode == KeyEvent.VK_A){
            direction = Vector2.right();
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e, int keyCode) {
        if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_A){
            direction = Vector2.zero();
        }
    }

    @Override
    public void onKeyTyped(KeyEvent e, int keyCode) {
        
    }
    
}
