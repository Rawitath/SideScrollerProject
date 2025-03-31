/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main;

import Entities.UI.UIImage;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class FrameThree extends UIImage{
    public FrameThree(Scene s) {
        super(s);
        setImage(FileReader.readImage("res/game/hud/statmenu/Rectangle_3.png"));
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
