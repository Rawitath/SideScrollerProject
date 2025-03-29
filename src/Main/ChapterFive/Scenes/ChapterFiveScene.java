/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Scenes;

import Datas.Vector2;
import Main.ChapterFive.Cutscenes.BossCutscene;
import Main.ChapterFive.Entities.BossWall;
import Main.ChapterFive.Entities.ChapterFiveManager;
import Main.ChapterFive.Entities.SheepBoss;
import Main.Entities.Main.Lucy;
import Main.GameSystem.Cutscene.CutsceneTrigger;
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
    
    private BossWall bosswall;
    
    @Override
    public void load() {
        
        lucy = new Lucy(this);
        ui = new LucyUISet(this, lucy.getInventory());
        manager = new ChapterFiveManager(this, lucy, ui);
        addEntity(manager);
        addEntity(ui);
        
        bossCutscene = new BossCutscene(this);
        sheep = new SheepBoss(this, lucy);
        bossCutscene.addControlledEntities("Lucy", lucy);
        bossCutscene.addControlledEntities("Boss", sheep);
        
        trigger = new CutsceneTrigger(this, bossCutscene);
        trigger.setTriggerTag("Player");
        trigger.getCollider().setBound(new Vector2(1, 100));
        
        bosswall = new BossWall(this);
        
        addEntity(bossCutscene);
        
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("debugBoss", lucy);
        MapBuilder.addVariable("last boss", sheep);
        MapBuilder.addVariable("BossCutsceneTrigger", trigger);
        MapBuilder.addVariable("Bosswall", bosswall);
        MapBuilder.setVariableClone("Bosswall", true);
        
        MapBuilder.loadMap("map/Chapter5");
        
        
        manager.setCutscene(bossCutscene);
    }
    
}
