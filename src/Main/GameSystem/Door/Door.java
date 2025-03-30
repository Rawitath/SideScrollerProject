/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.GameSystem.Door;

import Entities.CollidableEntity;
import Physics.Collider;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class Door extends CollidableEntity{
    private Integer doorID;

    public Door(Scene s) {
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

    public Integer getDoorID() {
        return doorID;
    }

    public void setDoorID(Integer doorID) {
        this.doorID = doorID;
    }
    
}
