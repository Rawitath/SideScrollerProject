/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Start;

import Datas.Vector2;
import Entities.UI.UIImage;
import Main.StartMenu.Entities.Fadable;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class ButtonFrame extends UIImage implements Fadable{
    
    public ButtonFrame(Scene s) {
        super(s);
        setImage(FileReader.readImage("res/game/startmenu/Button_Border.png"));
        setScale(new Vector2(getImage().getWidth() + 12, getImage().getHeight() + 5));
        setScale(getScale().multiply(1.29f));
        
        setAnchor(new Vector2(-15, 20));
        
        //setScreenAnchor(TOP_LEFT);
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
    public void fade(float alpha) {
        setAlpha(alpha);
    }
    
}
