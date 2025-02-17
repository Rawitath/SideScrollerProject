/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.UI;

import Inputs.MouseControlable;
import Scenes.Scene;
import java.awt.event.MouseEvent;

/**
 *
 * @author GA_IA
 */
public abstract class UIButton extends UIImage implements MouseControlable{
    
    private int buttonState;
    public static final int RELEASED = 0;
    public static final int PRESSED = 1;
    public static final int CLICKED = 2;

    public int getButtonState() {
        return buttonState;
    }
    public UIButton(Scene s) {
        super(s);
    }
    private boolean isMouseOnButton(MouseEvent e){
        return e.getPoint().x >= getScreenPoint().getX() &&
           e.getPoint().x <= getScreenPoint().getX() + getScreenSize().getX() &&
           e.getPoint().y >= getScreenPoint().getY() &&
           e.getPoint().y <= getScreenPoint().getY() + getScreenSize().getY();
    }
    public abstract void onButtonClicked();
    public abstract void onButtonPressed();
    @Override
    public void onMouseClicked(MouseEvent e) {
        
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        if(isMouseOnButton(e)){
            buttonState = PRESSED;
            onButtonPressed();
        }
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        if(isMouseOnButton(e)){
            buttonState = CLICKED;
            onButtonClicked();
            return;
        }
        buttonState = RELEASED;
    }

    @Override
    public void onMouseEntered(MouseEvent e) {
        
    }

    @Override
    public void onMouseExited(MouseEvent e) {

    }
}
