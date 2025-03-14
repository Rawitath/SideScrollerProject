/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Entities.UI.UIButton;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ActionButton extends UIButton{

    private TextBox text;
    
    public ActionButton(Scene s) {
        super(s);
        text = new TextBox(s);
        addChild(text);
    }

    public TextBox getText() {
        return text;
    }
    
    @Override
    public void onButtonClicked() {

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
