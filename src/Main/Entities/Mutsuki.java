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
public class Mutsuki extends SpriteEntity{

    public Mutsuki(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/mutsuki.png"));
        setSpriteSize(getSpriteSize().multiply(0.3f));
        setPosition(getPosition().add(Vector2.up().multiply(100)));
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
