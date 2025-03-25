/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Main;

import Datas.Vector2;
import Inputs.KeyControlable;
import Main.GameSystem.Inventory.Inventory;
import Main.GameSystem.Inventory.InventoryItem;
import Scenes.Scene;
import Scenes.SceneManager;
import Utilities.FileReader;
import java.awt.event.KeyEvent;

/**
 *
 * @author GA_IA
 */
public class Lucy extends PhysicableEntity implements KeyControlable{
    
    public Lucy(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/lucypixel.png"));
    }
    
    @Override
    public void onKeyPressed(KeyEvent e, int keyCode) {
        if(keyCode == KeyEvent.VK_D){
            if(getVelocity().getX() < 0){
                setVelocity(Vector2.zero());
            }
            else{
                setVelocity(Vector2.right());
                setFlip(Vector2.negativeX());
            }
        }
        if(keyCode == KeyEvent.VK_A){
            if(getVelocity().getX() > 0){
                setVelocity(Vector2.zero());
            }
            else{
                setVelocity(Vector2.left());
                setFlip(Vector2.one());
            }
        }
        if(keyCode == KeyEvent.VK_SPACE){
            if(isGrounded()){
                addVelocity(Vector2.up(), -80);
            }
        }
    }
    
    @Override
    public void onKeyReleased(KeyEvent e, int keyCode) {
        if(getVelocity().getX() == 0){
            if(keyCode == KeyEvent.VK_D){
                setVelocity(Vector2.left());
                setFlip(Vector2.one());
            }
            else if(keyCode == KeyEvent.VK_A){
                setVelocity(Vector2.right());
                setFlip(Vector2.negativeX());
            }
            return;
        }
        if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_A){
            setVelocity(Vector2.zero());
        }
    }

    @Override
    public void onKeyTyped(KeyEvent e, int keyCode) {
        
    }
}
