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
public class ChefBreath extends Animation {

    public ChefBreath() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/chef/cheif.png"));
        sliceSprite(8, 128, 128, 8);
        setFps(12);
        setLoop(true);
    }
}
