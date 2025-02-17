/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI;

import Datas.Vector2;
import Datas.Vector2Int;
import Entities.UI.UIImage;
import Scenes.Scene;
import Utilities.FileReader;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public class HeartUI extends UIImage{

    BufferedImage heartFull = FileReader.readImage("res/game/kotori.jpg"); 
    BufferedImage heartEmpty = FileReader.readImage("res/game/mutsuki.png"); 
    public HeartUI(Scene s) {
        super(s);
        setImage(heartFull);
        setScale(new Vector2(100, 100));
    }
    public void setHeart(boolean isFull){
        if(isFull){
            setImage(heartFull);
        }
        else{
            setImage(heartEmpty);
        }
    }
    @Override
    public void start() {
        setScreenAnchor(LEFT);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void fixedUpdate() {

    }
    
}
