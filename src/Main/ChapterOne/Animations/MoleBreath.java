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
public class MoleBreath extends Animation{

    public MoleBreath() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/mole/mole.png"));
        sliceSprite(10, 256, 128, 10);
        setFps(12);
        setLoop(true);
    }
    
}
