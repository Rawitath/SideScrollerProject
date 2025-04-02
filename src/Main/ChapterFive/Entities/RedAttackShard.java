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
public class RedAttackShard extends AttackShard {
    public RedAttackShard(Scene s, Vector2 direction, float speed, float duration) {
        super(s, direction, speed, duration);
        setSprite(FileReader.readImage("res/game/animation/sheep/slashred.png"));
    }

    public RedAttackShard(Scene s, Vector2 direction, float speed) {
        this(s, direction, speed, 4f);
    }

    @Override
    public void onColliderEnter(Collider other) {
        super.onColliderEnter(other);
        if(other.getEntity() instanceof PhysicableEntity){
            PhysicableEntity p = (PhysicableEntity) other.getEntity();
                Damagable d = (Damagable) p;
                d.damageTaken(getDamage());
        }
    }
}
