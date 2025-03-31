/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.GameSystem.Door;

import Main.GameSystem.Inventory.InventoryItem;
import Utilities.FileReader;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public class Key extends InventoryItem{
    
    protected Integer keyID;
    private int keyImageNum = 0;
    
    public Key(String name, Integer keyID, int keyType) {
        super(name, 1, new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        BufferedImage keyImage = FileReader.readImage("res/game/animation/chapter/redkey.png");
        switch(keyType){
            default:
                keyImage = keyImage.getSubimage(0, 0, 374, 374);
                break;
        }
        setIcon(keyImage);
        this.keyID = keyID;
    }

    public Integer getKeyID() {
        return keyID;
    }
}
