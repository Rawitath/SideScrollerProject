/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main;

import Datas.Vector2;
import Entities.UI.UIImage;
import Main.UI.Main.Stats.LucyDisplay;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class FrameTwo extends UIImage{
    private LucyDisplay lucyDisplay;
    public FrameTwo(Scene s) {
        super(s);
        setImage(FileReader.readImage("res/game/hud/statmenu/Rectangle_2.png"));
        lucyDisplay = new LucyDisplay(s);
        addChild(lucyDisplay);
    }

    @Override
    public void start() {
        lucyDisplay.setScale(new Vector2(100,100));
    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {

    }
}
