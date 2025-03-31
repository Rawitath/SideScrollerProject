/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Start;

import Datas.Vector2;
import Main.StartMenu.Entities.MenuController;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class NewGameButton extends MenuButton {
    
    public NewGameButton(Scene s, MenuController controller) {
        super(s, controller);
//        setScreenAnchor(TOP_LEFT);
//        setPosition(new Vector2(getPosition().getX(), -480));
        buttonText.setText("NEW GAME");
    }
    
}
