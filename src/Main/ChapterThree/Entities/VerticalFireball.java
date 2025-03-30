/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Datas.Constants;
import Datas.Vector2;
import Entities.Entity;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class VerticalFireball extends Fireball{
    
    private float fallSpeed;
    private String triggerTag = "Lava";
    private float upSpeed = 12.1f;
    
    public VerticalFireball(Scene s) {
        super(s);
    }

    @Override
    public void start() {
        super.start();
        setPosition(new Vector2(getPosition().getX(), getPosition().getY() - 2f));
    }

    @Override
    public void update() {
        super.update();
        if(fallSpeed > 0){
            setFlip(Vector2.negativeY());
        }
        else{
            setFlip(Vector2.one());
        }
    }

    @Override
    public void fixedUpdate() {
        super.fixedUpdate();
        setPosition(getPosition().translate(Vector2.down(), fallSpeed * Time.fixedDeltaTime()));
        fallSpeed += Constants.gravityValue * 0.2f;
    }

    @Override
    public void onColliderEnter(Collider other) {
        super.onColliderEnter(other);
        if(other.getEntity().getTag().equals(triggerTag)){
            fallSpeed = -upSpeed;
        }
    }

    @Override
    public <T extends Entity> T copyOf() {
        VerticalFireball fireball = new VerticalFireball(getScene());
        return (T) fireball;
    }
    
}
