/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main;

import Datas.Vector2;
import Entities.UI.UIEntity;
import Main.GameSystem.Inventory.Inventory;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class LucyUISet extends UIEntity{

    private HeartFrame heartFrame;
    private InventoryDisplay inventory;
    
    public LucyUISet(Scene s, Inventory inv) {
        super(s);
        setScale(Vector2.one().multiply(s.getUIView().getReferenceResolution()));
        
        heartFrame = new HeartFrame(s);
        addChild(heartFrame);
        heartFrame.setScale(new Vector2(1, 1));
        heartFrame.setLocalPosition(new Vector2(100, -80));
        heartFrame.setScreenAnchor(TOP_LEFT);
        
        inventory = new InventoryDisplay(s, inv);
        addChild(inventory);
        inventory.setScreenAnchor(BOTTOM_LEFT);
        inventory.setScale(new Vector2(1, 1));
        inventory.setLocalPosition(new Vector2(670, 80));
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

    public HeartFrame getHeartFrame() {
        return heartFrame;
    }

    public void setHeartFrame(HeartFrame heartFrame) {
        this.heartFrame = heartFrame;
    }

    public InventoryDisplay getInventory() {
        return inventory;
    }

    public void setInventory(InventoryDisplay inventory) {
        this.inventory = inventory;
    }
    
}
