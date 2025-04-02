/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterTwo.Animation;

import Animations.Animation;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class BlueKeyAnimation extends Animation{
    public BlueKeyAnimation(){
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/chapter/bluekey.png"));
        sliceSprite(4, 190, 189, 4);
        setFps(8);
        setLoop(true);
    }
}
