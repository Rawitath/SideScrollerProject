/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Scene;

import Datas.Vector2Int;
import Main.StartMenu.Entities.*;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class StartMenuScene extends Scene {

    @Override
    public void load() {
        getUIView().setReferenceResolution(new Vector2Int(1920, 1080));
        addEntity(new Background(this));
        addEntity(new Logo(this));
        addEntity(new StartButton(this));
        addEntity(new NewGameButton(this));
        addEntity(new LoadButton(this));
        addEntity(new OptionButton(this));
    }
    
}
