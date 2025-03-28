/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Scenes;
import Main.ChapterOne.Entities.ChapterOneManager;
import Main.ChapterOne.Entities.Lava;
import Main.ChapterOne.Entities.PushBox;
import Main.Entities.Main.Lucy;
import Maps.MapBuilder;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterOneScene extends Scene{

    private Lucy lucy;
    private ChapterOneManager manager;
    @Override
    public void load() {
        lucy = new Lucy(this);
        manager = new ChapterOneManager(this, lucy);
        
        addEntity(manager);
        
        MapBuilder.useMapBuilder(this);
        MapBuilder.addVariable("Spawn", lucy);
        MapBuilder.addVariable("Lava", new Lava(this));
        MapBuilder.addVariable("Box", new PushBox(this));
        MapBuilder.setVariableClone("Box", true);
        MapBuilder.setVariableClone("Lava", true);
        MapBuilder.loadMap("map/Chapter1");
    }
    
}
