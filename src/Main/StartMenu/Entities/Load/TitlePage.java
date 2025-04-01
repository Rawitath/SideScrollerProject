/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Load;

import Entities.UI.UIImage;
import Scenes.Scene;
import Utilities.FileReader;
import Datas.Vector2;
import Main.StartMenu.Entities.Fadable;

/**
 *
 * @author nirawith2548gmail.com
 */
public class TitlePage extends UIImage implements Fadable{
    
    public TitlePage(Scene s){
        super(s);
        this.setImage(FileReader.readImage("res/game/loadmenu/Save_Game.png"));
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
