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
public class ToAchievement  extends UIButton{
    
    public ToAchievement(Scene s) {
        super(s);
        setReleasedImage(FileReader.readImage("res/game/hud/statmenu/Achivement.png"));
        setHoverImage(FileReader.readImage("res/game/hud/statmenu/Achivement.png"));
        setPressedImage(FileReader.readImage("res/game/hud/statmenu/Achivement.png"));
    }

    @Override
    public void onButtonClicked() {

    }

    @Override
    public void onButtonHovered() {

    }

    @Override
    public void onButtonPressed() {

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
