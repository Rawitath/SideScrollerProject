/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Scene;

import Datas.Vector2Int;
import Main.StartMenu.Entities.*;
import Main.StartMenu.Entities.Option.OptionController;
import Main.StartMenu.Entities.Option.OptionGroup;
import Main.StartMenu.Entities.Start.MainGroup;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class StartMenuScene extends Scene {
    
    private MenuController controller;
    
    private OptionController option;
    private OptionGroup optionGroup;
    
    @Override
    public void load() {
        getUIView().setReferenceResolution(new Vector2Int(1920, 1080));
        
        controller = new MenuController(this);
        addEntity(controller);
        
        addEntity(new Background(this));
        addEntity(new MainGroup(this, controller));
        
        option = new OptionController(this);
        addEntity(option);
        controller.setOption(option);
        
        optionGroup = new OptionGroup(this, controller);
        addEntity(optionGroup);
    }
    
}
