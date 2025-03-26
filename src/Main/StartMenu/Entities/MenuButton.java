/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities;

import Datas.Vector2;
import Entities.UI.UIButton;
import Entities.UI.UIText;
import Scenes.Scene;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public class MenuButton extends UIButton{

    protected UIText buttonText;
    public MenuButton(Scene s) {
        super(s);
        buttonText = new TextButton(s);
        addChild(buttonText);
        setReleasedImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        
        buttonText.setSize(82);
        buttonText.setScreenAnchor(LEFT);
        buttonText.setLocalPosition(new Vector2(0, 0));
        
        setScale(new Vector2(200, 100));
    }

    @Override
    public void onButtonClicked() {
        
    }

    @Override
    public void onButtonPressed() {

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
    
}
