/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Main.GameSystem.Door.Door;
import Main.GameSystem.Door.Key;
import Saves.SaveManager;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class DoorRever extends Door{
    
    private Lever rever;
    
    public DoorRever(Scene s, Lever rever) {
        super(s);
        doorID = 2;
        this.rever = rever;
    }

    @Override
    public void start() {
        super.start();
        if(SaveManager.getInstance().getCurrentSave().getUnlockedDoors().contains(doorID)){
            rever.setLever(true);
            getScene().removeEntity(this);
        }
    }

    @Override
    public void update() {
        if(rever.isIsOn() && !isIsOpen()){
            open(new Key("Rever", rever.getLeverID(), 0));
        }
        super.update();
    }
    
}
