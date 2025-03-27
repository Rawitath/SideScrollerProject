/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Entities;

import Animations.Animator;
import Datas.Vector2;
import Main.ChapterFive.Animations.SheepBreath;
import Main.Entities.Main.PhysicableEntity;
import Main.GameSystem.Cutscene.CutsceneControllable;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class SheepBoss extends PhysicableEntity implements CutsceneControllable{

    private Animator animator;
    
    public SheepBoss(Scene s) {
        super(s);
        animator = new Animator();
        animator.setAnimation(new SheepBreath());
        setSpriteSize(getSpriteSize().multiply(2f));
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
    
}
