/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Entities;

import Animations.Animator;
import Datas.Vector2;
import Main.ChapterFour.Animations.ChefBreath;
import Main.Entities.Main.ChapterManager;
import Main.Entities.Main.Damagable;
import Main.Entities.Main.Lucy;
import Main.Entities.Main.PhysicableEntity;
import Main.GameSystem.Cutscene.CutsceneControllable;
import Physics.Collider;
import Physics.Time;
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
    
    private int health = 20;
    
    private float attacDuration = 2.5f;
    
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

    @Override
    public void update() {
        super.update();
        setSprite(animator.getFrame(Time.deltaTime()), true);
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

    }
}
