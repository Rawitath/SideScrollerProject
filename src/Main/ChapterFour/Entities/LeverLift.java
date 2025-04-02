/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Entities;

import Datas.Vector2;
import Entities.Entity;
import Main.ChapterThree.Entities.Lift;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class LeverLift extends Lift{

    private LiftLever lever;
    
    public LeverLift(Scene s, LiftLever l) {
        super(s);
        lever = l;
    }

    @Override
    public void start() {
        super.start();
        setBottomPosition(getPosition().getY());
        setTopPosition(getPosition().getY() + 3);
    }
    
    @Override
    public void fixedUpdate() {
        super.fixedUpdate();
            if(lever.isIsOn()){
            if(getPosition().getY() < getTopPosition()){
                setPosition(getPosition().translate(Vector2.up(), 2 * Time.fixedDeltaTime()));
            }
            else{
                lever.setLever(false);
            }
        }
        else{
            if(getPosition().getY() > getBottomPosition()){
                setPosition(getPosition().translate(Vector2.down(), 2 * Time.fixedDeltaTime()));
            }
        }
        
    }
    
    @Override
    public <T extends Entity> T copyOf() {
        return null;
    }
    
}
