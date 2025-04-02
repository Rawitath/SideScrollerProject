/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Entities;

import Entities.CollidableEntity;
import Entities.Copyable;
import Entities.Entity;
import Main.Entities.Main.ChapterManager;
import Physics.Collider;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class GoTo5 extends CollidableEntity implements Copyable{
     private final ChapterManager manager;

    public GoTo5(Scene s, ChapterManager manager) {
        super(s);
        this.manager = manager;
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
        if(other.getEntity().getTag().equals("Player")){
            manager.goTo(5, "From4");
        }
    }

    @Override
    public void onColliderStay(Collider other) {

    }

    @Override
    public void onColliderExit(Collider other) {

    }

    @Override
    public <T extends Entity> T copyOf() {
        return (T) new GoTo5(getScene(), manager);
    }
}
