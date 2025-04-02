/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.UI;

import Datas.Vector2;
import Scenes.Scene;
import Utilities.FileReader;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public abstract class UIProgressBar extends UIImage{
    private float value, min, max;
    private UIProgressBarFill fill;

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    @Override
    public void setAlpha(float alpha) {
        super.setAlpha(alpha);
        fill.setAlpha(alpha);
    }
    
    public void setMax(float max) {
        this.max = max;
    }

    public float getValue() {
        return value;
    }
    
    public UIProgressBarFill getFill() {
        return fill;
    }
    
    public UIProgressBar(Scene s) {
        super(s);
        value = 1f;
        min = 0f;
        max = 1f;
        
        setImage(FileReader.readImage("res/default/blacksquare.png"));
        fill = new UIProgressBarFill(s);
        addChild(fill);
        fill.setPosition(getPosition());
        fill.setScale(getScale());
        
        setScale(new Vector2(500,50));
    }
    public void setValue(float value) {
        if(value > max){
            System.err.println(getName()+"'s value is exceeding max. The value will be set to max.");
            value = max;
        }
        if(value < min){
            System.err.println(getName()+"'s value is preceeding min. The value will be set to min.");
            value = min;
        }
        this.value = value;
        //Fixed! by Libero Fluke (Cheese)
        fill.setValue((this.value - min) / (max - min));
    }
}
