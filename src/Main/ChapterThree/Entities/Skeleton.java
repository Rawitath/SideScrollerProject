/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Datas.Vector2;
import Entities.Entity;
import Main.ChapterOne.Entities.CommonEnemy;
import Main.ChapterThree.Animation.SkeletonAttack;
import Main.ChapterThree.Animation.SkeletonBreath;
import Main.ChapterThree.Animation.SkeletonRun;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class Skeleton extends CommonEnemy{
    
    private int health;
    private float attackLimit;
    private float limit;

    public Skeleton(Scene s, int health, float attackLimit, float limit) {
        super(s, health, attackLimit, limit);
        this.health = health;
        this.attackLimit = attackLimit;
        this.limit = limit;
    }
    
    public Skeleton(Scene s){
        this(s, 2, 5, 10);
    }

    @Override
    public void start() {
        super.start();
        setAnchor(new Vector2(0, 0.18f));
        setSpriteSize(getSpriteSize().multiply(1.65f));
        
        getCollider().setBound(getCollider().getBound().multiply(new Vector2(0.6f, 0.8f)));
        getCollider().setCenter(new Vector2(-0.05f, -0.16f));
        
        addIgnore("Player");
        
        setBreathAnimation(new SkeletonBreath());
        setRunAnimation(new SkeletonRun());
        setAttackAnimation(new SkeletonAttack());
    }
    
    

    @Override
    public void onAttack() {
        BoneProjectile p = new BoneProjectile(getScene(), Vector2.right().multiply(getFacing()), 10f);
        p.setDamage(1);
        p.setPosition(getPosition().add(new Vector2(0.5f * getFacing(), -0.18f)));
        getScene().addEntity(p);
    }

    @Override
    public <T extends Entity> T copyOf() {
        return (T) new Skeleton(getScene(), health, attackLimit, limit);
    }
    
}
