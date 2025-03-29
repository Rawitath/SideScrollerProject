/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Scenes;


import Main.ChapterOne.Entities.Background.*;
import Main.ChapterThree.Entities.Background.CaveBG0;
import Main.ChapterThree.Entities.ChapterThreeManager;
import Main.Entities.Main.Lucy;
import Maps.MapBuilder;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterThreeScene extends Scene{

    private Lucy lucy;
    private ChapterThreeManager manager;
    
    @Override
    public void load() {
        
        addEntity(new CaveBG0(this));
        addEntity(new HellBG2(this));
        addEntity(new HellBG3(this));
        addEntity(new HellBG5(this));
        addEntity(new HellBG6(this));
        
        lucy = new Lucy(this);
        manager = new ChapterThreeManager(this, lucy);
        addEntity(manager);
        
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("spaw for 2", lucy);
        MapBuilder.loadMap("map/Chapter3");
    }
    
}
