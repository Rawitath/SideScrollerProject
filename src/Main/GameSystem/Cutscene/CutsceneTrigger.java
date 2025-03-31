/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.GameSystem.Cutscene;

import Entities.CollidableEntity;
import Physics.Collider;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class CutsceneTrigger extends CollidableEntity{

    private Cutscene cutscene;
    private String triggerTag;
    public boolean oneTimeUse = true;
    public CutsceneTrigger(Scene s, Cutscene c) {
        super(s);
        cutscene = c;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {

    }

    public String getTriggerTag() {
        return triggerTag;
    }

    public void setTriggerTag(String triggerTag) {
        this.triggerTag = triggerTag;
    }
    
    @Override
    public void onColliderEnter(Collider other) {
        if(triggerTag == null || other.getEntity().getTag().equals(triggerTag)){
            cutscene.triggerCutscene();
            if(oneTimeUse);
            getScene().removeEntity(this);
        }
    }

    @Override
    public void onColliderStay(Collider other) {

    }

    @Override
    public void onColliderExit(Collider other) {

    }
    
}
