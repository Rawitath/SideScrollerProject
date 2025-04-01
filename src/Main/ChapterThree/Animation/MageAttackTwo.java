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
public class MageAttackTwo extends Animation{

    public MageAttackTwo() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/mage/mageattack2.png"));
        sliceSprite(12, 128, 128, 13);
        setFps(13);
        setLoop(false);
    }
    
}
