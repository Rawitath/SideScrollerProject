/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Datas.Vector2;
import Datas.Vector2Int;
import Entities.CollidableEntity;
import Entities.Copyable;
import Entities.Entity;
import Physics.Collider;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public abstract class Lift extends CollidableEntity implements Copyable{

    private float bottomPosition;
    private float topPosition;
    private boolean startFromTop = false;
    
    public Lift(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/chapter/lift1.png"));
        setSpriteSize(getSpriteSize().multiply(1.5f));
        getCollider().setSolid(true);
    }

    public float getBottomPosition() {
        return bottomPosition;
    }

    public void setBottomPosition(float bottomPosition) {
        this.bottomPosition = bottomPosition;
    }

    public float getTopPosition() {
        return topPosition;
    }

    public void setTopPosition(float topPosition) {
        this.topPosition = topPosition;
    }    

    public boolean isStartFromTop() {
        return startFromTop;
    }

    public void setStartFromTop(boolean startFromTop) {
        this.startFromTop = startFromTop;
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
//    public float distanceBetweenTop(){
//        return (float) Math.sqrt(Math.pow(topPosition.getX() - getPosition().getX(), 2) + Math.pow(topPosition.getY() - getPosition().getY(), 2));
//    }
//    public float distanceBetweenBottom(){
//        return (float) Math.sqrt(Math.pow(bottomPosition.getX() - getPosition().getX(), 2) + Math.pow(bottomPosition.getY() - getPosition().getY(), 2));
//    }
}
