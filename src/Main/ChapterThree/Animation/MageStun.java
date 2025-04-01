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
public class MageStun extends Animation{

    public MageStun() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/mage/mageattack3.png"));
        sliceSprite(6, 128, 128, 6);
        setFps(12);
        setLoop(true);
    }
    
}
