/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Cutscenes;

import Datas.Vector2;
import Entities.Entity;
import Main.ChapterFive.Entities.ChapterFiveManager;
import Main.GameSystem.Cutscene.DialogueCutscene;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class BossCutscene extends DialogueCutscene{
    
    private float pbp;
    private float lpos;
    
    public BossCutscene(Scene s) {
        super(s);
    }

    @Override
    public void start() {
        super.start();
        setScript(new String[]{"I'm a sheep", "King of hell, you know?"});
    }
    
    
    
    @Override
    public void update() {
        if(isCutscenePlaying()){
            if(!movePlayer1()) return;
            if(!moveBoss()) return;
            if(!showDialogue()) return;
            ChapterFiveManager manager = getScene().getEntity("Manager5");
            manager.setIsBoss(true);
            endCutscene();
        }
    }
    
    private boolean moveBoss(){
        if(getControlledEntities().get("Boss").currentPosition().getX() - pbp > -3){
                getControlledEntities().get("Boss").moveLeft();
                return false;
            }
            else{
                getControlledEntities().get("Boss").stop();
                return true;
            }
    }
    
    private boolean movePlayer1(){
        if(getControlledEntities().get("Lucy").currentPosition().getX() - lpos < 2){
                getControlledEntities().get("Lucy").moveRight();
            }
            else{
                getControlledEntities().get("Lucy").stop();
            }
        if(getScene().getCamera().getPosition().getX() < pbp - 3){
            getScene().getCamera().setPosition(getScene().getCamera().getPosition().translate(Vector2.right(), 4 * Time.deltaTime()));
        }
        else{
            return true;
        }
        return false;
    }
    private boolean showDialogue(){
        startDialouge();
        return isDialougeEnd();
    }

    @Override
    public void onCutsceneTriggered() {
        lpos = getControlledEntities().get("Lucy").currentPosition().getX();
        pbp = getControlledEntities().get("Boss").currentPosition().getX();
    }

    @Override
    public void fixedUpdate() {

    }
    
}
