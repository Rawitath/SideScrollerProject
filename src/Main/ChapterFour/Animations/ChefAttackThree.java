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
public class ChefAttackThree extends Animation {

    public ChefAttackThree() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/chef/cheifattack3.png"));
        sliceSprite(12, 128, 128, 12);
        setFps(12);
        setLoop(false);
    }
    
}
