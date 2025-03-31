/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Entities;

import Entities.CollidableEntity;
import Entities.Entity;
import Physics.Collider;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public abstract class BreakableBlock extends CollidableEntity{

    public BreakableBlock(Scene s) {
        super(s);
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
        destroyBlock(other.getEntity());
    }

    @Override
    public void onColliderExit(Collider other) {

    }
    public abstract void destroyBlock(Entity e);
    protected void destroy(){
        getScene().removeEntity(this);
    }
}
