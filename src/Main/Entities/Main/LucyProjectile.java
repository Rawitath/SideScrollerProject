/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Main;

import Datas.Vector2;
import Physics.Collider;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class LucyProjectile extends AttackProjectile{
    
    public LucyProjectile(Scene s, Vector2 direction, float speed, float duration) {
        super(s, direction, speed, duration);
        setSprite(FileReader.readImage("res/game/main/lucyprojectile.png"));
        setScale(getScale().multiply(0.22f));
        
    }
    public LucyProjectile(Scene s, Vector2 direction, float speed) {
        this(s, direction, speed, 3f);
    }

    @Override
    public void onColliderEnter(Collider other) {
        super.onColliderEnter(other);
        if(!other.getEntity().getTag().equals("Player")){
            if(other.getEntity() instanceof Damagable){
                Damagable d = (Damagable) other.getEntity();
                d.damageTaken(getDamage());
            }
            if(other.isSolid()){
                getScene().removeEntity(this);
            }
        }
    }
    
}
