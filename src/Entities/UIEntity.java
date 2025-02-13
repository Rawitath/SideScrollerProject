/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Datas.Vector2;
import Scenes.Scene;
import java.awt.Graphics;

/**
 *
 * @author GA_IA
 */
public abstract class UIEntity extends Entity{
    private Vector2 screenAnchor;
    public UIEntity(Scene s) {
        super(s);
        screenAnchor = new Vector2();
    }

    public Vector2 getScreenAnchor() {
        return screenAnchor;
    }

    public void setScreenAnchor(Vector2 screenAnchor) {
        this.screenAnchor = screenAnchor;
    }
    
    @Override
    public void draw(Graphics g, Vector2 posOffset, Vector2 scaleOffset, float zoom) {

    }
    
}
