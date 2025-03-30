/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Animation;

import Animations.Animation;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class FireballHorizontal extends Animation{
    public FireballHorizontal(){
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/chapter/fireball1.png"));
        sliceSprite(3, 460, 460, 3);
        setFps(12);
        setLoop(true);
    }
}
