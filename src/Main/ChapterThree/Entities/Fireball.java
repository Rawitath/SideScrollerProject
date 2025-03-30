/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Animations.Animator;
import Datas.Vector2Int;
import Entities.CollidableEntity;
import Entities.Copyable;
import Entities.Entity;
import Main.ChapterThree.Animation.FireballVertical;
import Main.ChapterTwo.Entities.Spike;
import Main.Entities.Main.Damagable;
import Main.Entities.Main.PhysicableEntity;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author GA_IA
 */
public class Fireball extends CollidableEntity implements Copyable{
    private float delayTime = 1f;
    private float count = 0;
    private boolean spikeEnable = false;
    private Map<Damagable, Float> damageMap = new ConcurrentHashMap<>();
    private Animator animator;
    
    public Fireball(Scene s) {
        super(s);
    }

    @Override
    public void start() {
        
        animator = new Animator();
        animator.setAnimation(new FireballVertical());
        setSpriteSize(new Vector2Int(100, 100));
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
            damageMap.put(d, 0f);
        }
    }

    @Override
    public void onColliderStay(Collider other) {
        for(Damagable d : damageMap.keySet()){
            if(Time.time() - damageMap.get(d) > delayTime){
                d.damageTaken(1);
                damageMap.put(d, Time.time());
            }
        }
    }

    @Override
    public void onColliderExit(Collider other) {
        if(other.getEntity() instanceof Damagable){
            Damagable d = (Damagable) other.getEntity();
            damageMap.remove(d);
        }
    }

    @Override
    public <T extends Entity> T copyOf() {
        Fireball fireball = new Fireball(getScene());
        return (T) fireball;
    }
}
