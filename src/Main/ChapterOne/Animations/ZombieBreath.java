/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Animations;

import Animations.Animation;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class ZombieBreath extends Animation{

    public ZombieBreath() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/zombie/skeleton.png"));
        sliceSprite(5, 128, 128, 5);
        setFps(5);
        setLoop(true);
    }
    
}
