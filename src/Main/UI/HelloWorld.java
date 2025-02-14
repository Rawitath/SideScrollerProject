/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI;

import Datas.Vector2;
import Entities.UI.UIText;
import Scenes.Scene;
import java.awt.Color;

/**
 *
 * @author GA_IA
 */
public class HelloWorld extends UIText{

    public HelloWorld(Scene s) {
        super(s);
        setName("Life");
        setColor(Color.white);
        setText("Life");
    }

    @Override
    public void start() {
        setPosition(new Vector2(-900, 370));
    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {

    }
    
}
