/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Entities;

import Datas.Vector2;
import Entities.CollidableEntity;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public abstract class AttackShard extends CollidableEntity{
    
    private int damage;
    
    private Vector2 direction;
    private float speed;
    private float duration;
    
    private float previous;
    
    public AttackShard(Scene s, Vector2 direction, float speed, float duration) {
        super(s);
        this.direction = direction;
        this.speed = speed;
        this.duration = duration;
    }

    public AttackShard(Scene s, Vector2 direction, float speed) {
        this(s, direction, speed, 4f);
    }

    @Override
    public void start() {
        previous = Time.time();
    }

    @Override
    public void update() {
        
    }

    @Override
    public void fixedUpdate() {
        setPosition(getPosition().translate(direction, speed * Time.fixedDeltaTime()));
        if(Time.time() - previous > duration){
            getScene().removeEntity(this);
        }
    }

    @Override
    public void onColliderEnter(Collider other) {

    }

    @Override
    public void onColliderStay(Collider other) {

    }

    @Override
    public void onColliderExit(Collider other) {

    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    
}
