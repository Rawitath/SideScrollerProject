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
    
    public AutomaticLift(Scene s) {
        super(s);
    }

    @Override
    public void start() {
        super.start();
        isWorking = true;
    }
    
    @Override
    public void fixedUpdate() {
        super.fixedUpdate();
        if(isWorking){
            if(!isUp){
            if(distanceBetweenBottom() > 0){
                setPosition(getPosition().translate(Vector2.up(), 2 * Time.fixedDeltaTime()));
            }
            else{
                isUp = false;
            }
        }
        else{
            if(distanceBetweenTop() > 0){
                setPosition(getPosition().translate(Vector2.down(), 2 * Time.fixedDeltaTime()));
            }
            else{
                isUp = true;
            }
        }
        }
    }
    
    @Override
    public <T extends Entity> T copyOf() {
        Lift lift = new AutomaticLift(getScene());
        return (T) lift;
    }
}
