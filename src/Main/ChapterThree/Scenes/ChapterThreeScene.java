/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Scenes;

import Maps.MapBuilder;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterThreeScene extends Scene{

    @Override
    public void load() {
        getCamera().setZoom(20f);
        MapBuilder.useMapBuilder(this);
        MapBuilder.loadMap("map/Chapter3");
    }
    
}
