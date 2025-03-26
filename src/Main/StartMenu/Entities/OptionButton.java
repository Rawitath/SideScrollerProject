/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities;

import Datas.Vector2;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class OptionButton extends MenuButton{
    
    public OptionButton(Scene s, MenuController controller) {
        super(s, controller);
        setScreenAnchor(TOP_LEFT);
        setPosition(new Vector2(getPosition().getX(), -780));
        buttonText.setText("OPTION");
    }
    
}
