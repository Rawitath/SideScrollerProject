/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main.Stats;

import Entities.UI.UIText;
import Scenes.Scene;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author GA_IA
 */
public class HeaderText extends UIText{

    public HeaderText(Scene s) {
        super(s);
        setFont("res/font/MOON Night DEMO.otf", Font.TRUETYPE_FONT);
        setSize(56);
        setColor(new Color(1.0f, 0.93f, 0.55f, 1.0f));
        setHorizontalAlignment(UIText.CENTER);
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
