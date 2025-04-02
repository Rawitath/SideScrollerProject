/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Cutscenes;

import Datas.Vector2;
import Main.ChapterFive.Entities.ChapterFiveManager;
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
    private float witchWait = 2f;
    private float witchw;
    
    private boolean isonemove = false;
    private boolean istwomove = false;
    
    public MageCutscene(Scene s) {
        super(s);
    }
    
    @Override
    public void start(){
        super.start();
        setScript(new String[]{"เจ้าหนู แกเข้ามาถึงที่นี่ได้ยังไงกัน", "ชั่งเถอะไม่ต้องตอบแล้วเพราะดูทรงแล้วแกคงมาเอาของแกคินสินะ", "เฮ้อ คงต้องทำให้ไปคุยกับรากมะม่วงแล้วสิ"});
    }

    @Override
    public void update() {
        super.update();
        if(isCutscenePlaying()){
            if(!movePlayer1()) return;
            if(!witchMove()) return;
            if(!moveCameraBack()) return;
            if(!showDialogue()) return;
            ChapterManager manager = getScene().getEntity("Manager3");
            manager.setIsBoss(true);
            endCutscene();
        }
    }
    
    @Override
    public void onCutsceneTriggered() {
        lpos = getControlledEntities().get("Lucy").currentPosition().getX();
        pbp = getControlledEntities().get("Boss").currentPosition().getX();
    }
    
    private boolean showDialogue(){
        startDialouge();
        return isDialougeEnd();
    }
    
    private boolean moveCameraBack(){
        if(getScene().getCamera().getPosition().getX() > 65.5f){
            getScene().getCamera().setPosition(getScene().getCamera().getPosition().translate(Vector2.left(), 4 * Time.deltaTime()));
        }
        else{
            return true;
        }
        return false;
    }
    private boolean witchMove(){
        if(istwomove){
            return true;
        }
        if(Time.time() - witchw < witchWait){return false;}
        if(getControlledEntities().get("Boss").currentPosition().getX() - pbp > -2){
            getControlledEntities().get("Boss").moveLeft();
        }
        else{
            getControlledEntities().get("Boss").stop();
            istwomove = true;
            return true;
        }
        return false;
    }
    private boolean movePlayer1(){
        if(isonemove){
            return true;
        }
        if(getControlledEntities().get("Lucy").currentPosition().getX() < 62.5f){
                getControlledEntities().get("Lucy").moveRight();
        }
        else{
            getControlledEntities().get("Lucy").stop();
        }
        if(getScene().getCamera().getPosition().getX() < 70.5f){
            getScene().getCamera().setPosition(getScene().getCamera().getPosition().translate(Vector2.right(), 4 * Time.deltaTime()));
        }
        else{
            isonemove = true;
            return true;
        }
        witchw = Time.time();
        return false;
    }

    @Override
    public void fixedUpdate() {
        
    }
    
}
