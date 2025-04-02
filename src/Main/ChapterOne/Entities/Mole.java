/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Entities;

import Animations.Animator;
import Datas.Vector2;
import Datas.Vector2Int;
import Entities.CollidableEntity;
import Entities.SpriteEntity;
import Main.ChapterOne.Animations.MoleBreath;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class Mole extends SpriteEntity{

    private Animator animator;
    
    private MoleStep step1;
    private MoleStep step2;
    
    public Mole(Scene s) {
        super(s);
        animator = new Animator();
        animator.setAnimation(new MoleBreath());
        
        step1 = new MoleStep(s);
        step2 = new MoleStep(s);
        addChild(step1);
        addChild(step2);
    }

    @Override
    public void start() {
        setSpriteSize(new Vector2Int(256*2, 128*2));
        setPosition(getPosition().add(new Vector2(2.05f, 0)));
        
        step2.setLocalPosition(new Vector2(-1f, 0f));
        step2.setLocalScale(new Vector2(3.4f, 2f));
        step1.setLocalPosition(new Vector2(1.1f, -0.5f));
        step1.setLocalScale(new Vector2(0.8f, 1f));
    }

    @Override
    public void update() {
        setSprite(animator.getFrame(Time.deltaTime()), true);
        
    }

    @Override
    public void fixedUpdate() {

    }
    
}
