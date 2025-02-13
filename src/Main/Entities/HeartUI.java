/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities;

import Datas.Vector2;
import Datas.Vector2Int;
import Entities.UIImage;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class HeartUI extends UIImage{

    public HeartUI(Scene s) {
        super(s);
        setImage(FileReader.readImage("res/game/kotori.jpg"), true);
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
