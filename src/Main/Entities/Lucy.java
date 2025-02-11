/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities;

import Datas.Vector2;
import Datas.Vector2Int;
import Entities.SpriteEntity;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Lucy extends SpriteEntity{

    public Lucy(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/lucy.png"));
        setSpriteSize(getSpriteSize().multiply(0.3f));       
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
