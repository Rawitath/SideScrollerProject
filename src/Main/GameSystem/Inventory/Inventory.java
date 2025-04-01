/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.GameSystem.Inventory;

/**
 *
 * @author user
 */


public class Inventory {
    private InventoryItem[] slots;
    private int selectedSlot = 0;
    private int itemCount = 0;

    public Inventory(int size){
        slots = new InventoryItem[size];
    }
    public Inventory() {
        this(9);
    }

    public int getSize() {
        return slots.length;
    }
    
    public boolean addItem(InventoryItem item) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) { 
                slots[i] = item;
                itemCount++;
                return true;
            }
        }
        return false;
    }

    public void removeItem(int index) {
        if (index >= 0 && index < slots.length) {
            if(slots[index] != null){
                itemCount--;
            }
            slots[index] = null;
        }
    }

    public void scroll(int scrollDirection) {
        //selection method coming soon
        if (selectedSlot + scrollDirection < 0){
            selectedSlot = slots.length - 1;
        }
        else if (selectedSlot + scrollDirection >= slots.length){
            selectedSlot = 0;
        }
        else{
            selectedSlot += scrollDirection;
        }
    }

    public void useSelectedItem() {
        if (slots[selectedSlot] != null) {
            System.out.println("Used: " + slots[selectedSlot].getName());
            removeItem(selectedSlot);
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
    public int getItemCount(){
        return itemCount;
    }
}
