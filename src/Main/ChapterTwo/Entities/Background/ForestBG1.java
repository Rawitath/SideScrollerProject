/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterTwo.Entities.Background;

import Datas.Vector2Int;
import Entities.SpriteEntity;
import Main.Entities.Main.StageBackground;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class ForestBG1 extends StageBackground{
    public ForestBG1(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/forestbg/Forest01.png"));
        setSpriteSize(getSpriteSize().multiply(1.56f));
    }
}
