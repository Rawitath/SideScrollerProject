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
public class LucyJump extends Animation{
    public LucyJump(){
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/lucy/startjump.png"));
        sliceSprite(2, 128, 128, 2);
        setFps(12);
        setLoop(false);
    }
}
