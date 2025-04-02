/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main.Stats;

import Entities.UI.UIImage;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class CheckBox extends UIImage{
    private boolean isOn = false;

    public CheckBox(Scene s) {
        super(s);
        setIsOn(false);
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

    public boolean isIsOn() {
        return isOn;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
        if(isOn){
            setImage(FileReader.readImage("res/game/hud/statmenu/CheckBox.png"));
        }
        else{
            setImage(FileReader.readImage("res/game/hud/statmenu/UncheckBox.png"));
        }
    }
    
}
