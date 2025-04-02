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
public class ChefAttackOne extends Animation{

    public ChefAttackOne() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/chef/cheifattack.png"));
        sliceSprite(12, 128, 128, 12);
        setFps(12);
        setLoop(false);
    }
    
}
