/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities;

import Datas.Vector2;
import Entities.UI.UIButton;
import Entities.UI.UIText;
import Scenes.Scene;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public class MenuButton extends UIButton implements InButtonGroup{

    protected UIText buttonText;
    private MenuController controller;
    private int buttonIndex;
    
    public MenuButton(Scene s, MenuController controller) {
        super(s);
        this.controller = controller;
        setReleasedImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        setHoverImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        setPressedImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        setScale(new Vector2(300, 100));
        setPosition(new Vector2(305, 0));
        
        buttonText = new TextButton(s);
        addChild(buttonText);
        
        buttonText.setSize(82);
        buttonText.setHorizontalAlignment(UIText.LEFT);
        buttonText.setScreenAnchor(LEFT);
        
        controller.addButton(this);
        buttonIndex = controller.getAllButtons().size() - 1;
    }

    public MenuController getController() {
        return controller;
    }
    
    @Override
    public void onButtonClicked() {
        
    }

    @Override
    public void onButtonPressed() {
        controller.getGroup().setIsOpaque(false);
    }

    @Override
    public void onButtonReleased() {

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

    @Override
    public void onButtonHovered() {
        if(controller.getSelectedButton() != buttonIndex){
            controller.setSelectedButton(buttonIndex);
        }
    }

    @Override
    public void fadeIn(float alpha) {
        setAlpha(alpha);
        buttonText.setColor(new Color(buttonText.getColor().getRed() / 255f, buttonText.getColor().getGreen() / 255f, buttonText.getColor().getBlue() / 255f, alpha));
    }

    @Override
    public void fadeOut(float alpha) {
        setAlpha(alpha);
        buttonText.setColor(new Color(buttonText.getColor().getRed() / 255f, buttonText.getColor().getGreen() / 255f, buttonText.getColor().getBlue() / 255f, alpha));
    }
    
}
