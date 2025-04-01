/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Scenes;

import Datas.Vector2;
import Main.ChapterFive.Cutscenes.BossCutscene;
import Main.ChapterFive.Entities.Background.*;
import Main.ChapterFive.Entities.BossWall;
import Main.ChapterFive.Entities.ChapterFiveManager;
import Main.ChapterFive.Entities.SheepBoss;
import Main.ChapterTwo.Entities.Spike;
import Main.Entities.Main.Lucy;
import Main.Entities.Main.SpawnMarker;
import Main.GameSystem.Cutscene.CutsceneTrigger;
import Main.GameSystem.SavePoint.SavePoint;
import Main.UI.Main.LucyUISet;
import Maps.MapBuilder;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterFiveScene extends Scene {

    private BossCutscene bossCutscene;
    private Lucy lucy;
    private SheepBoss sheep;
    private CutsceneTrigger trigger;
    private ChapterFiveManager manager;
    private LucyUISet ui;
    private SavePoint save9;
    private SavePoint save10;
    private SavePoint save11;
    
    private BossWall bosswall;
    
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
        manager = new ChapterFiveManager(this, lucy, ui);
        addEntity(manager);
        
        save9 = new SavePoint(this);
        save9.setSavePointID(9);
        save10 = new SavePoint(this);
        save10.setSavePointID(10);
        save11 = new SavePoint(this);
        save11.setSavePointID(11);
        
        bossCutscene = new BossCutscene(this);
        sheep = new SheepBoss(this, lucy);
        bossCutscene.addControlledEntities("Lucy", lucy);
        bossCutscene.addControlledEntities("Boss", sheep);
        
        trigger = new CutsceneTrigger(this, bossCutscene);
        trigger.setTriggerTag("Player");
        trigger.getCollider().setBound(new Vector2(1, 100));
        
        bosswall = new BossWall(this);
        
        
        addEntity(bossCutscene);
        
        SpawnMarker marker = new SpawnMarker(this);
        marker.setName("Mark1");
        
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("debugBoss", marker);
        MapBuilder.addVariable("Spike", new Spike(this));
        MapBuilder.addVariable("Save9", save9);
        MapBuilder.addVariable("Save10", save10);
        MapBuilder.addVariable("Save11", save11);
        MapBuilder.addVariable("last boss", sheep);
        MapBuilder.addVariable("BossCutsceneTrigger", trigger);
        MapBuilder.addVariable("Bosswall", bosswall);
        MapBuilder.setVariableClone("Spike", true);
        MapBuilder.setVariableClone("Bosswall", true);
        
        MapBuilder.loadMap("map/Chapter5");
        
        
        manager.setCutscene(bossCutscene);
    }
    
}
