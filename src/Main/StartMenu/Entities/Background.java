/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities;

import Datas.Vector2;
import Entities.UI.UIImage;
import Physics.Time;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Background extends UIImage{

    public Background(Scene s) {
        super(s);
        setImage(FileReader.readImage("res/game/startmenu/BGExtended.png"));
        setScale(new Vector2(1920, 1080));
        setAlpha(0.0f);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        if(getAlpha() < 1){
            if(getAlpha() + Time.deltaTime() < 1){
            setAlpha(getAlpha() + 0.8f * Time.deltaTime());
            }
            else{

                setAlpha(1f);
            }
        }
    }   

    @Override
    public void fixedUpdate() {

    }
    
}
