/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Example;

import Datas.Vector2;
import Entities.UI.UIText;
import Scenes.Scene;
import Utilities.FileReader;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author GA_IA
 */
public class ThisIsExample extends UIText{

    public ThisIsExample(Scene s) {
        super(s);
        setFont("res/font/Itim-Regular.ttf", Font.TRUETYPE_FONT);
        setColor(Color.white);
        setSize(48);
        setStyle(Font.BOLD);
    }

    @Override
    public void start() {
        setPosition(new Vector2(0 ,200));
        setText("This is Example!");
        setBoundaryVisibled(true);
    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {

    }
    
}
