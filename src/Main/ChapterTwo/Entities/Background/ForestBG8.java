/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterTwo.Entities.Background;

import Main.Entities.Main.StageBackground;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class ForestBG8  extends StageBackground{
    
    public ForestBG8(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/forestbg/Forest08.png"));
        setSpriteSize(getSpriteSize().multiply(1.56f));
    }
    
}
