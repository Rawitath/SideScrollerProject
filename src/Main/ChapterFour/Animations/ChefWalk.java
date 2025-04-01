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
public class ChefWalk extends Animation {

    public ChefWalk() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/chef/cheifwalk.png"));
        sliceSprite(5, 128, 128, 5);
        setFps(12);
        setLoop(true);
    }
    
}
