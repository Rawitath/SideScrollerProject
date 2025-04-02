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
public class SheepAttack extends Animation{

    public SheepAttack() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/sheep/sheepattackblue.png"));
        sliceSprite(8, 154, 140, 8);
        setFps(12);
        setLoop(false);
    }
    
}
