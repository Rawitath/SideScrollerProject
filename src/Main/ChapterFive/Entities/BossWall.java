/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Entities;

import Datas.Vector2;
import Entities.CollidableEntity;
import Entities.Copyable;
import Entities.Entity;
import Main.Entities.Main.PhysicableEntity;
import Physics.Collider;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class BossWall extends CollidableEntity implements Copyable{
    
    private boolean isActivated = false;
    public BossWall(Scene s) {
        super(s);
        getCollider().setBound(new Vector2(1, 100));
        getCollider().setSolid(false);
        setName("BossWall");
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setIsActivated(boolean isActivated) {
        if(this.isActivated == isActivated){
            return;
        }
        this.isActivated = isActivated;
        getCollider().setSolid(isActivated);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {

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
    
    @Override
    public <T extends Entity> T copyOf() {
        BossWall bw = new BossWall(getScene());
        return (T) bw;
    }
    
}
