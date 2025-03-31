/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main;

import Entities.UI.UIButton;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class ToMap extends UIButton{
    private StatPanel p;
    
    public ToMap(Scene s, StatPanel p) {
        super(s);
        this.p = p;
        setReleasedImage(FileReader.readImage("res/game/hud/statmenu/MAP.png"));
        setPressedImage(FileReader.readImage("res/game/hud/statmenu/MAP.png"));
        setHoverImage(FileReader.readImage("res/game/hud/statmenu/MAP.png"));
    }

    @Override
    public void onButtonClicked() {

    }

    @Override
    public void onButtonHovered() {

    }

    @Override
    public void onButtonPressed() {
        p.setCurrentPage(1);
    }

    @Override
    public void onButtonReleased() {

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
