/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Example;

import Animations.Animator;
import Main.UI.Example.HeartContainer;
import Datas.Constants;
import Datas.Vector2;
import Datas.Vector2Int;
import Entities.Audios.AudioSource;
import Entities.CollidableEntity;
import Entities.UI.UIText;
import Inputs.KeyControlable;
import Main.Animations.Example.LucyBreathAnim;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;
import Scenes.SceneManager;
import Utilities.FileReader;
import java.awt.Font;
import java.awt.event.KeyEvent;

/**
 *
 * @author GA_IA
 */
public class Lucy extends CollidableEntity implements KeyControlable{

    private Vector2 direction;
    private Vector2 lockDirection;
    private float speed = 18f;
    private float fallSpeed = 0f;
    private UIText lifeNum;
    private HeartContainer heartContainer;
    
    private Animator animator;
    
    private int life = 3;
    
    private boolean grounded = false;
    public Lucy(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/lucypixel.png"));
        direction = Vector2.zero();
        lockDirection = Vector2.zero();
        
        animator = new Animator();
        animator.setAnimation(new LucyBreathAnim());
    }

    @Override
    public void start() {
        setPosition(new Vector2(9f, 0f));
        getCollider().setBound(new Vector2(4.2f, 7.6f));
        getCollider().setCenter(new Vector2(0f, -0.8f));
        lifeNum = getScene().getEntity("Life");
        lifeNum.setFont("res/font/Itim-Regular.ttf", Font.TRUETYPE_FONT);
        heartContainer = getScene().getEntity("HeartContainer");
        heartContainer.setHeart(life);
        
        AudioSource a = getScene().getEntity("Music");
//        a.loop(true);
//        a.play();
        
//        setColliderVisibled(true);
        setSpriteSize(new Vector2Int(128, 128));
        setPixelRatio(0.1f);
    }

    @Override
    public void update() {
        lifeNum.setText("Life : "+ String.valueOf(life));
        setSprite(animator.getFrame(Time.deltaTime()));
    }
    
    @Override
    public void fixedUpdate() {
        setPosition(getPosition().translate(direction, speed * Time.fixedDeltaTime()));
        setPosition(getPosition().translate(Vector2.down(), fallSpeed * Time.fixedDeltaTime()));
        if(!grounded){
            fallSpeed += Constants.gravityValue;
        }
    }

    @Override
    public void onKeyPressed(KeyEvent e, int keyCode) {
        if(keyCode == KeyEvent.VK_D && lockDirection.getX() <= 0){
            direction = Vector2.right();
            setFlip(Vector2.negativeX());
            
        }
        if(keyCode == KeyEvent.VK_A && lockDirection.getX() >= 0){
            direction = Vector2.left();
            setFlip(Vector2.one());
        }
        if(keyCode == KeyEvent.VK_SPACE){
            if(grounded){
                fallSpeed = -80f;
            }
        }
        if(keyCode == KeyEvent.VK_P){
            SceneManager.loadScene(0);
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

    @Override
    public void onColliderEnter(Collider other) {
        if(other.getEntity().getTag().equals("Ground")){
            fallSpeed = 0f;
            grounded = true;
        }
        else if(other.getEntity().getTag().equals("Enemy")){
            if(other.isSolid()){
                if(getPosition().getX() > other.getEntity().getPosition().getX()){
                    direction = Vector2.zero();
                    lockDirection = Vector2.left();
                }
                else{
                    direction = Vector2.zero();
                    lockDirection = Vector2.right();
                }
            }
            life--;
            heartContainer.setHeart(life);
            
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
        if(other.getEntity().getTag().equals("Enemy")){
            if(other.isSolid()){
                lockDirection = Vector2.zero();
            }
        }
    }
}
