/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Entities.CollidableEntity;
import Main.GameSystem.Inventory.InventoryItem;
import Main.GameSystem.Inventory.Obtainable;
import Physics.Collider;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Wing extends CollidableEntity{

    public Wing(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/main/wing.png"));
        setName("Wing");
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
    
}
