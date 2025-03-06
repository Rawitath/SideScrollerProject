/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Example;

/**
 *
 * @author user
 */

import java.awt.event.MouseWheelEvent;
import java.util.Arrays;

public class Inventory {
    private InventoryItem[] slots;
    private int selectedSlot = 0;

    public Inventory() {
        slots = new InventoryItem[9];
    }

    public boolean addItem(InventoryItem item) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) { 
                slots[i] = item;
                return true;
            }
        }
        return false;
    }

    public void removeItem(int index) {
        if (index >= 0 && index < slots.length) {
            slots[index] = null;
        }
    }

    public void scroll(MouseWheelEvent e) {
        //selection method coming soon
        
        if (selectedSlot < 0) selectedSlot = slots.length - 1;
        if (selectedSlot >= slots.length) selectedSlot = 0;
    }

    public void useSelectedItem() {
        if (slots[selectedSlot] != null) {
            System.out.println("Used: " + slots[selectedSlot].getName());
        }
    }

    public InventoryItem getSelectedItem() {
        return slots[selectedSlot];
    }

    public InventoryItem[] getItems() {
        return slots;
    }

    public int getSelectedSlot() {
        return selectedSlot;
    }
    
}
