/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Entities;

import Animations.Animation;
import Animations.Animator;
import Datas.Vector2;
import Entities.Copyable;
import Main.Entities.Main.Damagable;
import Main.Entities.Main.Lucy;
import Main.Entities.Main.PhysicableEntity;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public abstract class CommonEnemy extends PhysicableEntity implements Damagable, Copyable{
    
    private Animator animator;
    private int health;
    
    private float limit;
    private float limitY = 2f;
    private float attackLimit;
    
    private boolean isDamageTaken;
    private float countdownLeft;
    private float damageCountdown;
    
    private boolean isAttacking = false;
    private float attackTime = 0.35f;
    private float attackDelay = 1f;
    private float delayCountdown;
    private float attackCountdown;
    private int facing = 1;
    private float speed = 3f;
    
    private Animation breathAnimation;
    private Animation runAnimation;
    private Animation attackAnimation;
    
    protected Lucy lucy;
    
    public CommonEnemy(Scene s, int health, float attackLimit, float limit) {
        super(s);
        this.health = health;
        this.attackLimit = attackLimit;
        this.limit = limit;
        animator = new Animator();
        setTag("Enemy");
    }

    public Animation getBreathAnimation() {
        return breathAnimation;
    }

    public void setBreathAnimation(Animation breathAnimation) {
        this.breathAnimation = breathAnimation;
    }

    public Animation getRunAnimation() {
        return runAnimation;
    }

    public int getFacing() {
        return facing;
    }
    
    public void setRunAnimation(Animation runAnimation) {
        this.runAnimation = runAnimation;
    }

    public Animation getAttackAnimation() {
        return attackAnimation;
    }

    public void setAttackAnimation(Animation attackAnimation) {
        this.attackAnimation = attackAnimation;
    }
    
    @Override
    public void update() {
        super.update();
        if(health > 0){
            if(!isDamageTaken && !isAttacking){
                
                    if(Math.abs(getVelocity().getX()) > 0.01f){
                        animator.setAnimation(runAnimation);
                    }
                    else{
                        animator.setAnimation(breathAnimation);
                    }
                
            }
        }
        setSprite(animator.getFrame(Time.deltaTime()), true);
    }
    @Override
    public void fixedUpdate() {
        
        lucy = this.getScene().getEntity("Lucy");
        
        super.fixedUpdate();
        
        Vector2 lucyDistance = lucy.getPosition();
        Vector2 kotoriDistance = this.getPosition();
        
        if(isDamageTaken)
        {
            if(Time.time() - countdownLeft >= damageCountdown){
                isDamageTaken = false;
            }
        }
        if(isAttacking){
            if(Time.time() - attackCountdown >= attackTime){
                onAttack();
                isAttacking = false;
            }
        }
        
        // enemy move on X-axis
        if(!isAttacking){
            if(Math.abs(lucyDistance.getY() - kotoriDistance.getY()) < limitY){
                if(Math.abs(lucyDistance.getX() - kotoriDistance.getX()) < attackLimit){
                    if(Time.time() - delayCountdown >= attackDelay){
                        stop();
                        attack();
                    }
                }
                else if (Math.abs(lucyDistance.getX() - kotoriDistance.getX()) < limit){
                    if (lucyDistance.getX() > kotoriDistance.getX()){
                        moveRight();
                    }
                    else if (lucyDistance.getX() < kotoriDistance.getX()){
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
    
    public abstract void onAttack();
    
    private void attack(){
        if(isAttacking){
            return;
        }
        
        animator.setAnimation(attackAnimation);
        
        isAttacking = true;
        attackCountdown = Time.time();
        delayCountdown = Time.time();
    }
    protected void moveLeft() {
        setVelocity(new Vector2(-speed, getVelocity().getY()));
        setFlip(Vector2.one());
    }
    
    protected void moveRight() {
        setVelocity(new Vector2(speed, getVelocity().getY()));
        setFlip(Vector2.negativeX());
    }
    
    protected void stop() {
        setVelocity(new Vector2(0, getVelocity().getY()));
    }
    
    @Override
    public void onGroundTouch(Collider ground) {

    }

    @Override
    public void onGroundExit(Collider ground) {

    }

    @Override
    public void damageTaken(int damage) {
        if(isDamageTaken){
            return;
        }
        health -= damage;
        if(health < 1){
            getScene().removeEntity(this);
        }
        else{
            isDamageTaken = true;
            countdownLeft = Time.time();
        }
    }
}
