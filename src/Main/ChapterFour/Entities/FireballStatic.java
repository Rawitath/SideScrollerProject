/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Entities;

import Datas.Vector2;
import Entities.Copyable;
import Entities.Entity;
import Main.ChapterThree.Entities.Fireball;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class FireballStatic extends Fireball{
    
    private Vector2 begin;
    
    public FireballStatic(Scene s) {
        super(s);
    }

    @Override
    public void start() {
        super.start();
        begin = getPosition().add(new Vector2(0, 2));
        setFlip(Vector2.negativeY());
    }

    @Override
    public void fixedUpdate() {
        super.fixedUpdate();
        if(getPosition().getY() < begin.getY() - 6){
            setPosition(begin);
        }
        setPosition(getPosition().translate(Vector2.down(), 1 * Time.fixedDeltaTime()));
    }
    
    

    @Override
    public <T extends Entity> T copyOf() {
        FireballStatic fireball = new FireballStatic(getScene());
        return (T) fireball;
    }
    
}
