/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Scene;

import Datas.Vector2Int;
import Entities.UI.UIImage;
import Main.StartMenu.Entities.*;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class StartMenuScene extends Scene {
    
    private MenuController controller;
    private UIImage buttonFrame;
    private ButtonGroup buttonGroup;
    
    @Override
    public void load() {
        getUIView().setReferenceResolution(new Vector2Int(1920, 1080));
        
        controller = new MenuController(this);
        buttonFrame = new ButtonFrame(this);
        controller.setButtonFrame(buttonFrame);
        addEntity(controller);
        
        addEntity(new Background(this));
        addEntity(buttonFrame);
        addEntity(new Logo(this));
        addEntity(new StartButton(this, controller));
        addEntity(new NewGameButton(this, controller));
        addEntity(new LoadButton(this, controller));
        addEntity(new OptionButton(this, controller));
        addEntity(new CopyrightText(this));
        
        buttonGroup = new ButtonGroup(this);
        addEntity(buttonGroup);
        controller.setGroup(buttonGroup);
    }
    
}
