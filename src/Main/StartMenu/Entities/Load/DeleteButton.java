/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Load;

import Entities.UI.UIButton;
import Main.StartMenu.Entities.Fadable;
import Main.StartMenu.Entities.MenuController;
import Physics.Time;
import Saves.GameSave;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author nirawith2548gmail.com
 */
public class DeleteButton extends UIButton implements Fadable{
    
    private SaveGroup sv;
    private float deleteCount = 5f;
    private float previous;
    private boolean isDelete = false;
    
    public DeleteButton(Scene s, SaveGroup sv){
        super(s);
        this.sv = sv;
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
        previous = Time.time();
        isDelete = true;
    }

    @Override
    public void onButtonReleased() {
        isDelete = false;
    }

    @Override
    public void start() {
    }

    @Override
    public void update() {
        if(isDelete){
            if(Time.time() - previous > deleteCount){
                GameSave gs = sv.getSave();
                sv.setSave(null);
                
            }
        }
    }

    @Override
    public void fixedUpdate() {
    }

    @Override
    public void fade(float alpha) {
        this.setAlpha(alpha);
    }
    
}
