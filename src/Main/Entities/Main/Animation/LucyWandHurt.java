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
public class LucyWandHurt extends Animation{
    public LucyWandHurt(){
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/lucy/hurt1.png"));
        sliceSprite(1, 128, 128, 1);
        setFps(1);
        setLoop(false);
    }
}
