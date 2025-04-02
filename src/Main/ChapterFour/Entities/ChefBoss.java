/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Entities;

import Animations.Animator;
import Datas.Vector2;
import Main.ChapterFive.Animations.SheepDead;
import Main.ChapterFour.Animations.ChefAttackOne;
import Main.ChapterFour.Animations.ChefBreath;
import Main.ChapterFour.Animations.ChefDead;
import Main.ChapterFour.Animations.ChefWalk;
import Main.Entities.Main.ChapterManager;
import Main.Entities.Main.Damagable;
import Main.Entities.Main.Lucy;
import Main.Entities.Main.PhysicableEntity;
import Main.GameSystem.Cutscene.CutsceneControllable;
import Physics.Collider;
import Physics.Time;
import Saves.SaveManager;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChefBoss extends PhysicableEntity implements CutsceneControllable, Damagable{
    private Animator animator;
    
    private Lucy lucy;
    
    private float previous = 0;
    
    private boolean activated = false;
    
    private ChapterManager manager;
    
    private int health = 15;
    
    private float attack1Time = 0.67f;
    private float attack2Time = 1.08f;
    private float attackCountdown = 1.5f;
    
    private boolean isAttacking = false;
    private float attackLimit = 1;
    private float limit = 13f;
    private float limitY = 2;
    private float attackTime = 0.35f;
    private float attackDelay = 0.1f;
    private float delayCountdown;
    
    private float fadeDuration = 3f;
    
    public ChefBoss(Scene s, Lucy lucy) {
        super(s);
        
        this.lucy = lucy;
        animator = new Animator();
        animator.setAnimation(new ChefBreath());
        setSpriteSize(getSpriteSize().multiply(2f));
    }

    @Override
    public void start() {
        super.start();
        manager = getScene().getEntity("Manager4");
    }

    public int getHealth() {
        return health;
    }
    
    @Override
    public void update() {
        super.update();
        if(health > 0){
            if(!isAttacking){
                
                    if(Math.abs(getVelocity().getX()) > 0.01f){
                        animator.setAnimation(new ChefWalk());
                    }
                    else{
                        animator.setAnimation(new ChefBreath());
                    }
                
            }
        }
        else{
            if(animator.isAnimationEnd()){
                if(Time.time() - previous >= fadeDuration){
                    manager.setIsBoss(false);
                    getScene().removeEntity(this);
                }
                else{
                    if(getAlpha() - (1 / fadeDuration) * Time.fixedDeltaTime() > 0){
                        setAlpha(getAlpha() - (1 / fadeDuration) * Time.fixedDeltaTime());
                    }
                }
            }
            }
        setSprite(animator.getFrame(Time.deltaTime()), true);
    }
    
    private int side = 1;

    @Override
    public void fixedUpdate() {
        super.fixedUpdate();
        if(manager.isBoss() && lucy.getHealth() > 0 && health > 0){
            Vector2 lucyDistance = lucy.getPosition();
            Vector2 selfDistance = this.getPosition();
            if(isAttacking){
            if(Time.time() - attackCountdown >= attackTime){
                if(Math.abs(lucyDistance.getX() - selfDistance.getX()) < attackLimit &&
                    Math.abs(lucyDistance.getY() - selfDistance.getY()) < 1){
                     lucy.damageTaken(1);
                 }
                isAttacking = false;
            }
            // enemy move on X-axis
            }else{
            if(Math.abs(lucyDistance.getY() - selfDistance.getY()) < limitY){
                if(Math.abs(lucyDistance.getX() - selfDistance.getX()) < attackLimit){
                    if(Time.time() - delayCountdown >= attackDelay){
                        stop();
                        attack();
                    }
                }
                else if (Math.abs(lucyDistance.getX() - selfDistance.getX()) < limit){
                    if (lucyDistance.getX() > selfDistance.getX()){
                        moveRight();
                    }
                    else if (lucyDistance.getX() < selfDistance.getX()){
                        moveLeft();
                    }
                }
                else{
                    stop();
                }
            }
            else {
                stop();
            }
        }
        
        }
        
        
    }
     private void attack(){
        if(isAttacking){
            return;
        }
        
        animator.setAnimation(new ChefAttackOne());
        
        isAttacking = true;
        attackCountdown = Time.time();
        delayCountdown = Time.time();
    }
    @Override
    public void onGroundTouch(Collider ground) {

    }

    @Override
    public void onGroundExit(Collider ground) {

    }

    @Override
    public void moveLeft() {
        setVelocity(new Vector2(-9, getVelocity().getY()));
        setFlip(Vector2.one());
    }

    @Override
    public void moveRight() {
        setVelocity(new Vector2(9, getVelocity().getY()));
        setFlip(Vector2.negativeX());
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
            animator.setAnimation(new ChefDead());
            SaveManager.getInstance().getCurrentSave().getDefeatedBosses().add(1);
            SaveManager.getInstance().saveCurrentSave();
            previous = Time.time();
        }
    }
}
