/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.UI;

import Datas.Vector2;
import Datas.Vector2Int;
import Scenes.Scene;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public class UISliderBar extends UIProgressBar{

    private Vector2 barEnd;
    public UISliderBar(Scene s) {
        super(s);
    }

    public Vector2 getBarEnd() {
        return new Vector2((getPosition().getX() - getScale().getX() / 2) + getLocalScale().getX() * getFill().getValue(), getPosition().getY());
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
    
}
