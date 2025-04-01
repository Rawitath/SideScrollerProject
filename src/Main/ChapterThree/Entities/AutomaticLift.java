/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Datas.Vector2;
import Entities.Entity;
import Physics.Time;
import Scenes.Scene;
import java.util.Vector;

/**
 *
 * @author GA_IA
 */
public class AutomaticLift extends Lift{
    private boolean isUp = false;
    private boolean isWorking;
    private float speed = 2f;
    
    public AutomaticLift(Scene s) {
        super(s);
    }

    @Override
    public void start() {
        super.start();
        setBottomPosition(getPosition().getY() - 1);
        setTopPosition(getPosition().getY() + 11);
        isWorking = true;
    }
    
    @Override
    public void fixedUpdate() {
        super.fixedUpdate();
        if(isWorking){
            if(isUp){
            if(getPosition().getY() < getTopPosition()){
                setPosition(getPosition().translate(Vector2.up(), speed * Time.fixedDeltaTime()));
            }
            else{
                isUp = false;
            }
        }
        else{
            if(getPosition().getY() > getBottomPosition()){
                setPosition(getPosition().translate(Vector2.down(), speed * Time.fixedDeltaTime()));
            }
            else{
                isUp = true;
            }
        }
        }
    }

    public boolean isIsWorking() {
        return isWorking;
    }

    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    @Override
    public <T extends Entity> T copyOf() {
        Lift lift = new AutomaticLift(getScene());
        return (T) lift;
    }
}
