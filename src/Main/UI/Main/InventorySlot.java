/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main;

import Datas.Vector2;
import Entities.UI.UIImage;
import Main.GameSystem.Inventory.InventoryItem;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class InventorySlot extends UIImage{

    private UIImage itemImage;
    private InventoryItem item;
    
    public InventorySlot(Scene s) {
        super(s);
        setImage(FileReader.readImage("res/game/hud/slot.png"));
        itemImage = new InventorySlotImage(s);
        addChild(itemImage);
        itemImage.setScale(getScale().multiply(0.5f));
    }
    
    public InventoryItem getItem() {
        return item;
    }

    public void setItem(InventoryItem item) {
        this.item = item;
        if(item != null){
            itemImage.setImage(item.getIcon());
            return;
        }
        itemImage.setImage(null);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {

    }
    
}
