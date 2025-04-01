/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Start;

import Datas.Vector2;
import Entities.UI.UIText;

import Scenes.Scene;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author GA_IA
 */
public class CopyrightText extends UIText{

    public CopyrightText(Scene s) {
        super(s);
        setFont("res/font/MOON Night DEMO.otf", Font.TRUETYPE_FONT);
        setColor(new Color(1.0f, 0.93f, 0.55f, 1.0f));
        setScreenAnchor(BOTTOM_RIGHT);
        setLocalPosition(new Vector2(-370, 30));
        setSize(24);
        
        setText("2025 Group นี้ไม่ได้นอน All Right Reserved");
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

//    @Override
//    public void fadeIn(float alpha) {
//        setColor(new Color(getColor().getRed() / 255f, getColor().getGreen() / 255f, getColor().getBlue() / 255f, alpha));
//    }
//
//    @Override
//    public void fadeOut(float alpha) {
//        setColor(new Color(getColor().getRed() / 255f, getColor().getGreen() / 255f, getColor().getBlue() / 255f, alpha));
//    }
//    
}
