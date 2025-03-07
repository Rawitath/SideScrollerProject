/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Example;

import Datas.Vector2;
import Entities.UI.UIImage;
import Main.Entities.Example.InventoryItem;
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
        setImage(FileReader.readImage("res/default/framesquare.png"));
        itemImage = new UIImageDisplay(s);
        addChild(itemImage);
        itemImage.setLocalScale(Vector2.one().multiply(0.8f));
    }

    public InventoryItem getItem() {
        return item;
    }

    public void setItem(InventoryItem item) {
        this.item = item;
        itemImage.setImage(item.getIcon());
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
