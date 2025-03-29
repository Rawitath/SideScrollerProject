/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Entities.Background;

import Main.Entities.Main.StageBackground;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class HellBG5 extends StageBackground{
    
    public HellBG5(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/hellbg/map05.png"));
        setSpriteSize(getSpriteSize().multiply(1.56f));
        setHorizontalShiftMultiplier(0.98f);
    }
    
}
