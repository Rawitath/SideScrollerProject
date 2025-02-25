/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Example;

import Entities.SpriteEntity;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class HellBackground extends SpriteEntity{

    public HellBackground(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/hell.jpg"));
        setPixelRatio(0.1f);
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
