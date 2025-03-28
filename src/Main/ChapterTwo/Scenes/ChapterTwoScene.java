/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterTwo.Scenes;

import Main.ChapterTwo.Entities.ChapterTwoManager;
import Main.ChapterTwo.Entities.Background.*;
import Main.Entities.Main.Lucy;
import Maps.MapBuilder;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterTwoScene extends Scene{

    private ChapterTwoManager manager;
    private Lucy lucy;
    
    @Override
    public void load() {
        
        addEntity(new ForestBG0(this));
        addEntity(new ForestBG1(this));
        addEntity(new ForestBG2(this));
        addEntity(new ForestBG3(this));
        addEntity(new ForestBG4(this));
        addEntity(new ForestBG5(this));
        addEntity(new ForestBG6(this));
        addEntity(new ForestBG7(this));
        addEntity(new ForestBG8(this));
        addEntity(new ForestBG9(this));
        
        lucy = new Lucy(this);
        manager = new ChapterTwoManager(this, lucy);
        
        addEntity(manager);
        
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("GoTo1", lucy);
        MapBuilder.loadMap("map/Chapter2");
    }
    
}
