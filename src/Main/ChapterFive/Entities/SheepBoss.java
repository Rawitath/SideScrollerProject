/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Entities;

import Animations.Animator;
import Main.ChapterFive.Animations.SheepBreath;
import Main.Entities.Main.PhysicableEntity;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class SheepBoss extends PhysicableEntity{

    private Animator animator;
    
    public SheepBoss(Scene s) {
        super(s);
        animator = new Animator();
        animator.setAnimation(new SheepBreath());
    }

    @Override
    public void update() {
        super.update();
        setSprite(animator.getFrame(Time.deltaTime()));
    }

    @Override
    public void onGroundTouch(Collider ground) {

    }

    @Override
    public void onGroundExit(Collider ground) {

    }
    
}
