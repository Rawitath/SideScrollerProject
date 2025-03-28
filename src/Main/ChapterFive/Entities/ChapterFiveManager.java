/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Entities;

import Datas.Vector2;
import Entities.Entity;
import Main.Entities.Main.Lucy;
import Main.GameSystem.Cutscene.Cutscene;
import Scenes.Scene;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GA_IA
 */
public class ChapterFiveManager extends Entity{
    
    private boolean isBoss;
    private Lucy lucy;
    private Cutscene cutscene;
    private List<Entity> bosswalls;

    public ChapterFiveManager(Scene s, Lucy lucy) {
        super(s);
        isBoss = false;
        this.lucy = lucy;
        bosswalls = new ArrayList<>();
        setName("Manager5");
    }

    public Cutscene getCutscene() {
        return cutscene;
    }

    public void setCutscene(Cutscene cutscene) {
        this.cutscene = cutscene;
    }
    
    public boolean isBoss(){
        return isBoss;
    }
    public void setIsBoss(boolean isBoss){
        this.isBoss = isBoss;
        for(Entity e : bosswalls){
            BossWall b = (BossWall) e; 
            b.setIsActivated(isBoss);
        }
    }
    
    @Override
    public void start() {
        getScene().getCamera().setZoom(85f);
        bosswalls = getScene().getEntities("BossWall");
    }

    @Override
    public void update() {
        if(!isBoss && (cutscene == null || !cutscene.isCutscenePlaying())){
            getScene().getCamera().setPosition(lucy.getPosition().multiply(Vector2.negativeY()));
        }
        else{
            if(cutscene.isCutscenePlaying()){
                lucy.setBreakControl(true);
            }
            else{
                if(lucy.getHealth() > 0){
                    lucy.setBreakControl(false);
                }
            }
        }
    }

    @Override
    public void fixedUpdate() {
        
    }
}
