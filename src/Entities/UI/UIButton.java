/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.UI;

import Inputs.MouseControlable;
import Scenes.Scene;
import Utilities.FileReader;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public abstract class UIButton extends UIImage implements MouseControlable{
    
    private int buttonState;
    public static final int RELEASED = 0;
    public static final int HOVER = 1;
    public static final int PRESSED = 2;
    public static final int CLICKED = 3;

    private BufferedImage releasedImage;
    private BufferedImage hoverImage;
    private BufferedImage pressedImage;

    public int getButtonState() {
        return buttonState;
    }
    public UIButton(Scene s) {
        super(s);
        releasedImage = FileReader.readImage("res/default/whitesquare.png");
        hoverImage = FileReader.readImage("res/default/blacksquare.png");
        pressedImage = FileReader.readImage("res/default/blacksquare.png");
        setImage(releasedImage);
    }

    public BufferedImage getReleasedImage() {
        return releasedImage;
    }

    public void setReleasedImage(BufferedImage releasedImage) {
        this.releasedImage = releasedImage;
        setImage(releasedImage);
    }

    public BufferedImage getHoverImage() {
        return hoverImage;
    }

    public void setHoverImage(BufferedImage hoverImage) {
        this.hoverImage = hoverImage;
    }

    public BufferedImage getPressedImage() {
        return pressedImage;
    }

    public void setPressedImage(BufferedImage pressedImage) {
        this.pressedImage = pressedImage;
    }
    
    private boolean isMouseOnButton(MouseEvent e){
        return getPositionOnScreen() != null && (e.getPoint().x >= getPositionOnScreen().getX() &&
           e.getPoint().x <= getPositionOnScreen().getX() + getScreenSize().getX() &&
           e.getPoint().y >= getPositionOnScreen().getY() &&
           e.getPoint().y <= getPositionOnScreen().getY() + getScreenSize().getY());
    }
    public abstract void onButtonClicked();
    public abstract void onButtonHovered();
    public abstract void onButtonPressed();
    public abstract void onButtonReleased();
    @Override
    public void onMouseClicked(MouseEvent e) {
        super.onMouseClicked(e);
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        super.onMousePressed(e);
        if(isMouseOnButton(e)){
            buttonState = PRESSED;
            onButtonPressed();
            setImage(pressedImage);
        }
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        super.onMouseReleased(e);
        if(isMouseOnButton(e)){
            buttonState = CLICKED;
            onButtonClicked();
        }
        if(buttonState != RELEASED){
            buttonState = RELEASED;
            onButtonReleased();
        }
        buttonState = RELEASED;
        setImage(releasedImage);
    }

    @Override
    public void onMouseEntered(MouseEvent e) {
        super.onMouseEntered(e);
    }

    @Override
    public void onMouseExited(MouseEvent e) {
        super.onMouseExited(e);
    }

    @Override
    public void onMouseMoved(MouseEvent e) {
        super.onMouseMoved(e);
        if(isMouseOnButton(e)){
            buttonState = HOVER;
            setImage(hoverImage);
            onButtonHovered();
        }
        else{
            buttonState = RELEASED;
            setImage(releasedImage);
        }
    }
    
}
