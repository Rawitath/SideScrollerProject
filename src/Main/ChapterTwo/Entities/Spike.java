/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterTwo.Entities;

import Datas.Vector2;
import Entities.CollidableEntity;
import Entities.Copyable;
import Entities.Entity;
import Main.Entities.Main.Damagable;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author GA_IA
 */
public class Spike extends CollidableEntity implements Copyable{

    private float delayTime = 1f;
    private float count = 0;
    private boolean spikeEnable = false;
    private Map<Damagable, Float> damageMap = new ConcurrentHashMap<>();
    public Spike(Scene s) {
        super(s);
    }

    @Override
    public void start() {
        getCollider().setBound(new Vector2(getCollider().getBound().getX(), getCollider().getBound().getY() * 0.35f));
        getCollider().setCenter(new Vector2(0f, -0.35f));
    }

    @Override
    public void update() {
        
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
        Spike spike = new Spike(getScene());
        return (T) spike;
    }
    
}
