/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.GameSystem.Door;

import Main.GameSystem.Inventory.InventoryItem;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public class Key extends InventoryItem{
    
    private Integer doorID;
    
    public Key(String name, int quantity, BufferedImage icon) {
        super(name, quantity, icon);
    }

    public Integer getDoorID() {
        return doorID;
    }

    public void setDoorID(Integer doorID) {
        this.doorID = doorID;
    }
    
    
}
