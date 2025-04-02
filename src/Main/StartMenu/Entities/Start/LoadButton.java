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
public class LoadButton extends MenuButton{
    
    public LoadButton(Scene s, MenuController controller) {
        super(s, controller);
//        setScreenAnchor(TOP_LEFT);
//        setPosition(new Vector2(getPosition().getX(), -630));
        buttonText.setText("LOAD");
    }
    
    @Override
    public void onButtonPressed() {
        if (getController().getCurrentPage() == 0){
            getController().goToPage(2);
        }
    }
}
