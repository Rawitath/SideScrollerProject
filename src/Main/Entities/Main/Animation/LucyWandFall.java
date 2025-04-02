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
public class LucyWandFall extends Animation{
    public LucyWandFall(){
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/lucy/jump1.png"));
        sliceSprite(4, 128, 128, 4);
        setFps(12);
        setLoop(false);
    }
}
