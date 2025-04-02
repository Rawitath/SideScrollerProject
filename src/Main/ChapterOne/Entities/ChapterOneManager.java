/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Entities;

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
import java.util.List;

/**
 *
 * @author GA_IA
 */
public class ChapterOneManager extends ChapterManager{
    
    private GameSave save = SaveManager.getInstance().getCurrentSave();
    private List<Entity> touchBlock;
    private List<Entity> breakBlock;
    
    public ChapterOneManager(Scene s, Lucy lucy, LucyUISet ui) {
        super(s, lucy, ui);
        setInitialZoom(85f);
        setName("Manager1");
        setMinCameraLimit(new Vector2(-9.51f, -3.55f));
        setMaxCameraLimit(new Vector2(19.43f, 4.96f));
    }

    @Override
    public void start() {
        if(save.getCurrentCheckpoint() == null){
            getLucy().setPosition(getScene().getEntity("Mark1").getPosition());
            getScene().addEntity(getLucy());
        }
        else{
           if(SaveManager.getInstance().getCurrentSave().getMarkerName() != null){
                getLucy().setPosition(getScene().getEntity(SaveManager.getInstance().getCurrentSave().getMarkerName()).getPosition());
                getLucy().setHealth(SaveManager.getInstance().getCurrentSave().getCurrentHearts());
                getScene().addEntity(getLucy());
                SaveManager.getInstance().getCurrentSave().setCurrentHearts(null);
                SaveManager.getInstance().getCurrentSave().setMarkerName(null);
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
        }
        
        touchBlock = getScene().getEntities("Drop");
        breakBlock = getScene().getEntities("Destroy");
        
        
        
        super.start();
    }

    @Override
    public void update() {
        super.update();
        if(!touchBlock.isEmpty()){
            
        }
        if(save.getOne_GroundDrop()){
            for(Entity e : touchBlock){
                BreakableBlock b = (BreakableBlock) e;
                b.destroy();
            }
        }
    }
    
}
