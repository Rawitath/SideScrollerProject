/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Inputs;

import java.awt.event.KeyEvent;

/**
 *
 * @author GA_IA
 */
public interface KeyControlable {
    public abstract void onKeyPressed(int keyCode);
    public abstract void onKeyReleased(int keyCode);
    public abstract void onKeyTyped(int keyCode);
}
