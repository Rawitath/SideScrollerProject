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
public class LucyWingBreath extends Animation{
    public LucyWingBreath() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/lucy/breath2.png"));
        sliceSprite(12, 128, 128, 12);
        setFps(12);
        setLoop(true);
    }
}
