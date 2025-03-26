/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities;

import Datas.Vector2;
import Entities.UI.UIImage;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Logo extends UIImage{

    public Logo(Scene s) {
        super(s);
        setImage(FileReader.readImage("res/game/startmenu/Logo.png"));
        setScale(new Vector2(getImage().getWidth(), getImage().getHeight()));
        setScreenAnchor(TOP_LEFT);
        setPosition(new Vector2(400, -150));
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
