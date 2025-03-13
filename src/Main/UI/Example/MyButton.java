/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Example;

import Datas.Vector2;
import Entities.UI.UIButton;
import Scenes.Scene;
import Utilities.FileReader;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author GA_IA
 */
public class MyButton extends UIButton{

    public MyButton(Scene s) {
        super(s);
    }
    
    @Override
    public void start() {
        setScale(new Vector2(300,100));
        setPosition(new Vector2(-200,0));
    }

    @Override
    public void update() {
        
    }

    @Override
    public void fixedUpdate() {
        
    }

    @Override
    public void onButtonClicked() {
        
    }

    @Override
    public void onButtonPressed() {
        
    }

    @Override
    public void onButtonReleased() {

    }

    @Override
    public void onMouseWheelMoved(MouseWheelEvent e) {

    }
}
