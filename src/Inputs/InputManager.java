/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author GA_IA
 */
public class InputManager implements KeyListener{
    private List<KeyControlable> keyCons;
    
    public InputManager(){
        keyCons = new CopyOnWriteArrayList<>();
    }
    public void addKeyControlable(KeyControlable k){
        keyCons.add(k);
    }
    public void removeKeyControlable(KeyControlable k){
        keyCons.remove(k);
    }
    public void clearKeyControlable(){
        keyCons.clear();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        for(var k : keyCons){
            k.onKeyTyped(e.getKeyCode());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for(var k : keyCons){
            k.onKeyPressed(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(var k : keyCons){
            k.onKeyReleased(e.getKeyCode());
        }
    }
    
}
