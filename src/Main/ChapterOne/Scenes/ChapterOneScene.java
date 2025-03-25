/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Scenes;
import Main.Entities.Main.Lucy;
import Maps.MapBuilder;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterOneScene extends Scene{

    @Override
    public void load() {
        getCamera().setZoom(20f);
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("Lucy", new Lucy(this));
        MapBuilder.loadMap("map/MyMap");
    }
    
}
