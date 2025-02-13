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
    private Vector2 anchor;
    public UIEntity(Scene s) {
        super(s);
    }

    @Override
    public void draw(Graphics g, Vector2 posOffset, Vector2 scaleOffset, float zoom) {

    }
    
}
