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
public class RedKeyAnimation extends Animation{
    public RedKeyAnimation(){
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/chapter/redkey.png"));
        sliceSprite(4, 374, 374, 4);
        setFps(8);
        setLoop(true);
    }
}
