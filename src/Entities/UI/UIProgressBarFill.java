/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.UI;

import Datas.Vector2;
import Datas.Vector2Int;
import Inputs.KeyControlable;
import Physics.Time;
import Scenes.Scene;
import Utilities.FileReader;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public class UIProgressBarFill extends UIImage{
    private float value;
    private Float keepValue;
    public UIProgressBarFill(Scene s) {
        super(s);
        setImage(FileReader.readImage("res/default/whitesquare.png"));
        value = 1f;
        setClipping(true);
        setClippingPosition(new Vector2Int(0,0));
    }

    @Override
    public void onAddedToParent() {
        super.onAddedToParent();
        updateBar(value);
    }
    
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        if(value > 1f){
            value = 1f;
        }
        else if(value < 0){
            value = 0.00001f;
        }
        this.value = value;
    }
    
    @Override
    public void start() {
        
    }
    @Override
    public void update() {
        updateBar(value);
    }
    private void updateBar(float value){
        if(keepValue == null){
            keepValue = value;
        }
        else{
            if(value > keepValue - 0.00001f && value < keepValue + 0.00001f){
            return;
            }
        }
        setClippingSize(new Vector2Int((int) (getImage().getWidth() * value), getImage().getHeight()));
        setLocalScale(new Vector2(getLocalScale().getX() / keepValue * value, getLocalScale().getY()));
        setLocalPosition(new Vector2(-getParent().getLocalScale().getX() / 2 * (1 - value), getLocalPosition().getY()));
        keepValue = value;
    }
    @Override
    public void fixedUpdate() {
        
    }
    
}
