/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities;

import Datas.Vector2;
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
        Vector2 size = Vector2.copy(getSpriteSize());
        size.multiply(0.3);
        setSpriteSize(size);
        
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
