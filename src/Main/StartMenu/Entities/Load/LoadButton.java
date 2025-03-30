/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Load;

import Entities.UI.UIButton;
import Main.StartMenu.Entities.Fadable;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author nirawith2548gmail.com
 */

public class LoadButton extends UIButton implements Fadable{
    
    public LoadButton(Scene s){
        super(s);
        this.setReleasedImage(FileReader.readImage("res/game/loadmenu/LoadButton.png"));
        this.setPressedImage(FileReader.readImage("res/game/loadmenu/LoadClick.png"));
        this.setHoverImage(FileReader.readImage("res/game/loadmenu/LoadHover.png"));
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

    @Override
    public void fade(float alpha) {
        this.setAlpha(alpha);
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
    
}
