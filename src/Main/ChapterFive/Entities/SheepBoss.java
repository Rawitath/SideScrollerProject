/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Entities;

import Animations.Animator;
import Datas.Vector2;
import Main.ChapterFive.Animations.SheepBreath;
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
public class SheepBoss extends PhysicableEntity implements CutsceneControllable{

    private Animator animator;
    
    private Lucy lucy;
    
    private float previous = 0;
    
    private boolean activated = false;
    
    private ChapterFiveManager manager;
    
    private int health = 20;
    
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
        if(manager.isBoss()){
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
            if(Time.time() - previous > 1.5f){
                int attack = new Random().nextInt();
                if(attack % 2 == 0){
                    attackOrange(side);
                }
                else if(attack % 2 != 0){
                    attackBlue(side);
                }
                previous = Time.time();
            }
        }
        setSprite(animator.getFrame(Time.deltaTime()), true);
        
        
    }
    
    private void attackOrange(int side){
        AttackShard shard = new OrangeAttackShard(getScene(), Vector2.right().multiply(side), 8);
        shard.setPosition(getPosition().add(new Vector2(2 * side, 0)));
        getScene().addEntity(shard);
    }
    private void attackBlue(int side){
        AttackShard shard = new BlueAttackShard(getScene(), Vector2.right().multiply(side), 8);
        shard.setPosition(getPosition().add(new Vector2(2 * side, 0)));
        getScene().addEntity(shard);
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
    
}
