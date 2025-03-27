/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.GameSystem.Cutscene;

import Datas.Vector2;
import Entities.Entity;
import Main.Entities.Example.Boss;
import Main.UI.Example.BoxDialogueTest1;
import Physics.Collider;
import Physics.Time;
import Saves.GameSave;
import Saves.SaveManager;
import Saves.SaveSerializer;
import Scenes.Scene;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author GA_IA
 * @author nirawith2548gmail.com
 */
public abstract class Cutscene extends Entity{
    
    private Map<String, CutsceneControllable> controlledEntities;
    private boolean isCutscenePlaying;
    
    public Cutscene(Scene s){
        super(s);
        isCutscenePlaying = false;
        controlledEntities = new HashMap<>();
    }

    @Override
    public void start() {
//        this.setPosition(new Vector2(20.5f, -10f));
//        this.getCollider().setBound(new Vector2(10f, 10f));
    }

    public boolean isCutscenePlaying() {
        return isCutscenePlaying;
    }
    
    @Override
    public void update() {
    }
    
    public void addControlledEntities(String key, CutsceneControllable c){
        controlledEntities.put(key, c);
    }
    public void removeControlledEntities(String key, CutsceneControllable c){
        controlledEntities.remove(key, c);
    }

    public Map<String, CutsceneControllable> getControlledEntities() {
        return controlledEntities;
    }
    
    public void triggerCutscene(){
        if(isCutscenePlaying){
            return;
        }
        isCutscenePlaying = true;
        onCutsceneTriggered();
    }
    
    public abstract void onCutsceneTriggered();
    
    public void endCutscene(){
        isCutscenePlaying = false;
    }
}
