/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Animations;

import Animations.Animation;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class SheepAttackTwo extends Animation{

    public SheepAttackTwo() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/sheep/sheepattackred.png"));
        sliceSprite(12, 128, 128, 12);
        setFps(12);
        setLoop(false);
    }
    
}
