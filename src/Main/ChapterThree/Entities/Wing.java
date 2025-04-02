/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Entities.CollidableEntity;
import Entities.Entity;
import Main.Entities.Main.Interactable;
import Main.Entities.Main.Lucy;
import Main.GameSystem.Inventory.InventoryItem;
import Main.GameSystem.Inventory.Obtainable;
import Physics.Collider;
import Physics.Time;
import Saves.SaveManager;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Wing extends CollidableEntity implements Interactable{

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
        if(SaveManager.getInstance().getCurrentSave().getWingAchived().equals(true)){
            getScene().removeEntity(this);
        }
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
        SaveManager.getInstance().getCurrentSave().setWingAchived(true);
        SaveManager.getInstance().getCurrentSave().setMaxJump(2);
        SaveManager.getInstance().saveCurrentSave();
        if(interactor instanceof Lucy l){
            l.refreshSave();
        }
    }
}
