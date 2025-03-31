/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Entities;

import Entities.Entity;
import Main.ChapterThree.Entities.AutomaticLift;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class Elevator extends AutomaticLift{
    
    public Elevator(Scene s) {
        super(s);
    }

    @Override
    public void start() {
        setBottomPosition(getPosition().getY() );
        setTopPosition(getPosition().getY() + 7);
        setSpeed(0.9f);
        setIsWorking(true);
    }
    
    

    @Override
    public <T extends Entity> T copyOf() {
        return (T) new Elevator(getScene());
    }
    
}
