/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Entities;

import Datas.Vector2;
import Main.Entities.Main.Damagable;
import Main.Entities.Main.PhysicableEntity;
import Physics.Collider;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class OrangeAttackShard extends AttackShard{
    
    public OrangeAttackShard(Scene s, Vector2 direction, float speed, float duration) {
        super(s, direction, speed, duration);
        setSprite(FileReader.readImage("res/default/whitesquare.png"));
    }

    public OrangeAttackShard(Scene s, Vector2 direction, float speed) {
        this(s, direction, speed, 4f);
    }
    @Override
    public void onColliderEnter(Collider other) {
        super.onColliderEnter(other);
        if(other.getEntity() instanceof PhysicableEntity){
            PhysicableEntity p = (PhysicableEntity) other.getEntity();
            if(p.getVelocity().equals(Vector2.zero()) && p instanceof Damagable){
                Damagable d = (Damagable) p;
                d.damageTaken(getDamage());
            }
        }
    }
}
