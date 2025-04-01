/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Datas.Vector2;
import Datas.Vector2Int;
import Main.Entities.Main.AttackProjectile;
import Main.Entities.Main.Damagable;
import Physics.Collider;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class BoneProjectile extends AttackProjectile{
    
    public BoneProjectile(Scene s, Vector2 direction, float speed, float duration) {
        super(s, direction, speed, duration);
        setSprite(FileReader.readImage("res/game/animation/skeleton/bone.png"));
        setSpriteSize(new Vector2Int(256, 256));
        setScale(getScale().multiply(0.33f));
        setTag("Lucy Projectile");
    }
    public BoneProjectile(Scene s, Vector2 direction, float speed) {
        this(s, direction, speed, 3f);
    }

    @Override
    public void onColliderEnter(Collider other) {
        super.onColliderEnter(other);
        if(other.getEntity().getTag().equals("Player")){
            if(other.getEntity() instanceof Damagable){
                Damagable d = (Damagable) other.getEntity();
                d.damageTaken(getDamage());
            }
        }
        if(other.isSolid()){
            getScene().removeEntity(this);
        }
    }
}
