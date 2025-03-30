/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Main.Animation;

import Animations.Animation;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class LucyHurt extends Animation {
    public LucyHurt(){
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/lucy/hurt.png"));
        sliceSprite(1, 128, 128, 1);
        setFps(1);
        setLoop(false);
    }
}
