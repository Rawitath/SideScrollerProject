/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities;

import Entities.UI.UIText;
import Scenes.Scene;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author GA_IA
 */
public class TextButton extends UIText{

    public TextButton(Scene s) {
        super(s);
        setFont("res/font/MOON Night DEMO.otf", Font.TRUETYPE_FONT);
        setColor(new Color(1.0f, 0.93f, 0.55f, 1.0f));
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
