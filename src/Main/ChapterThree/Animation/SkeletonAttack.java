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
public class SkeletonAttack extends Animation{

    public SkeletonAttack() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/skeleton/chiefskeleton-attack.png"));
        sliceSprite(3, 128, 128, 3);
        setFps(7);
        setLoop(true);
    }
    
}
