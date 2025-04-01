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
public class ChefDead extends Animation {

    public ChefDead() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/chef/cheifdead.png"));
        sliceSprite(6, 128, 128, 6);
        setFps(12);
        setLoop(false);
    }
    
}
