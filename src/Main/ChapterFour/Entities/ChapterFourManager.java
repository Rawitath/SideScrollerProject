/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Entities;

import Datas.Vector2;
import Entities.Entity;
import Main.Entities.Main.ChapterManager;
import Main.Entities.Main.Lucy;
import Main.GameSystem.Cutscene.Cutscene;
import Main.GameSystem.SavePoint.SavePoint;
import Main.UI.Main.LucyUISet;
import Saves.GameSave;
import Saves.SaveManager;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterFourManager extends ChapterManager{
    private GameSave save = SaveManager.getInstance().getCurrentSave();
    public ChapterFourManager(Scene s, Lucy lucy, LucyUISet ui) {
        super(s, lucy, ui);
        setInitialZoom(85f);
        setName("Manager4");
        setMinCameraLimit(new Vector2(-17.39f, -8.55f));
        setMaxCameraLimit(new Vector2(21.5f, 10.44f));
    }
    @Override
    public void start() {
        if(save.getCurrentCheckpoint() == null){
            if(SaveManager.getInstance().getCurrentSave().getMarkerName() != null){
                getLucy().setPosition(getScene().getEntity(SaveManager.getInstance().getCurrentSave().getMarkerName()).getPosition());
                getLucy().setHealth(SaveManager.getInstance().getCurrentSave().getCurrentHearts());
                getScene().addEntity(getLucy());
                SaveManager.getInstance().getCurrentSave().setCurrentHearts(null);
                SaveManager.getInstance().getCurrentSave().setMarkerName(null);
            }
            else{
                getLucy().setPosition(getScene().getEntity("Mark1").getPosition());
            getScene().addEntity(getLucy());
            }
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
