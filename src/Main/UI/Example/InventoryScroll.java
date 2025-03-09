/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Example;

import Datas.Vector2;
import Entities.UI.UIEntity;
import Entities.UI.UIImage;
import Main.Entities.Example.Lucy;
import Main.GameSystem.Inventory.Inventory;
import Main.GameSystem.Inventory.InventoryItem;
import Physics.Time;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class InventoryScroll extends UIEntity{

    private UIImage selector;
    
    Inventory inventory;
    InventorySlot[] inventorySlots;
    
    float degreePerSlot;
    
    public InventoryScroll(Scene s) {
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
        selector.setPosition(inventorySlots[0].getPosition());
    }
    private int s = 0;
    private float currentDegree = 0;
    @Override
    public void update() {
        if(s != inventory.getSelectedSlot()){
            if(currentDegree < degreePerSlot * (getScale().getX() / 2) * 500){
                for (int i = 0; i < inventorySlots.length; i++) {
                    float x = (float) Math.cos(Math.toRadians(degreePerSlot * i));
                    float y = (float) Math.sin(Math.toRadians(degreePerSlot * i));
                    x = x * ((getScale().getX() / 2) * 500);
                    y = y * ((getScale().getY() / 2) * 500);
                    inventorySlots[i].setLocalPosition(inventorySlots[i].getLocalPosition().translate(new Vector2(x, y), Time.deltaTime()));
//                    inventorySlots[i].setLocalPosition(inventorySlots[i].getLocalPosition().translate(Vector2.down(), 1f * Time.deltaTime()));
                    currentDegree += 1f;
                }
            }
            else{
                currentDegree = 0;
                s = inventory.getSelectedSlot();
            }   
        }
        
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
        int startY = 0;
        inventorySlots = new InventorySlot[inventory.getSize()];
        degreePerSlot = 360 / inventorySlots.length;
        for (int i = 0; i < inventorySlots.length; i++) {
            float x = (float) Math.cos(Math.toRadians(degreePerSlot * i));
            float y = (float) Math.sin(Math.toRadians(degreePerSlot * i));
            x = x * ((getScale().getX() / 2) * 500);
            y = y * ((getScale().getY() / 2) * 500);
            InventorySlot e = new InventorySlot(getScene());
            addChild(e);
            e.setLocalPosition(new Vector2(x, y));
            e.setLocalScale(Vector2.one().multiply(slotSize));
            inventorySlots[i] = e;
        }
    }
}
