/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Main;

import Datas.Vector2;
import Entities.Camera;
import Entities.Entity;
import Inputs.KeyControlable;
import Main.GameSystem.Cutscene.Cutscene;
import Main.UI.Main.LucyUISet;
import Saves.GameSave;
import Saves.SaveManager;
import Scenes.Scene;
import java.awt.event.KeyEvent;

/**
 *
 * @author GA_IA
 */
public class ChapterManager extends Entity{
    
    private GameSave save;
    
    private boolean isBoss;
    private Lucy lucy;
    private Cutscene cutscene;
    private LucyUISet ui;
    private float initialZoom = 85f;
    private Vector2 minCameraLimit = new Vector2();
    private Vector2 maxCameraLimit = new Vector2();
    protected boolean freeCamera = false;

    public ChapterManager(Scene s, Lucy lucy, LucyUISet ui) {
        super(s);
        isBoss = false;
        this.lucy = lucy;
        this.ui = ui;
        s.addEntity(ui);
        
        save = SaveManager.getInstance().getCurrentSave();
        lucy.setSave(save);
    }

    public Vector2 getMinCameraLimit() {
        return minCameraLimit;
    }

    public void setMinCameraLimit(Vector2 minCameraLimit) {
        this.minCameraLimit = minCameraLimit;
    }

    public Vector2 getMaxCameraLimit() {
        return maxCameraLimit;
    }

    public void setMaxCameraLimit(Vector2 maxCameraLimit) {
        this.maxCameraLimit = maxCameraLimit;
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
        Camera camera = getScene().getCamera();
        camera.setZoom(initialZoom);
        camera.setPosition(lucy.getPosition().multiply(Vector2.negativeY()));
        
        if(camera.getPosition().getX() > maxCameraLimit.getX()){
            camera.setPosition(new Vector2(maxCameraLimit.getX(), camera.getPosition().getY()));
        }
        if(camera.getPosition().getX() < minCameraLimit.getX()){
            camera.setPosition(new Vector2(minCameraLimit.getX(), camera.getPosition().getY()));
        }
        if(camera.getPosition().getY() > maxCameraLimit.getY()){
            camera.setPosition(new Vector2(camera.getPosition().getX(), maxCameraLimit.getY()));
        }
        else if(camera.getPosition().getY() < minCameraLimit.getY()){
            camera.setPosition(new Vector2(camera.getPosition().getX(), minCameraLimit.getY()));
        }
    }

    @Override
    public void update() {
        ui.getHeartFrame().setCurrrentHeart(lucy.getHealth());
        
        if(!isBoss && (cutscene == null || !cutscene.isCutscenePlaying())){
            ui.setActive(true);
            Camera camera = getScene().getCamera();
            
            if(freeCamera){
                camera.setPosition(lucy.getPosition().multiply(Vector2.negativeY()));
            }
            else{
                if(lucy.getPosition().getX() <= maxCameraLimit.getX() && lucy.getPosition().getX() >= minCameraLimit.getX()){
                camera.setPosition(new Vector2(lucy.getPosition().getX(), camera.getPosition().getY()));
                }
                if(-lucy.getPosition().getY() <= maxCameraLimit.getY() && -lucy.getPosition().getY() >= minCameraLimit.getY()){
                    camera.setPosition(new Vector2(camera.getPosition().getX(), -lucy.getPosition().getY()));
                }
            }
            
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
