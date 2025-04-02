/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Scenes;
import Main.ChapterOne.Entities.Background.*;
import Main.ChapterOne.Entities.BreakOnShootBlock;
import Main.ChapterOne.Entities.BreakOnTouchBlock;
import Main.ChapterOne.Entities.ChapterOneManager;
import Main.ChapterOne.Entities.GoTo2;
import Main.ChapterOne.Entities.GoTo3;
import Main.ChapterOne.Entities.Lava;
import Main.ChapterOne.Entities.Mole;
import Main.ChapterOne.Entities.PushBox;
import Main.ChapterOne.Entities.Wall;
import Main.ChapterOne.Entities.Wand;
import Main.ChapterOne.Entities.Zombie;
import Main.Entities.Main.Lucy;
import Main.GameSystem.SavePoint.SavePoint;
import Main.Entities.Main.SpawnMarker;
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
    
    private SavePoint save0;
    private SavePoint save1;
    
    
    @Override
    public void load() {
        addEntity(new HellBG1(this));
        addEntity(new HellBG2(this));
        addEntity(new HellBG3(this));
        addEntity(new HellBG4(this));
        addEntity(new HellBG5(this));
        addEntity(new HellBG6(this));
        addEntity(new HellBG7(this));
        
        save0 = new SavePoint(this);
        save1 = new SavePoint(this);
        
        save0.setSavePointID(0);
        save1.setSavePointID(1);
        
        lucy = new Lucy(this);
        ui = new LucyUISet(this, lucy.getInventory());
        manager = new ChapterOneManager(this, lucy, ui);
        
        
        addEntity(manager);
        SpawnMarker mark1 = new SpawnMarker(this);
        SpawnMarker mark2 = new SpawnMarker(this);
        SpawnMarker mark3 = new SpawnMarker(this);
        mark1.setName("Mark1");
        mark2.setName("From2");
        mark3.setName("From3");
        
        BreakOnTouchBlock touchBlock = new BreakOnTouchBlock(this);
        //touchBlock.setName("Drop");
        
        BreakOnShootBlock breakBlock = new BreakOnShootBlock(this);
        //breakBlock.setName("Destroy");
        
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("Spawn", mark1);
        MapBuilder.addVariable("Lava", new Lava(this));
        MapBuilder.addVariable("Key Item", new Wand(this));
        MapBuilder.addVariable("Box", new PushBox(this));
        MapBuilder.addVariable("From2", mark2);
        MapBuilder.addVariable("From3", mark3);
        MapBuilder.addVariable("Drop", touchBlock);
        MapBuilder.addVariable("Enemy", new Zombie(this));
        MapBuilder.addVariable("Save0", save0);
        MapBuilder.addVariable("GoTo2", new GoTo2(this, manager));
        MapBuilder.addVariable("GoTo3", new GoTo3(this, manager));
        MapBuilder.addVariable("Save1", save1);
        MapBuilder.addVariable("Mole", new Mole(this));
        MapBuilder.addVariable("Destory", breakBlock);
        MapBuilder.addVariable("Wall", new Wall(this));
        MapBuilder.setVariableClone("Enemy", true);
        MapBuilder.setVariableClone("GoTo3", true);
        MapBuilder.setVariableClone("Destory", true);
        MapBuilder.setVariableClone("Drop", true);
        MapBuilder.setVariableClone("Box", true);
        MapBuilder.setVariableClone("Lava", true);
        MapBuilder.loadMap("map/Chapter1");
    }
    
}
