/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFive.Cutscenes;

import Datas.Vector2;
import Entities.UI.UIImage;
import Main.Entities.Main.ScreenFade;
import Main.GameSystem.Cutscene.Cutscene;
import Physics.Time;
import Saves.SaveManager;
import Scenes.Scene;
import Scenes.SceneManager;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class FinalCutscene extends Cutscene{

    private float camX;
    private float lucyX;
    private UIImage fade;
    
    public FinalCutscene(Scene s) {
        super(s);
        fade = new ScreenFade(s);
        fade.setImage(FileReader.readImage("res/default/whitesquare.png"));
        fade.setAlpha(0f);
        s.addEntity(fade);
    }

    @Override
    public void update() {
        if(isCutscenePlaying()){
            if(!movePlayer1()) {return;}
            if(!fade()){return;}
            SceneManager.loadScene(0);
        }
    }
    
    private boolean fade(){
        getControlledEntities().get("Lucy").moveRight();
        if(fade.getAlpha() + 0.3f * Time.deltaTime() < 1f){
            fade.setAlpha(fade.getAlpha() + 0.3f * Time.deltaTime());
        }
        else{
            return true;
        }
        return false;
    }
    private boolean movePlayer1(){
        if(getScene().getCamera().getPosition().getX() <  camX + 5){
            getScene().getCamera().setPosition(getScene().getCamera().getPosition().translate(Vector2.right(), 4 * Time.deltaTime()));
        }
        else{
            return true;
        }
        return false;
    }

    @Override
    public void onCutsceneTriggered() {
        camX = getScene().getCamera().getPosition().getX();
        lucyX = getControlledEntities().get("Lucy").currentPosition().getX();
    }

    @Override
    public void fixedUpdate() {

    }
    
}
