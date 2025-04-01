/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Animations.Animator;
import Datas.Vector2;
import Main.ChapterFive.Animations.SheepBreath;
import Main.ChapterFive.Entities.ChapterFiveManager;
import Main.ChapterThree.Animation.MageBreath;
import Main.ChapterThree.Animation.MageDead;
import Main.ChapterThree.Animation.MageStun;
import Main.Entities.Main.ChapterManager;
import Main.Entities.Main.Damagable;
import Main.Entities.Main.Lucy;
import Main.Entities.Main.PhysicableEntity;
import Main.GameSystem.Cutscene.CutsceneControllable;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;
import java.util.Random;

/**
 *
 * @author GA_IA
 */
public class MageBoss extends PhysicableEntity implements CutsceneControllable, Damagable{

    private Animator animator;
    
    private Lucy lucy;
    
    private float previous = 0;
    
    private boolean activated = false;
    
    private ChapterManager manager;
    
    private int health = 30;
    
    private float attacDuration = 3.5f;
   
    
    public MageBoss(Scene s, Lucy lucy) {
        super(s);
        
        this.lucy = lucy;
        animator = new Animator();
        animator.setAnimation(new MageBreath());
        setSpriteSize(getSpriteSize().multiply(2f));
    }

    @Override
    public void start() {
        super.start();
        manager = getScene().getEntity("Manager3");
    }

    @Override
    public void update() {
        super.update();
        setSprite(animator.getFrame(Time.deltaTime()), true);
    }
    @Override
    public void fixedUpdate() {
        super.fixedUpdate();
        if(manager.isBoss() && lucy.getHealth() > 0 && health > 0){
            Vector2 lucyDistance = lucy.getPosition();
            Vector2 selfDistance = this.getPosition();

            if(previous == 0){
                previous = Time.time();
            }
            if(Time.time() - previous > attacDuration){
                int attack = new Random().nextInt();
                if(attack % 2 == 0){
                    bombAttack();
                }
                else{
                    fireAttack();
                }
                previous = Time.time();
            }
        }
    }
    
    private void bombAttack(){
        Bomb bomb = new Bomb(getScene(), 2);
        int bombPos = new Random().nextInt(1, 7);
        bomb.setPosition(getPosition().add(new Vector2(-bombPos, 4)));
        getScene().addEntity(bomb);
    }
    private void fireAttack(){
        MageFire fire = new MageFire(getScene(), Vector2.left(), 5f, 3f);
        fire.setPosition(getPosition().add(new Vector2(-2, 1)));
        fire.setScale(getScale().multiply(2f));
        getScene().addEntity(fire);
    }
    
    @Override
    public void onGroundTouch(Collider ground) {

    }

    @Override
    public void onGroundExit(Collider ground) {

    }

    @Override
    public void moveLeft() {
        setVelocity(new Vector2(-2, getVelocity().getY()));
    }

    @Override
    public void moveRight() {
        setVelocity(new Vector2(2, getVelocity().getY()));
    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {
        
    }
    
    @Override
    public void stop() {
        setVelocity(new Vector2(0, getVelocity().getY()));
    }

    @Override
    public Vector2 currentPosition() {
        return getPosition();
    }
    
    private void stun(){
        animator.setAnimation(new MageStun());
    }
    
    @Override
    public void damageTaken(int damage) {
        health -= damage;
        if(health < 1){
            stop();
            animator.setAnimation(new MageDead());
        }
        else{
//            //animator.setAnimation(animationMap.get("Hurt"));
//            isDamageTaken = true;
//            countdownLeft = Time.time();
//            statsOpen = false;
        }
    }
    
}
