/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Scenes;


import Datas.Vector2;
import Main.ChapterOne.Entities.Background.*;
import Main.ChapterOne.Entities.Lava;
import Main.ChapterOne.Entities.PushBox;
import Main.ChapterThree.Entities.AutomaticLift;
import Main.ChapterThree.Entities.Background.CaveBG0;
import Main.ChapterThree.Entities.BossKey;
import Main.ChapterThree.Entities.ChapterThreeManager;
import Main.ChapterThree.Entities.DoorRever;
import Main.ChapterThree.Entities.Fireball;
import Main.ChapterThree.Entities.Lever;
import Main.ChapterThree.Entities.Lift;
import Main.ChapterThree.Entities.Rever;
import Main.ChapterThree.Entities.RoomBoss;
import Main.ChapterThree.Entities.VerticalFireball;
import Main.ChapterTwo.Entities.Spike;
import Main.Entities.Main.Lucy;
import Main.UI.Main.LucyUISet;
import Maps.MapBuilder;
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
        
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("spaw for 2", lucy);
        MapBuilder.addVariable("Spike", new Spike(this));
        MapBuilder.addVariable("elevator", lift);
        MapBuilder.addVariable("fire ball UD", fireball);
        MapBuilder.addVariable("Rever", rever);
        MapBuilder.addVariable("Door Rever", doorRever);
        MapBuilder.addVariable("key boss", new BossKey(this));
        MapBuilder.addVariable("room boss", new RoomBoss(this));
        MapBuilder.addVariable("box", new PushBox(this));
        MapBuilder.addVariable("lava", new Lava(this));
        MapBuilder.setVariableClone("box", true);
        MapBuilder.setVariableClone("lava", true);
        MapBuilder.setVariableClone("elevator", true);
        MapBuilder.setVariableClone("Spike", true);
        MapBuilder.setVariableClone("fire ball UD", true);
        MapBuilder.loadMap("map/Chapter3");
    }
    
}
