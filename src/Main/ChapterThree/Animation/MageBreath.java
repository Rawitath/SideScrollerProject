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
public class MageBreath extends Animation{

    public MageBreath() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/mage/mage.png"));
        sliceSprite(8, 128, 128, 8);
        setFps(12);
        setLoop(true);
    }
}
