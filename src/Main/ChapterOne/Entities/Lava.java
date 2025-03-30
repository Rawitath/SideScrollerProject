/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Entities;

import Animations.Animator;
import Datas.Vector2;
import Entities.CollidableEntity;
import Entities.Copyable;
import Entities.Entity;
import Main.ChapterOne.Animations.LavaAnimation;
import Main.Entities.Main.Damagable;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Lava extends CollidableEntity implements Copyable{

    private Animator animator;
    public Lava(Scene s) {
        super(s);
        setName("Lava");
        setTag("Lava");
        animator = new Animator();
        animator.setAnimation(new LavaAnimation());
        getCollider().setBound(new Vector2(1f, 0.5f));
        getCollider().setCenter(new Vector2(0f, -0.25f));
    }

    @Override
    public void start() {
        setSpriteSize(getSpriteSize().multiply(1f));
    }

    @Override
    public void update() {
        setSprite(animator.getFrame(Time.deltaTime()), true);
    }

    @Override
    public void fixedUpdate() {

    }

    @Override
    public void onColliderEnter(Collider other) {
        if(other.getEntity() instanceof Damagable){
            Damagable d = (Damagable) other.getEntity();
            d.damageTaken(999);
        }
    }

    @Override
    public void onColliderStay(Collider other) {

    }

    @Override
    public void onColliderExit(Collider other) {

    }

    @Override
    public <T extends Entity> T copyOf() {
        Lava lava = new Lava(getScene());
        return (T) lava;
    }
    
}
