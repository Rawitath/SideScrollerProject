/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities;

import Datas.Vector2;
import Entities.SpriteEntity;
import Inputs.KeyControlable;
import Scenes.Scene;
import Utilities.FileReader;
import java.awt.event.KeyEvent;

/**
 *
 * @author GA_IA
 */
public class Mutsuki extends SpriteEntity implements KeyControlable{

    private Vector2 direction;
    private float speed = 0.1f;
    
    public Mutsuki(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/mutsuki.png"));
        direction = Vector2.zero();
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }
    @Override
    public void fixedUpdate() {
        setPosition(getPosition().translate(direction, speed));
    }

    @Override
    public void onKeyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_D){
            direction = Vector2.right();
        }
        if(keyCode == KeyEvent.VK_A){
            direction = Vector2.left();
        }
    }

    @Override
    public void onKeyReleased(int keyCode) {
        if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_A){
            direction = Vector2.zero();
        }
    }

    @Override
    public void onKeyTyped(int keyCode) {
        
    }
    
}
