/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Load;

import Entities.UI.UIButton;
import Main.StartMenu.Entities.Fadable;
import Main.StartMenu.Entities.MenuController;
import Saves.SaveManager;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author nirawith2548gmail.com
 */

public class LoadButton extends UIButton implements Fadable{
    private MenuController controller;
    private SaveGroup saveGroup;
    public LoadButton(Scene s, MenuController controller, SaveGroup saveGroup){
        super(s);
        this.controller = controller;
        this.saveGroup = saveGroup;
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
        if(getAlpha() > 0.5f){
             if(controller.getCurrentPage() == 2){
             SaveManager.getInstance().loadSave(saveGroup.getSaveID());
            controller.begin();
        }
        }
    }

    @Override
    public void onButtonReleased() {
    }
    
}
