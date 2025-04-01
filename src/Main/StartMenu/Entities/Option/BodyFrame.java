/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Option;

import Datas.Vector2;
import Entities.UI.UIImage;
import Main.StartMenu.Entities.Fadable;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class BodyFrame extends UIImage implements Fadable{

    private InnerBodyFrame bodyFrame;
    
    public BodyFrame(Scene s) {
        super(s);
        setImage(FileReader.readImage("res/game/settingsmenu/Border_2.png"));
        setScale(new Vector2(getImage().getWidth(), getImage().getHeight()));
        
        bodyFrame = new InnerBodyFrame(s);
        addChild(bodyFrame);
        bodyFrame.setScale(new Vector2(bodyFrame.getImage().getWidth(), bodyFrame.getImage().getHeight()));
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
