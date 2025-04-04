/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.GameSystem.Inventory;

/**
 *
 * @author user
 */
import java.awt.image.BufferedImage;

public class InventoryItem{
    private int itemID;
    private String name;
    private int quantity;
    private BufferedImage icon;
    

    public InventoryItem(String name, int quantity, BufferedImage icon) {
        this.name = name;
        this.quantity = quantity;
        this.icon = icon;
    }

    public int getItemID() {
        return itemID;
    }

    protected void setItemID(int itemID) {
        this.itemID = itemID;
    }
    
    
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public BufferedImage getIcon() { return icon; }
    protected void setIcon(BufferedImage icon) {this.icon = icon;}
    public void increaseQuantity(int amount) { this.quantity += amount; }
    public void decreaseQuantity(int amount) { 
        this.quantity -= amount; 
        if (this.quantity < 0) this.quantity = 0;
    }
}