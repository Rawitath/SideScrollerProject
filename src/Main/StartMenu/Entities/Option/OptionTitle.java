/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Option;

/**
 *
 * @author nirawith2548gmail.com
 */

import Datas.Vector2;
import static Entities.UI.UIEntity.BOTTOM_RIGHT;
import Entities.UI.UIText;
import Main.StartMenu.Entities.Fadable;
import Scenes.Scene;
import java.awt.Color;
import java.awt.Font;

public class OptionTitle extends UIText implements Fadable{
    
    public OptionTitle(Scene s){
        super(s);
        setFont("res/font/MOON Night DEMO.otf", Font.TRUETYPE_FONT);
        setColor(new Color(1.0f, 0.93f, 0.55f, 1.0f));
        setSize(82);
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
        setColor(new Color(getColor().getRed() / 255f, getColor().getGreen() / 255f, getColor().getBlue() / 255f, alpha));
    }
    
    
}
