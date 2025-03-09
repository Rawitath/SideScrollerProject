/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Example;

import Datas.Vector2;
import Entities.UI.UIEntity;
import Entities.UI.UIImage;
import Main.GameSystem.Inventory.Inventory;
import Main.GameSystem.Inventory.InventoryItem;
import Main.Entities.Example.Lucy;
import Scenes.Scene;
import Utilities.FileReader;
import java.awt.Graphics;

/**
 *
 * @author GA_IA
 */
public class InventoryBar extends UIEntity{

    private UIImage selector;
    
    Inventory inventory;
    InventorySlot[] inventorySlots;
    
    public InventoryBar(Scene s) {
        super(s);
        selector = new UIImageDisplay(s);
        addChild(selector);
        selector.setImage(FileReader.readImage("res/default/framesquare.png"));
    }

    @Override
    public void start() {
        
        Lucy lucy = (Lucy) getScene().getEntity("Lucy");

        if (lucy == null) {
            System.err.println("Error: Lucy entity not found in the scene!");
            return;
        }
        inventory = lucy.getInventory();
        
        drawHotbar(); //Inventory update
    }

    @Override
    public void update() {
        selector.setPosition(inventorySlots[inventory.getSelectedSlot()].getPosition());
        
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
    public void drawHotbar() { //Big Inventory update
        int slotSize = 80;
        int slotSpacing = 5;
        int totalWidth = (slotSize + slotSpacing) * 9 - slotSpacing;
        int startX = -totalWidth / 2;
        int startY = -(getScene().getUIView().getReferenceResolution().getY() / 2 - slotSize - 20);
        inventorySlots = new InventorySlot[inventory.getSize()];
        for (int i = 0; i < inventorySlots.length; i++) {
            int x = startX + i * (slotSize + slotSpacing);
            InventorySlot e = new InventorySlot(getScene());
            addChild(e);
            e.setLocalPosition(new Vector2(x, startY));
            e.setLocalScale(Vector2.one().multiply(slotSize));
            inventorySlots[i] = e;
        }
    }
        
}
