/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.UI;

import Datas.Vector2;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public abstract class UISlider extends UIEntity{
    private UISliderHandle handle;
    private UISliderBar bar;
    
    public UISlider(Scene s) {
        super(s);
        bar = new UISliderBar(s);
        handle = new UISliderHandle(s);
        addChild(bar);
        addChild(handle);
        
        bar.setLocalScale(new Vector2(500, 50));
        handle.setLocalScale(new Vector2(100, 100));
        handle.setHandlePosition(bar.getBarEnd());
    }

    public float getValue() {
        return bar.getValue();
    }

    public void setValue(float value) {
        bar.setValue(value);
        handle.setHandlePosition(bar.getBarEnd());
    }
    public float getMin() {
        return bar.getMin();
    }

    public void setMin(float min) {
        bar.setMin(min);
    }
    public float getMax() {
        return bar.getMax();
    }

    public void setMax(float max) {
        bar.setMax(max);
    }
    
    public UISliderHandle getHandle() {
        return handle;
    }

    public UISliderBar getBar() {
        return bar;
    }
    
}
