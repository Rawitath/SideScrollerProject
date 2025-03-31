/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterTwo.Scenes;

import Main.ChapterTwo.Entities.*;
import Main.ChapterTwo.Entities.Background.*;
import Main.Entities.Main.Lucy;
import Main.Entities.Main.SpawnMarker;
import Main.GameSystem.SavePoint.SavePoint;
import Main.UI.Main.LucyUISet;
import Maps.MapBuilder;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterTwoScene extends Scene{

    private ChapterTwoManager manager;
    private Lucy lucy;
    private LucyUISet ui;
    private SavePoint save2;
    
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
        ui = new LucyUISet(this, lucy.getInventory());
        manager = new ChapterTwoManager(this, lucy, ui);
        
        addEntity(manager);
        SpawnMarker sp = new SpawnMarker(this);
        sp.setName("Mark1");
        
        save2 = new SavePoint(this);
        save2.setSavePointID(2);
        
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("From1", sp);
        MapBuilder.addVariable("Save2", save2);
        MapBuilder.addVariable("Spike", new Spike(this));
        MapBuilder.addVariable("RedKey1", new RedKey1(this));
        MapBuilder.addVariable("RedKey2", new RedKey2(this));
        MapBuilder.addVariable("Door1", new RedDoor1(this));
        MapBuilder.addVariable("Door2", new RedDoor2(this));
        MapBuilder.setVariableClone("Spike", true);
        MapBuilder.loadMap("map/Chapter2");
    }
    
}
