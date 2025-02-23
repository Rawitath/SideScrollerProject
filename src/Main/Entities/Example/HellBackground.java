/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Example;

import Datas.Vector2;
import Entities.SpriteEntity;
import Scenes.Scene;
import Scenes.SceneManager;
import Utilities.FileReader;
import java.awt.event.KeyEvent;
import Inputs.KeyControlable;

/**
 *
 * @author GA_IA
 */
public class HellBackground extends SpriteEntity implements KeyControlable{
    
    private Vector2 direction;
    private float speed = 18f;
    private float upSpeed = 0f;
    private boolean lucy;

    public HellBackground(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/hell.jpg"));
        setPixelRatio(0.1f);
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
    
    @Override
    public void onKeyPressed(KeyEvent e, int keyCode) {
        if(keyCode == KeyEvent.VK_D){
            direction = Vector2.left();
            
        }
        if(keyCode == KeyEvent.VK_A){
            direction = Vector2.right();
        }
        if(keyCode == KeyEvent.VK_SPACE){
            if(lucy){
                upSpeed = 80f;
            }
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e, int keyCode) {
        if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_A){
            direction = Vector2.zero();
        }
    }

    @Override
    public void onKeyTyped(KeyEvent e, int keyCode) {
        
    }
}
