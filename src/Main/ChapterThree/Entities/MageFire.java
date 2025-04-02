/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Animations.Animator;
import Datas.Vector2;
import Main.ChapterThree.Animation.FireballHorizontal;
import Main.Entities.Main.AttackProjectile;
import Main.Entities.Main.Damagable;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class MageFire extends AttackProjectile{
    
    private Animator animator;
    
    public MageFire(Scene s, Vector2 direction, float speed, float duration) {
        super(s, direction, speed, duration);
        setTag("Mage Fire");
        animator = new Animator();
        animator.setAnimation(new FireballHorizontal());
        setSpriteSize(getSpriteSize().multiply(0.7f));
        getCollider().setBound(new  Vector2(1f, 0.7f));
        setFlip(Vector2.negativeX());
    }

    @Override
    public void update() {
        super.update();
        setSprite(animator.getFrame(Time.deltaTime()), true);
    }

    @Override
    public void onColliderEnter(Collider other) {
        super.onColliderEnter(other);
        if(other.getEntity() instanceof Damagable){
            Damagable d = (Damagable) other.getEntity();
            d.damageTaken(getDamage());
        }
    }
    
    
    
}
