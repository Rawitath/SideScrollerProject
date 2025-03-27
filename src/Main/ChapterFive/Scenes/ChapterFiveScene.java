/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Scenes;

import Main.ChapterFive.Entities.SheepBoss;
import Main.Entities.Main.Lucy;
import Maps.MapBuilder;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterFiveScene extends Scene {

    @Override
    public void load() {
        getCamera().setZoom(20f);
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("debugBoss", new Lucy(this));
        MapBuilder.addVariable("last boss", new SheepBoss(this));
        MapBuilder.loadMap("map/Chapter5");
    }
    
}
