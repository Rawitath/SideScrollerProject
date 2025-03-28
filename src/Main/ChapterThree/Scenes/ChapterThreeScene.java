/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Scenes;

import Main.Entities.Main.Lucy;
import Maps.MapBuilder;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterThreeScene extends Scene{

    @Override
    public void load() {
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("spaw for 2", new Lucy(this));
        MapBuilder.loadMap("map/Chapter3");
    }
    
}
