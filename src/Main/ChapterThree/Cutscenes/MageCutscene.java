/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Cutscenes;

import Datas.Vector2;
import Main.Entities.Main.ChapterManager;
import Main.GameSystem.Cutscene.DialogueCutscene;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class MageCutscene extends DialogueCutscene{

    private float pbp;
    private float lpos;
    
    public MageCutscene(Scene s) {
        super(s);
    }
    
    @Override
    public void start(){
        super.start();
        setScript(new String[]{"Wish to see your own world?", "Just dream under my magic?"});
    }

    @Override
    public void update() {
        super.update();
        if(isCutscenePlaying()){
            if(!movePlayer1()) return;
        }
    }
    
    

    @Override
    public void onCutsceneTriggered() {
        lpos = getControlledEntities().get("Lucy").currentPosition().getX();
        //pbp = getControlledEntities().get("Boss").currentPosition().getX();
    }
    private boolean movePlayer1(){
        if(getControlledEntities().get("Lucy").currentPosition().getX() < 62.5f){
                getControlledEntities().get("Lucy").moveRight();
            }
            else{
                getControlledEntities().get("Lucy").stop();
            }
        if(getScene().getCamera().getPosition().getX() < 66.5f){
            getScene().getCamera().setPosition(getScene().getCamera().getPosition().translate(Vector2.right(), 4 * Time.deltaTime()));
        }
        else{
            return true;
        }
        return false;
    }

    @Override
    public void fixedUpdate() {
        
    }
    
}
