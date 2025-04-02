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
public class ZombieRun extends Animation{

    public ZombieRun() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/zombie/skeletonWalk.png"));
        sliceSprite(10, 128, 128, 10);
        setFps(10);
        setLoop(true);
    }
    
}
