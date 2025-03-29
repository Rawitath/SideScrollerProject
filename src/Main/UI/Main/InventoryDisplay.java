/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main;

import Datas.Vector2;
import Entities.UI.UIEntity;
import Main.GameSystem.Inventory.Inventory;
import Main.GameSystem.Inventory.InventoryItem;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class InventoryDisplay extends UIEntity{
    
    private Inventory inventory;
    private InventorySlot[] inventorySlots;
    
    private int slotSize = 100;
    private int slotSpacing = 30;

    public InventoryDisplay(Scene s, Inventory inventory) {
        super(s);
        this.inventory = inventory;
    }
    
    @Override
    public void start() {
        drawHotbar(); //Inventory update
    }

    @Override
    public void update() {
//        selector.setPosition(inventorySlots[inventory.getSelectedSlot()].getPosition());
        
        for (int i = 0; i < inventory.getSize(); i++) {
            InventoryItem item = inventory.getItems()[i];
            if (item != null) {
                inventorySlots[i].setItem(item);
            }
            else{
                inventorySlots[i].setItem(null);
            }
        }
    }

    @Override
    public void fixedUpdate() {

    }

    public int getSlotSize() {
        return slotSize;
    }

    public void setSlotSize(int slotSize) {
        this.slotSize = slotSize;
    }

    public int getSlotSpacing() {
        return slotSpacing;
    }

    public void setSlotSpacing(int slotSpacing) {
        this.slotSpacing = slotSpacing;
    }
    
    public void drawHotbar() { //Big Inventory update @Contribute by Fluke(user)
        int totalWidth = (slotSize + slotSpacing) * 9 - slotSpacing;
        int startX = -totalWidth / 2;
        int startY = 0;//-(getScene().getUIView().getReferenceResolution().getY() / 2 - slotSize - 20);
        inventorySlots = new InventorySlot[inventory.getSize()];
        for (int i = 0; i < inventorySlots.length; i++) {
            int x = startX + i * (slotSize + slotSpacing);
            InventorySlot e = new InventorySlot(getScene());
            addChild(e);
            e.setScreenAnchor(LEFT);
            e.setLocalPosition(new Vector2(x, startY));
            e.setLocalScale(Vector2.one().multiply(slotSize));
            inventorySlots[i] = e;
        }
    }
}
