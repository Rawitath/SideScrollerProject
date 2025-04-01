/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Entities;

import Animations.Animator;
import Datas.Vector2;
import Entities.Copyable;
import Entities.Entity;
import Main.ChapterOne.Animations.ZombieAttack;
import Main.ChapterOne.Animations.ZombieBreath;
import Main.ChapterOne.Animations.ZombieRun;
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
public class Zombie extends CommonEnemy{
    
    private Animator animator;
    private int health;
    
    private float limit = 5f;
    private float limitY = 5f;
    private float attackLimit = 1f;
    
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

    public Zombie(Scene s) {
        super(s, 1, 1, 5);
        setTag("Enemy");
    }

    @Override
    public void start() {
        super.start();
        setAnchor(new Vector2(0, 0.18f));
        setSpriteSize(getSpriteSize().multiply(1.65f));
        
        getCollider().setBound(getCollider().getBound().multiply(new Vector2(0.6f, 0.8f)));
        getCollider().setCenter(new Vector2(-0.05f, -0.16f));
        
        addIgnore("Player");
        
        setBreathAnimation(new ZombieBreath());
        setRunAnimation(new ZombieRun());
        setAttackAnimation(new ZombieAttack());
    }
   
//    @Override
//    public void damageTaken(int damage) {
//        if(isDamageTaken){
//            return;
//        }
//        health -= damage;
//        if(health < 1){
//            getScene().removeEntity(this);
//        }
//        else{
//            isDamageTaken = true;
//            countdownLeft = Time.time();
//        }
//    }

    @Override
    public <T extends Entity> T copyOf() {
        return (T) new Zombie(getScene());
    }

    @Override
    public void onAttack() {
        Vector2 lucyDistance = lucy.getPosition();
        Vector2 kotoriDistance = this.getPosition();
        if(Math.abs(lucyDistance.getX() - kotoriDistance.getX()) < attackLimit &&
           Math.abs(lucyDistance.getY() - kotoriDistance.getY()) < 2){
            lucy.damageTaken(1);
        }
    }
    
}
