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
public class LucyHaloDead extends Animation {
    public LucyHaloDead() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/lucy/dead3.png"));
        sliceSprite(8, 128, 128, 8);
        setFps(8);
        setLoop(false);
    }
}
