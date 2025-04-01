/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Scenes;

import Main.ChapterFive.Entities.Background.*;
import Main.ChapterFour.Entities.ChapterFourManager;
import Main.ChapterFour.Entities.Elevator;
import Main.ChapterFour.Entities.FireballLeftRight;
import Main.ChapterFour.Entities.FireballUpDown;
import Main.ChapterFour.Entities.LeverLift;
import Main.ChapterFour.Entities.LiftLever;
import Main.ChapterFour.Entities.StaticFire;
import Main.ChapterThree.Entities.VerticalFireball;
import Main.ChapterTwo.Entities.Spike;
import Main.Entities.Main.Lucy;
import Main.GameSystem.SavePoint.SavePoint;
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
    private LeverLift lift;
    private LiftLever lever;
    private SavePoint save6;
    private SavePoint save7;
    private SavePoint save8;
    
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
        
        lever = new LiftLever(this);
        lift = new LeverLift(this, lever);
        
        save6 = new SavePoint(this);
        save6.setSavePointID(6);
        save7 = new SavePoint(this);
        save7.setSavePointID(7);
        save8 = new SavePoint(this);
        save8.setSavePointID(8);
        
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("Entry1", lucy);
        MapBuilder.addVariable("fire ball UD", new FireballUpDown(this));
        MapBuilder.addVariable("Lever", lever);
        MapBuilder.addVariable("Save6", save6);
        MapBuilder.addVariable("Save7", save7);
        MapBuilder.addVariable("Save8", save8);
        MapBuilder.addVariable("fire", new StaticFire(this));
        MapBuilder.addVariable("spike", new Spike(this));
        MapBuilder.addVariable("elvevator", new Elevator(this));
        MapBuilder.addVariable("fire ball LR", new FireballLeftRight(this));
        MapBuilder.setVariableClone("fire ball LR", true);
        MapBuilder.setVariableClone("elvevator", true);
        MapBuilder.setVariableClone("fire", true);
        MapBuilder.setVariableClone("spike", true);
        MapBuilder.addVariable("leverlift", lift);
        MapBuilder.loadMap("map/Chapter4");
    }
    
}
