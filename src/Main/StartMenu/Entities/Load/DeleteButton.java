/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Load;

import Entities.UI.UIButton;
import Main.StartMenu.Entities.Fadable;
import Main.StartMenu.Entities.MenuController;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author nirawith2548gmail.com
 */
public class DeleteButton extends UIButton implements Fadable{
    
    public DeleteButton(Scene s){
        super(s);
        this.setReleasedImage(FileReader.readImage("res/game/loadmenu/DeleteSaveButton.png"));
        this.setHoverImage(FileReader.readImage("res/game/loadmenu/DeleteSaveButton.png"));
        this.setPressedImage(FileReader.readImage("res/game/loadmenu/DeleteSaveButton.png"));
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

    @Override
    public void fade(float alpha) {
        this.setAlpha(alpha);
    }
    
}
