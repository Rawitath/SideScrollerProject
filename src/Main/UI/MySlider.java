/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI;

import Datas.Vector2;
import Entities.UI.UISlider;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class MySlider extends UISlider{

    public MySlider(Scene s) {
        super(s);
    }

    @Override
    public void start() {
        // Slider cannot handle negative Min, needed to be fixed;
        setMin(0);
        setMax(100);
        setValue(0);
//        getBar().setImage(FileReader.readImage("res/game/kotori.jpg"));
//        getBar().getFill().setImage(FileReader.readImage("res/game/mutsuki.png"));
//        getHandle().setReleasedImage(FileReader.readImage("res/icon/aronadaingai.jpg"));
    }

    @Override
    public void update() {
        
    }

    @Override
    public void fixedUpdate() {

    }
    
}
