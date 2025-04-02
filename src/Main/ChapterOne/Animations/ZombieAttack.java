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
public class ZombieAttack extends Animation{

    public ZombieAttack() {
        super();
        setSpriteSheet(FileReader.readImage("res/game/animation/zombie/skeleton-attack.png"));
        sliceSprite(4, 128, 128, 4);
        setFps(12);
        setLoop(false);
        
    }
    
}
