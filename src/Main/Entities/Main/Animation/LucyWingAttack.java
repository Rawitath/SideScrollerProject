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
public class LucyWingAttack extends Animation{
    public LucyWingAttack(){
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/lucy/attack1.png"));
        sliceSprite(3, 128, 128, 3);
        setFps(12);
        setLoop(false);
    }
}
