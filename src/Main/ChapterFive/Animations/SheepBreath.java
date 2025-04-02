/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Animations;

import Animations.Animation;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class SheepBreath extends Animation{
    public SheepBreath(){
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/sheep/breath.png"));
        sliceSprite(8, 128, 128, 8);
        setFps(12);
        setLoop(true);
    }
}
