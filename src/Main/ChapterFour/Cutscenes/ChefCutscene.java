/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Cutscenes;

import Datas.Vector2;
import Main.Entities.Main.ChapterManager;
import Main.GameSystem.Cutscene.DialogueCutscene;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChefCutscene extends DialogueCutscene{

    private Vector2 lucyPos;
    private Vector2 camPos;
    public ChefCutscene(Scene s) {
        super(s);
    }
    
    @Override
    public void start(){
        super.start();
        setScript(new String[]{"Wish to see your own world?", "Just dream under my fire"});
    }

    @Override
    public void update() {
        super.update();
        if(isCutscenePlaying()){
            if(Math.abs(getControlledEntities().get("Lucy").currentPosition().getX() - lucyPos.getX()) < 3){
                getControlledEntities().get("Lucy").moveRight();
            }
            else{
                getControlledEntities().get("Lucy").stop();
            }
            if(!moveCameraLeft()){return;}
            if(!moveCameraUp()){return;}
            if(!showDialogue()) return;
            ChapterManager manager = getScene().getEntity("Manager4");
            manager.setIsBoss(true);
            endCutscene();
        }
    }
    
    private boolean showDialogue(){
        startDialouge();
        return isDialougeEnd();
    }
    
    private boolean moveCameraUp(){
        if(getScene().getCamera().getPosition().getY() <= -11f){
            return true;
        }
        getScene().getCamera().setPosition(getScene().getCamera().getPosition().translate(Vector2.down(), 4 * Time.deltaTime()));
        return false;
    }
    
    private boolean moveCameraLeft(){
        if(getScene().getCamera().getPosition().getX() >= 18.5f){
            return true;
        }
        getScene().getCamera().setPosition(getScene().getCamera().getPosition().translate(Vector2.right(), 4 * Time.deltaTime()));
        return false;
    }

    @Override
    public void onCutsceneTriggered() {
        lucyPos = getControlledEntities().get("Lucy").currentPosition();
        camPos = getScene().getCamera().getPosition();
    }

    @Override
    public void fixedUpdate() {

    }
    
}
