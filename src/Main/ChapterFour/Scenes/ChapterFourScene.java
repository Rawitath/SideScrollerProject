/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Scenes;

import Main.ChapterFive.Entities.Background.*;
import Main.ChapterFour.Entities.ChapterFourManager;
import Main.ChapterFour.Entities.FireballStatic;
import Main.ChapterThree.Entities.VerticalFireball;
import Main.Entities.Main.Lucy;
import Main.UI.Main.LucyUISet;
import Maps.MapBuilder;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterFourScene extends Scene {

    private Lucy lucy;
    private ChapterFourManager manager;
    private LucyUISet ui;
    @Override
    public void load() {
        
        addEntity(new CastleBG0(this));
        addEntity(new CastleBG2(this));
        addEntity(new CastleBG1(this));
        addEntity(new CastleBG3(this));
        addEntity(new CastleBG4(this));
        addEntity(new CastleBG5(this));
        addEntity(new CastleBG6(this));
        addEntity(new CastleBG7(this));
        addEntity(new CastleBG8(this));
        
        lucy = new Lucy(this);
        ui = new LucyUISet(this, lucy.getInventory());
        manager = new ChapterFourManager(this, lucy, ui);
        addEntity(manager);
        
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("Entry1", lucy);
        MapBuilder.addVariable("fire ball UD", new FireballStatic(this));
        MapBuilder.loadMap("map/Chapter4");
    }
    
}
