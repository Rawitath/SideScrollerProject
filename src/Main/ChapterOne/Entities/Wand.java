/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Entities;

import Datas.Vector2;
import Entities.CollidableEntity;
import Entities.Entity;
import Main.Entities.Main.Interactable;
import Main.Entities.Main.Lucy;
import Physics.Collider;
import Physics.Time;
import Saves.SaveManager;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Wand extends CollidableEntity implements Interactable{

    private float fadeTime = 1.5f;
    private float previous;
    private boolean achieved = false;
    
    public Wand(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/chapter/kata.png"));
        setSpriteSize(getSpriteSize().multiply(0.2f));
        setScale(getScale().multiply(0.65f));
        setAnchor(new Vector2(0,0.3f));
        setTag("Key Item");
    }

    @Override
    public void start() {
        
    }

    @Override
    public void update() {
        if(SaveManager.getInstance().getCurrentSave().getWandAchived().equals(true)){
            getScene().removeEntity(this);
        }
        if(achieved){
            if(Time.time() - previous < fadeTime){
                if(getAlpha() - (1 / fadeTime) * Time.deltaTime() >= 0){
                    setAlpha(getAlpha() - (1 / fadeTime) * Time.deltaTime());
                }
            }
            else{
                getScene().removeEntity(this);
            }
        }
    }

    @Override
    public void fixedUpdate() {

    }

    @Override
    public void onColliderEnter(Collider other) {

    }

    @Override
    public void onColliderStay(Collider other) {

    }

    @Override
    public void onColliderExit(Collider other) {

    }

    @Override
    public void interact(Entity interactor) {
        achieved = true;
        SaveManager.getInstance().getCurrentSave().setWandAchived(true);
        SaveManager.getInstance().saveCurrentSave();
        if(interactor instanceof Lucy l){
            l.refreshSave();
        }
        previous = Time.time();
    }
    
}
