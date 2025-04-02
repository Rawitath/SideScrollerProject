/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Option;

/**
 *
 * @author nirawith2548gmail.com
 */
import Entities.UI.UIButton;
import Main.StartMenu.Entities.Fadable;
import Scenes.Scene;
import Utilities.FileReader;

public class PreviousModeButton extends UIButton implements Fadable{
    
    public PreviousModeButton(Scene s){
        super(s);
        this.setHoverImage(FileReader.readImage("res/game/settingsmenu/Button.png"));
        this.setReleasedImage(FileReader.readImage("res/game/settingsmenu/Button.png"));
        this.setPressedImage(FileReader.readImage("res/game/settingsmenu/Button.png"));
        this.flipX();
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
