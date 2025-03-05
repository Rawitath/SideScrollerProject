/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author GA_IA
 */
public class InputManager implements KeyListener, MouseListener, MouseWheelListener{
    private List<KeyControlable> keyCons;
    private List<MouseControlable> mouseCons;
    
    public InputManager(){
        keyCons = new CopyOnWriteArrayList<>();
        mouseCons = new CopyOnWriteArrayList<>();
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
    
    public void addMouseControlable(MouseControlable m){
        mouseCons.add(m);
    }
    public void removeMouseControlable(MouseControlable m){
        mouseCons.remove(m);
    }
    public void clearMouseControlable(){
        mouseCons.clear();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        for(var k : keyCons){
            k.onKeyTyped(e, e.getKeyCode());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for(var k : keyCons){
            k.onKeyPressed(e, e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(var k : keyCons){
            k.onKeyReleased(e, e.getKeyCode());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(var m : mouseCons){
           m.onMouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(var m : mouseCons){
            m.onMousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(var m : mouseCons){
           m.onMouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for(var m : mouseCons){
           m.onMouseEntered(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for(var m : mouseCons){
           m.onMouseExited(e);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        for(var m : mouseCons){
           m.onMouseWheelMoved(e);
        }
    }
}
