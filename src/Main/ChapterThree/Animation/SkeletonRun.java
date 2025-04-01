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
public class SkeletonRun extends Animation{

    public SkeletonRun() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/skeleton/chiefskeletonWalk.png"));
        sliceSprite(10, 128, 128, 10);
        setFps(12);
        setLoop(true);
    }
    
}
