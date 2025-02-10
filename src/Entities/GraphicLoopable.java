/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Entities;

import Datas.Vector2;
import java.awt.Graphics;

/**
 *
 * @author GA_IA
 */
public interface GraphicLoopable {
    public abstract void draw(Graphics g, Vector2 posOffset, Vector2 scaleOffset);
}
