/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Animations;

import Animations.Animation;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class ChefAttackTwo extends Animation {

    public ChefAttackTwo() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/chef/cheifattack2.png"));
        sliceSprite(11, 128, 128, 11);
        setFps(12);
        setLoop(false);
    }
    
}
