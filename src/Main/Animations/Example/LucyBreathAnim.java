/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Animations.Example;

import Animations.Animation;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class LucyBreathAnim extends Animation{

    public LucyBreathAnim() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/lucy_breath.png"));
        sliceSprite(12, 128, 128, 12);
        setLoop(true);
    }
}
