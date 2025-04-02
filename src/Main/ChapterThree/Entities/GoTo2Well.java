/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Entities.CollidableEntity;
import Main.Entities.Main.ChapterManager;
import Physics.Collider;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */ 
public class GoTo2Well extends CollidableEntity{
    private final ChapterManager manager;

    public GoTo2Well(Scene s, ChapterManager manager) {
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
            manager.goTo(2, "Warp");
        }
    }

    @Override
    public void onColliderStay(Collider other) {

    }

    @Override
    public void onColliderExit(Collider other) {

    }
}
