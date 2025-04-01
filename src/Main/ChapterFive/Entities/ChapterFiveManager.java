/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Entities;

import Datas.Vector2;
import Entities.Entity;
import Inputs.KeyControlable;
import Main.Entities.Main.ChapterManager;
import Main.Entities.Main.Lucy;
import Main.GameSystem.Cutscene.Cutscene;
import Main.GameSystem.SavePoint.SavePoint;
import Main.UI.Main.LucyUISet;
import Physics.Time;
import Saves.GameSave;
import Saves.SaveManager;
import Scenes.Scene;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GA_IA
 */
public class ChapterFiveManager extends ChapterManager{
    
    private GameSave save = SaveManager.getInstance().getCurrentSave();
    private List<Entity> bosswalls;

    public ChapterFiveManager(Scene s, Lucy lucy, LucyUISet ui) {
        super(s, lucy, ui);
        bosswalls = new ArrayList<>();
        setInitialZoom(85f);
        setName("Manager5");
        setMinCameraLimit(new Vector2(-18.48f, 0.44f));
        setMaxCameraLimit(new Vector2(83.5f, 10.44f));
    }
    
    @Override
    public void setIsBoss(boolean isBoss){
        super.setIsBoss(isBoss);
        
        for(Entity e : bosswalls){
            BossWall b = (BossWall) e; 
            b.setIsActivated(isBoss);
        }
    }
    
    @Override
    public void start() {
        super.start();
        bosswalls = getScene().getEntities("BossWall");
        
        if(save.getCurrentCheckpoint() == null){
            getLucy().setPosition(getScene().getEntity("Mark1").getPosition());
            getScene().addEntity(getLucy());
        }
        else{
            for(Entity e : getScene().getEntities("Save")){
                SavePoint s = (SavePoint) e;
                if(save.getCurrentCheckpoint().equals(s.getSavePointID())){
                    getLucy().setPosition(s.getPosition());
                    getScene().addEntity(getLucy());
                    break;
                }
            }
        }
        
//        breakBlock = getScene().getEntities("Destroy");
//        
//        if(save.getOne_WallDestroy()){
//            for(Entity e : breakBlock){
//                BreakableBlock b = (BreakableBlock) e;
//                b.destroy();
//            }
//        }
        super.start();
    }   
}
