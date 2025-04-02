/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.GameSystem.Door;

import Entities.CollidableEntity;
import Physics.Collider;
import Physics.Time;
import Saves.SaveManager;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class Door extends CollidableEntity{
    protected Integer doorID;
    private float fadeTime = 1.5f;
    private float previous;
    private boolean isOpen = false;
    
    public Door(Scene s) {
        super(s);
        getCollider().setSolid(true);
    }
    

    @Override
    public void start() {
        
    }

    @Override
    public void update() {
        if(SaveManager.getInstance().getCurrentSave().getUnlockedDoors().contains(doorID)){
            setActive(false);
            getScene().removeEntity(this);
        }
        if(isOpen){
            if(Time.time() - previous < fadeTime){
                if(getAlpha() - (1 / fadeTime) * Time.deltaTime() >= 0){
                    setAlpha(getAlpha() - (1 / fadeTime) * Time.deltaTime());
                }
            }
            else{
                SaveManager.getInstance().getCurrentSave().getUnlockedDoors().add(doorID);
                getScene().removeEntity(this);
            }
        }
    }

    @Override
    public void fixedUpdate() {

    }

    public boolean isIsOpen() {
        return isOpen;
    }
    protected void setIsOpen(boolean isOpen){
        this.isOpen = isOpen;
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
    
    public boolean open(Key key){
        if(key.getKeyID().equals(doorID)){
            previous = Time.time();
            isOpen = true;
            return true;
        }
        return false;
    }

    public Integer getDoorID() {
        return doorID;
    }
}
