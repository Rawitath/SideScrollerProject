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
public class ToStats extends UIButton{
    private StatPanel p;
    public ToStats(Scene s, StatPanel p) {
        super(s);
        this.p = p;
        setReleasedImage(FileReader.readImage("res/game/hud/statmenu/STAT.png"));
        setPressedImage(FileReader.readImage("res/game/hud/statmenu/STAT.png"));
        setHoverImage(FileReader.readImage("res/game/hud/statmenu/STAT.png"));
    }

    @Override
    public void onButtonClicked() {
        
    }

    @Override
    public void onButtonHovered() {

    }

    @Override
    public void onButtonPressed() {
        p.setCurrentPage(0);
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
