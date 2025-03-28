/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Datas.Vector2;
import Entities.Entity;
import Main.Entities.Main.Lucy;
import Main.GameSystem.Cutscene.Cutscene;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterThreeManager extends Entity{
    private boolean isBoss;
    private Lucy lucy;
    private Cutscene cutscene;

    public ChapterThreeManager(Scene s, Lucy lucy) {
        super(s);
        isBoss = false;
        this.lucy = lucy;
        setName("Manager3");
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
    }

    @Override
    public void start() {
        getScene().getCamera().setZoom(95f);
    }

    @Override
    public void update() {
        if(!isBoss && (cutscene == null || !cutscene.isCutscenePlaying())){
            getScene().getCamera().setPosition(lucy.getPosition().multiply(Vector2.negativeY()));
        }
        else{
            if(cutscene != null){
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
    }

    @Override
    public void fixedUpdate() {
        
    }
}
