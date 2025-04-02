/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Scenes;


import Datas.Vector2;
import Main.ChapterFive.Cutscenes.BossCutscene;
import Main.ChapterFive.Entities.BossWall;
import Main.ChapterFive.Entities.SheepBoss;
import Main.ChapterOne.Entities.Background.*;
import Main.ChapterOne.Entities.BreakOnShootBlock;
import Main.ChapterOne.Entities.Lava;
import Main.ChapterOne.Entities.PushBox;
import Main.ChapterOne.Entities.Zombie;
import Main.ChapterThree.Cutscenes.MageCutscene;
import Main.ChapterThree.Entities.AutomaticLift;
import Main.ChapterThree.Entities.Background.CaveBG0;
import Main.ChapterThree.Entities.BossKey;
import Main.ChapterThree.Entities.CastleKey;
import Main.ChapterThree.Entities.ChapterThreeManager;
import Main.ChapterThree.Entities.DoorRever;
import Main.ChapterThree.Entities.Fireball;
import Main.ChapterThree.Entities.GoTo1;
import Main.ChapterThree.Entities.GoTo4;
import Main.ChapterThree.Entities.Lever;
import Main.ChapterThree.Entities.Lift;
import Main.ChapterThree.Entities.MageBoss;
import Main.ChapterThree.Entities.Rever;
import Main.ChapterThree.Entities.RoomBoss;
import Main.ChapterThree.Entities.Skeleton;
import Main.ChapterThree.Entities.VerticalFireball;
import Main.ChapterThree.Entities.Wing;
import Main.ChapterTwo.Entities.Spike;
import Main.Entities.Main.Lucy;
import Main.Entities.Main.SpawnMarker;
import Main.GameSystem.Cutscene.CutsceneTrigger;
import Main.GameSystem.SavePoint.SavePoint;
import Main.UI.Main.LucyUISet;
import Maps.MapBuilder;
import Saves.SaveManager;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterThreeScene extends Scene{

    private Lucy lucy;
    private ChapterThreeManager manager;
    private LucyUISet ui;
    private Lift lift;
    private Fireball fireball;
    private Lever rever;
    private DoorRever doorRever;
    private SavePoint save3;
    private SavePoint save4;
    private SavePoint save5;
    
    @Override
    public void load() {
        
        addEntity(new CaveBG0(this));
        addEntity(new HellBG2(this));
        addEntity(new HellBG3(this));
        addEntity(new HellBG5(this));
        addEntity(new HellBG6(this));
        
        lucy = new Lucy(this);
        ui = new LucyUISet(this, lucy.getInventory());
        manager = new ChapterThreeManager(this, lucy, ui);
        addEntity(manager);
        
        lift = new AutomaticLift(this);
        
        fireball = new VerticalFireball(this);
        
        rever = new Rever(this);
        doorRever = new DoorRever(this, rever);
        
        save3 = new SavePoint(this);
        save3.setSavePointID(3);
        save4 = new SavePoint(this);
        save4.setSavePointID(4);
        save5 = new SavePoint(this);
        save5.setSavePointID(5);
        
        MageBoss mage = new MageBoss(this, lucy);
        
        MageCutscene bossCutscene = new MageCutscene(this);
        bossCutscene.addControlledEntities("Lucy", lucy);
        bossCutscene.addControlledEntities("Boss", mage);
        
        CutsceneTrigger trigger = new CutsceneTrigger(this, bossCutscene);
        trigger.setTriggerTag("Player");
        trigger.getCollider().setBound(new Vector2(1, 100));
        
//        bosswall = new BossWall(this);
        
        SpawnMarker marker = new SpawnMarker(this);
        marker.setName("Mark1");
        
        SpawnMarker marker2 = new SpawnMarker(this);
        marker2.setName("spaw for chap1");
        
        SpawnMarker marker3 = new SpawnMarker(this);
        marker3.setName("From4");
        
        addEntity(bossCutscene);
        
        MapBuilder.useMapBuilder(this);
        
        MapBuilder.addVariable("spaw for chap1", marker2);
        MapBuilder.addVariable("spaw for 2", marker);
        MapBuilder.addVariable("Spike", new Spike(this));
        MapBuilder.addVariable("elevator", lift);
        MapBuilder.addVariable("Save3", save3);
        MapBuilder.addVariable("destroy", new BreakOnShootBlock(this));
        MapBuilder.addVariable("Save4", save4);
        MapBuilder.addVariable("wing", new Wing(this));
        MapBuilder.addVariable("Save5", save5);
        MapBuilder.addVariable("fire ball UD", fireball);
        MapBuilder.addVariable("Rever", rever);
        MapBuilder.addVariable("Door Rever", doorRever);
        if(!SaveManager.getInstance().getCurrentSave().getDefeatedBosses().contains(0)){
            MapBuilder.addVariable("BossTrigger", trigger);
            MapBuilder.addVariable("wizard boss", mage);
        }
        MapBuilder.addVariable("zombie", new Zombie(this));
        MapBuilder.addVariable("skeleton", new Skeleton(this, 2, 4, 8));
        MapBuilder.addVariable("key boss", new BossKey(this));
        MapBuilder.addVariable("room boss", new RoomBoss(this));
        MapBuilder.addVariable("box", new PushBox(this));
        MapBuilder.addVariable("lava", new Lava(this));
        MapBuilder.addVariable("From4", marker3);
        MapBuilder.addVariable("CastleKey", new CastleKey(this));
        MapBuilder.addVariable("GoTo1", new GoTo1(this, manager));
        MapBuilder.addVariable("GoTo4", new GoTo4(this, manager));
        MapBuilder.setVariableClone("destroy", true);
        MapBuilder.setVariableClone("zombie", true);
        MapBuilder.setVariableClone("skeleton", true);
        MapBuilder.setVariableClone("GoTo1", true);
        MapBuilder.setVariableClone("GoTo4", true);
        MapBuilder.setVariableClone("box", true);
        MapBuilder.setVariableClone("lava", true);
        MapBuilder.setVariableClone("elevator", true);
        MapBuilder.setVariableClone("Spike", true);
        MapBuilder.setVariableClone("fire ball UD", true);
        MapBuilder.loadMap("map/Chapter3");
        
        manager.setCutscene(bossCutscene);
    }
    
}
