/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Example;

import Datas.Vector2;
import Entities.UI.UIEntity;
import Entities.UI.UIImage;
import Main.Entities.Example.Inventory;
import Main.Entities.Example.InventoryItem;
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
    public InventoryBar(Scene s) {
        super(s);
        selector = new UIImageDisplay(s);
        addChild(selector);
        selector.setImage(FileReader.readImage("res/default/framesquare.png"));
    }

    @Override
    public void start() {
        drawHotbar(); //Inventory update
    }

    @Override
    public void update() {

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

        Lucy lucy = (Lucy) getScene().getEntity("Lucy");

        if (lucy == null) {
            System.err.println("Error: Lucy entity not found in the scene!");
            return;
        }

        Inventory inventory = lucy.getInventory();


        for (int i = 0; i < 9; i++) {
            int x = startX + i * (slotSize + slotSpacing);
            InventorySlot e = new InventorySlot(getScene());
            addChild(e);
            e.setPosition(new Vector2(x, startY));
            e.setScale(Vector2.one().multiply(slotSize));
            InventoryItem item = inventory.getItems()[i];
//            if (item != null) {
//                g.drawImage(item.getIcon(), x + 5, startY + 5, slotSize - 10, slotSize - 10, null);
//            }
//
            if (i == inventory.getSelectedSlot()) {
                selector.setPosition(new Vector2(x - 2, startY - 2));
                selector.setScale(new Vector2(slotSize * 1.2f, slotSize * 1.2f));
            }
        }
    }
        
}
