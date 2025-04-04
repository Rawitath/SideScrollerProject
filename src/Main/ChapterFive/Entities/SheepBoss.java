/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Entities;

import Animations.Animator;
import Datas.Vector2;
import Entities.Entity;
import Main.ChapterFive.Animations.SheepAttack;
import Main.ChapterFive.Animations.SheepAttackTwo;
import Main.ChapterFive.Animations.SheepBreath;
import Main.ChapterFive.Animations.SheepDead;
import Main.ChapterFive.Cutscenes.FinalCutscene;
import Main.ChapterThree.Animation.MageDead;
import Main.Entities.Main.Damagable;
import Main.Entities.Main.Lucy;
import Main.Entities.Main.PhysicableEntity;
import Main.GameSystem.Cutscene.Cutscene;
import Main.GameSystem.Cutscene.CutsceneControllable;
import Physics.Collider;
import Physics.Time;
import Saves.SaveManager;
import Scenes.Scene;
import java.util.Random;

/**
 *
 * @author GA_IA
 */
public class SheepBoss extends PhysicableEntity implements CutsceneControllable, Damagable{

    private Animator animator;
    
    private Lucy lucy;
    
    private float previous = 0;
    
    private boolean activated = false;
    
    private ChapterFiveManager manager;
    
    private int health = 20;
    
    private float attacDuration = 2f;
    
    private float attack1Time = 0.67f;
    private float attack2Time = 1.08f;
    private float attackCountdown = 1.5f;
    
    private boolean isAttacking = false;
    
    public SheepBoss(Scene s, Lucy lucy) {
        super(s);
        
        this.lucy = lucy;
        animator = new Animator();
        animator.setAnimation(new SheepBreath());
        setSpriteSize(getSpriteSize().multiply(2f));
    }

    @Override
    public void start() {
        super.start();
        manager = getScene().getEntity("Manager5");
    }
    
    private int side = 1;

    @Override
    public void update() {
        super.update();
        setSprite(animator.getFrame(Time.deltaTime()), true);
    }

    int attack = 0;
    
    @Override
    public void fixedUpdate() {
        super.fixedUpdate();
        if(manager.isBoss() && lucy.getHealth() > 0 && health > 0){
            Vector2 lucyDistance = lucy.getPosition();
            Vector2 selfDistance = this.getPosition();

            float limit = 100f;

            // enemy move on X-axis
            if (Math.abs(lucyDistance.getX() - selfDistance.getX()) < limit){
                if (lucyDistance.getX() > selfDistance.getX()){
                    side = 1;
                    setFlip(Vector2.negativeX());
                    
                }
                else if (lucyDistance.getX() < selfDistance.getX()){
                    side = -1;
                    setFlip(Vector2.one());
                }
            }
            if(previous == 0){
                previous = Time.time();
            }
            if(Time.time() - previous > attacDuration){
                if(!isAttacking){
                    attack = new Random().nextInt();
                    if(attack % 10 == 0){
                        if(!isAttacking){
                            attackCountdown = Time.time();
                            isAttacking = true;
                            animator.setAnimation(new SheepAttackTwo());
                        }

                    }
                    else if(attack % 2 == 0){
                        if(!isAttacking){
                            attackCountdown = Time.time();
                            isAttacking = true;
                            animator.setAnimation(new SheepAttack());
                        }
                        
                    }
                    else if(attack % 2 != 0){
                        if(!isAttacking){
                            attackCountdown = Time.time();
                            isAttacking = true;
                            animator.setAnimation(new SheepAttack());
                        }
                        
                    }
                }
                else{
                    if(attack % 10 == 0){
                        if(Time.time() - attackCountdown >= attack2Time){
                            attackRed();
                            isAttacking = false;
                            previous = Time.time();
                            animator.setAnimation(new SheepBreath());
                        }
                    }
                    else if(attack % 2 == 0){
                        if(Time.time() - attackCountdown >= attack1Time){
                            attackOrange(side);
                            isAttacking = false;
                            previous = Time.time();
                            animator.setAnimation(new SheepBreath());
                        }
                    }
                    else if(attack % 2 != 0){
                        if(Time.time() - attackCountdown >= attack1Time){
                            attackBlue(side);
                            isAttacking = false;
                            previous = Time.time();
                            animator.setAnimation(new SheepBreath());
                        } 
                    }
                    
                }
            }
        }
        else{
            if(health < 1){
                if(animator.isAnimationEnd()){
                getScene().removeEntity(this);
                for(Entity e : getScene().getEntities("BossWall")){
                    getScene().removeEntity(e);
                }
                Cutscene cuts = new FinalCutscene(getScene());
                cuts.addControlledEntities("Lucy", lucy);
                    getScene().addEntity(cuts);
                manager.setCutscene(cuts);
                cuts.triggerCutscene();
            }
            }
            
        }
    }
    
    private void attackOrange(int side){
        AttackShard shard = new OrangeAttackShard(getScene(), Vector2.right().multiply(side), 8);
        shard.setDamage(1);
        shard.setPosition(getPosition().add(new Vector2(2 * side, 0)));
        shard.setFlip(new Vector2(-side, 1f));
        getScene().addEntity(shard);
    }
    private void attackBlue(int side){
        AttackShard shard = new BlueAttackShard(getScene(), Vector2.right().multiply(side), 8);
        shard.setDamage(1);
        shard.setPosition(getPosition().add(new Vector2(2 * side, 0)));
         shard.setFlip(new Vector2(-side, 1f));
        getScene().addEntity(shard);
    }
    private void attackRed(){
        AttackShard shard1 = new RedAttackShard(getScene(), Vector2.right(), 8);
        shard1.setDamage(1);
        shard1.setPosition(getPosition().add(new Vector2(2, 0)));
         shard1.setFlip(new Vector2(-side, 1f));
        getScene().addEntity(shard1);
        
        AttackShard shard2 = new RedAttackShard(getScene(), Vector2.left(), 8);
        shard2.setDamage(1);
        shard2.setPosition(getPosition().add(new Vector2(-2, 0)));
        getScene().addEntity(shard2);
        
        AttackShard shard3 = new RedAttackShard(getScene(), Vector2.up(), 8);
        shard3.setDamage(1);
        shard3.setPosition(getPosition().add(new Vector2(0, 2)));
        getScene().addEntity(shard3);
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

    @Override
    public void damageTaken(int damage) {
        health -= damage;
        if(health < 1){
            stop();
            animator.setAnimation(new SheepDead());
            SaveManager.getInstance().getCurrentSave().getDefeatedBosses().add(2);
            SaveManager.getInstance().saveCurrentSave();
            previous = Time.time();
        }
    }
    
}
