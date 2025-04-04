/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Option;

import Datas.Vector2;
import Entities.UI.UIImage;
import Main.StartMenu.Entities.Fadable;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class SideWing extends UIImage implements Fadable{

    public SideWing(Scene s) {
        super(s);
        this.setImage(FileReader.readImage("res/game/settingsmenu/Wingy_Thing.png"));
        setScale(new Vector2(getImage().getWidth(), getImage().getHeight()));
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
        setAlpha(alpha);
    }
    
}
