/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Example;

import Datas.Vector2;
import Entities.UI.UIProgressBar;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class MyProgressBar extends UIProgressBar{

    public MyProgressBar(Scene s) {
        super(s);
    }

    @Override
    public void start() {
        setMin(-50);
        setMax(50);
        setValue(50);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void fixedUpdate() {
        
    }
    
}
