/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Scenes;
import Main.ChapterOne.Entities.Background.*;
import Main.ChapterOne.Entities.BreakOnTouchBlock;
import Main.ChapterOne.Entities.ChapterOneManager;
import Main.ChapterOne.Entities.Lava;
import Main.ChapterOne.Entities.PushBox;
import Main.Entities.Main.Lucy;
import Main.UI.Main.LucyUISet;
import Maps.MapBuilder;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterOneScene extends Scene{

    private Lucy lucy;
    private ChapterOneManager manager;
    private LucyUISet ui;
    @Override
    public void load() {
        addEntity(new HellBG1(this));
        addEntity(new HellBG2(this));
        addEntity(new HellBG3(this));
        addEntity(new HellBG4(this));
        addEntity(new HellBG5(this));
        addEntity(new HellBG6(this));
        addEntity(new HellBG7(this));
        
        lucy = new Lucy(this);
        ui = new LucyUISet(this, lucy.getInventory());
        manager = new ChapterOneManager(this, lucy, ui);
        
        addEntity(manager);
        
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("Spawn", lucy);
        MapBuilder.addVariable("Lava", new Lava(this));
        MapBuilder.addVariable("Box", new PushBox(this));
        MapBuilder.addVariable("Drop", new BreakOnTouchBlock(this));
        MapBuilder.setVariableClone("Drop", true);
        MapBuilder.setVariableClone("Box", true);
        MapBuilder.setVariableClone("Lava", true);
        MapBuilder.loadMap("map/Chapter1");
    }
    
}
