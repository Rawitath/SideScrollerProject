/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Load;

import Entities.UI.UIText;
import Scenes.Scene;
import java.awt.Font;

/**
 *
 * @author nirawith2548gmail.com
 */
public class PlayTime extends UIText{
    
    public PlayTime(Scene s){
        super(s);
        setFont("res/font/MOON Night DEMO.otf", Font.TRUETYPE_FONT);
        this.setSize(43);
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
