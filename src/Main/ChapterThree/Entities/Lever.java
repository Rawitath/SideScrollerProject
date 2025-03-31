/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Entities.CollidableEntity;
import Main.Entities.Main.Interactable;
import Physics.Collider;
import Scenes.Scene;
import Utilities.FileReader;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public class Lever extends CollidableEntity implements Interactable{

    private boolean isOn = false;
    private BufferedImage offSprite;
    private BufferedImage onSprite;
    private int leverID;
    
    public Lever(Scene s) {
        super(s);
        offSprite = FileReader.readImage("res/game/chapter/lever_1.png");
        onSprite = FileReader.readImage("res/game/chapter/lever_2.png");
        setSprite(offSprite);
        isOn = false;
    }

    public int getLeverID() {
        return leverID;
    }

    protected void setLeverID(int leverID) {
        this.leverID = leverID;
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

    @Override
    public void onColliderEnter(Collider other) {

    }

    @Override
    public void onColliderStay(Collider other) {

    }

    @Override
    public void onColliderExit(Collider other) {

    }

    public boolean isIsOn() {
        return isOn;
    }
    
    public void setLever(boolean isOn){
        if(this.isOn == isOn){
            return;
        }
        this.isOn = isOn;
        if(isOn){
            setSprite(onSprite);
        }
        else{
            setSprite(offSprite);
        }
    }

    @Override
    public void interact() {
        if(!isOn){
            setLever(true);
        }
    }
}
