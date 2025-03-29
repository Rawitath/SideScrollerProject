/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Entities.Background;

import Main.Entities.Main.StageBackground;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class CastleBG8 extends StageBackground{
    
    public CastleBG8(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/castlebg/Castle08.png"));
        setSpriteSize(getSpriteSize().multiply(1.56f));
        setHorizontalShiftMultiplier(0.99f);
    }
    
}
