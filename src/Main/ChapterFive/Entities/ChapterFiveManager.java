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

/**
 *
 * @author GA_IA
 */
public class ChapterFiveManager extends Entity{
    
    private boolean isBoss;
    private Lucy lucy;
    private Cutscene cutscene;

    public ChapterFiveManager(Scene s, Lucy lucy) {
        super(s);
        isBoss = false;
        this.lucy = lucy;
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
    }

    @Override
    public void start() {
        getScene().getCamera().setZoom(85f);
    }

    @Override
    public void update() {
        if(!isBoss && !cutscene.isCutscenePlaying()){
            getScene().getCamera().setPosition(lucy.getPosition().multiply(Vector2.negativeY()));
        }
        else{
            if(cutscene.isCutscenePlaying()){
                lucy.setBreakControl(true);
            }
            else{
                lucy.setBreakControl(false);
            }
        }
    }

    @Override
    public void fixedUpdate() {
        
    }
}
