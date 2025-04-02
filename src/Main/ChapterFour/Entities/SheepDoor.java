/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Entities;

import Main.GameSystem.Door.Door;
import Main.GameSystem.Door.Key;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class SheepDoor extends Door{
    
    private ChefBoss c;
    
    public SheepDoor(Scene s, ChefBoss c) {
        super(s);
        this.c = c;
        doorID = 5;
        
    }

    @Override
    public void update() {
        if(c.getHealth() < 1 && !isIsOpen()){
            open(new Key("Chef", 5, 0));
        }
        super.update();
    }
   
}
