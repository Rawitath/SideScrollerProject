/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Scenes;

import Main.ChapterFour.Entities.ChapterFourManager;
import Main.Entities.Main.Lucy;
import Maps.MapBuilder;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterFourScene extends Scene {

    private Lucy lucy;
    private ChapterFourManager manager;
    @Override
    public void load() {
        lucy = new Lucy(this);
        manager = new ChapterFourManager(this, lucy);
        addEntity(manager);
        
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("Entry1", lucy);
        MapBuilder.loadMap("map/Chapter4");
    }
    
}
