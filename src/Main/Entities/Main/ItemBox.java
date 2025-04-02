/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Main;

import Entities.CollidableEntity;
import Entities.Entity;
import Physics.Collider;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ItemBox extends CollidableEntity implements Interactable{

    public ItemBox(Scene s) {
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

    }

    @Override
    public void onColliderExit(Collider other) {

    }

    @Override
    public void interact(Entity interactor) {
        if(interactor instanceof Lucy l){
            
        }
    }
    
}
