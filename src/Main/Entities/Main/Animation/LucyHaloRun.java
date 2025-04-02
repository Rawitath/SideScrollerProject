/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Main.Animation;

import Animations.Animation;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class LucyHaloRun extends Animation {
    public LucyHaloRun(){
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/lucy/run3.png"));
        sliceSprite(7, 128, 128, 7);
        setFps(12);
        setLoop(true);
    }
}
