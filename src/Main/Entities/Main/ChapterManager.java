/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Main;

import Datas.Vector2;
import Entities.Entity;
import Inputs.KeyControlable;
import Main.GameSystem.Cutscene.Cutscene;
import Main.UI.Main.LucyUISet;
import Scenes.Scene;
import java.awt.event.KeyEvent;

/**
 *
 * @author GA_IA
 */
public class ChapterManager extends Entity{
    private boolean isBoss;
    private Lucy lucy;
    private Cutscene cutscene;
    private LucyUISet ui;
    private float initialZoom = 85f;

    public ChapterManager(Scene s, Lucy lucy, LucyUISet ui) {
        super(s);
        isBoss = false;
        this.lucy = lucy;
        this.ui = ui;
        s.addEntity(ui);
    }

    public float getInitialZoom() {
        return initialZoom;
    }

    protected void setInitialZoom(float initialZoom) {
        this.initialZoom = initialZoom;
    }
    
    public Lucy getLucy() {
        return lucy;
    }

    protected void setLucy(Lucy lucy) {
        this.lucy = lucy;
    }

    public LucyUISet getUi() {
        return ui;
    }

    protected void setUi(LucyUISet ui) {
        this.ui = ui;
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
        getScene().getCamera().setZoom(initialZoom);
    }

    @Override
    public void update() {
        ui.getHeartFrame().setCurrrentHeart(lucy.getHealth());
        
        if(!isBoss && (cutscene == null || !cutscene.isCutscenePlaying())){
            ui.setActive(true);
            getScene().getCamera().setPosition(lucy.getPosition().multiply(Vector2.negativeY()));
            if(lucy.getHealth() > 0){
                ui.setActive(true);
                
                if(lucy.isStatsOpen()){
                    ui.getHeartFrame().setActive(false);
                    ui.getInventory().setActive(false);
                    ui.getStatPanel().setActive(true);
                    lucy.setBreakControl(true);
                }
                else{
                    ui.getHeartFrame().setActive(true);
                    ui.getInventory().setActive(true);
                    ui.getStatPanel().setActive(false);
                    lucy.setBreakControl(false);
                }
            }
            else{
                lucy.setBreakControl(true);
                ui.setActive(false);
            }
        }
        else{
            if(cutscene.isCutscenePlaying()){
                ui.setActive(false);
                lucy.setBreakControl(true);
            }
            else{
                if(lucy.getHealth() > 0){
                    ui.setActive(true);
                    lucy.setBreakControl(false);
                }
                else{
                    lucy.setBreakControl(true);
                    ui.setActive(false);
                }
            }
        }
    }

    @Override
    public void fixedUpdate() {
        
    }
}
