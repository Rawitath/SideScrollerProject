/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Animations;

import Animations.Animation;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class LavaAnimation extends Animation{
    public LavaAnimation(){
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/chapter/Lava.png"));
        sliceSprite(3, 192, 192, 3);
        setFps(3);
        setLoop(true);
    }
}
