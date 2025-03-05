/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.UI;

import Datas.Vector2;
import Scenes.Scene;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author GA_IA
 */
public class UISliderHandle extends UIButton{
    private boolean using;
    float mousePosition;
    float value;
    Float keepValue = null;
    
    public boolean isUsing(){
        return using;
    }
    
    public void setHandlePosition(Vector2 handlePosition) {
        setPosition(handlePosition);
        float posX = getLocalPosition().getX();
            UISlider parent = (UISlider)getParent();
            if(posX > parent.getBar().getLocalScale().getX() / 2f){
                posX = parent.getBar().getLocalScale().getX() / 2f;
            }
            if(posX < -parent.getBar().getLocalScale().getX() / 2f){
                posX = -parent.getBar().getLocalScale().getX() / 2;
            }
            value = (posX + parent.getBar().getLocalScale().getX() / 2f) / (parent.getBar().getLocalScale().getX() / 2f + parent.getBar().getLocalScale().getX() / 2f);

    }
    
    
    public UISliderHandle(Scene s) {
        super(s);
        mousePosition = MouseInfo.getPointerInfo().getLocation().x;
    }

    public float getValue() {
        return value;
    }
    
    @Override
    public void onAddedToParent() {
        super.onAddedToParent();
    }
//    public void setHandlePosition(float value){
//        this.value = value;
//        UISlider parent = (UISlider) getParent();
//        setLocalPosition(new Vector2(value *(parent.getBar().getLocalScale().getX() / 2f-parent.getBar().getLocalScale().getX() / 2f)+parent.getBar().getLocalScale().getX() / 2f, getLocalPosition().getY()));
//    }
    @Override
    public void onButtonClicked() {

    }

    @Override
    public void onButtonPressed() {
        using = true;
    }
    @Override
    public void onButtonReleased() {
        using = false;
    }
    
    @Override
    public void start() {
        
    }
    @Override
    public void update() {
        int refX = getScene().getUIView().getReferenceResolution().getX();
        int screenX = getScene().getUIView().getScreenSize().width;
        float ans = MouseInfo.getPointerInfo().getLocation().x * ((float)refX / (float)screenX);
        int delta = (int) (ans - mousePosition);
        if(using){
            float posX = getLocalPosition().getX() + delta;
            UISlider parent = (UISlider)getParent();
            if(posX > parent.getBar().getLocalScale().getX() / 2f){
                posX = parent.getBar().getLocalScale().getX() / 2f;
            }
            if(posX < -parent.getBar().getLocalScale().getX() / 2f){
                posX = -parent.getBar().getLocalScale().getX() / 2;
            }
            setLocalPosition(new Vector2(posX, getLocalPosition().getY()));
            value = (posX + parent.getBar().getLocalScale().getX() / 2f) / (parent.getBar().getLocalScale().getX() / 2f + parent.getBar().getLocalScale().getX() / 2f);
            
            if(keepValue == null){
            keepValue = value;
        }
        else{
            if(value > keepValue - 0.00001f && value < keepValue + 0.00001f){
            return;
            }
        }
        parent.setValue(value * (parent.getMax() + parent.getMin()) - parent.getMin());
        keepValue = value;
        }
        mousePosition = ans;
        
        
    }

    @Override
    public void fixedUpdate() {
        
    }

    @Override
    public void onMouseWheelMoved(MouseWheelEvent e) {

    }
}
