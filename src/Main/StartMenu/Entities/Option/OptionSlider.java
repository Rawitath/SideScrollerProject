/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Option;

import Datas.Vector2;
import Entities.UI.UISlider;
import Main.StartMenu.Entities.Fadable;
import Scenes.Scene;
import Utilities.FileReader;
import java.awt.Color;

/**
 *
 * @author GA_IA
 */
public class OptionSlider extends UISlider implements Fadable{

    public OptionSlider(Scene s) {
        super(s);
//        this.getBar().setScale(new Vector2(50,1));
//        this.getBar().setColor(new Color(1.0f, 0.93f, 0.55f, 1.0f)); I can't do this.
        this.getHandle().setReleasedImage(FileReader.readImage("res/game/settingsmenu/huajai.png"));
        this.getHandle().setHoverImage(FileReader.readImage("res/game/settingsmenu/huajai.png"));
        this.getHandle().setPressedImage(FileReader.readImage("res/game/settingsmenu/huajai.png"));
//        this.getHandle().setScale(new Vector2(5,10));
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
        getBar().setAlpha(alpha);
        getHandle().setAlpha(alpha);
    }
    
}
