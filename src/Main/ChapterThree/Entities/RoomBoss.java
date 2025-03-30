/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Main.GameSystem.Door.Door;
import Main.GameSystem.Door.Key;
import Physics.Collider;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class RoomBoss extends Door{
    
    public RoomBoss(Scene s) {
        super(s);
        doorID = 4;
    }

    @Override
    public void onColliderEnter(Collider other) {
        setIsOpen(true);
    }

    
    
}
