/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterTwo.Entities;

import Entities.CollidableEntity;
import Entities.Entity;
import Main.Entities.Main.ChapterManager;
import Main.Entities.Main.Interactable;
import Physics.Collider;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class Warp extends CollidableEntity implements Interactable{

    public Warp(Scene s) {
        super(s);
        setName("Warp");
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
    public void interact(Entity interactor) {
        ChapterManager manager = getScene().getEntity("Manager2");
        manager.goTo(3, "Rope");
    }
    
}
