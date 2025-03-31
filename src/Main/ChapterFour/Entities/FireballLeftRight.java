/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Entities;

import Datas.Vector2;
import Entities.Entity;
import Main.ChapterThree.Entities.Fireball;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class FireballLeftRight extends Fireball{

    private Vector2 begin;
    public FireballLeftRight(Scene s) {
        super(s);
        setIsHorizontal(true);
        setScale(getScale().multiply(0.7f));
    }

    @Override
    public void start() {
        super.start();
        begin = getPosition().add(new Vector2(1, 0));
        setFlip(Vector2.negativeX());
    }
        
    @Override
    public void fixedUpdate() {
        super.fixedUpdate();
        if(getPosition().getX() < begin.getX() - 6){
            setPosition(begin);
        }
        setPosition(getPosition().translate(Vector2.left(), 2.5f * Time.fixedDeltaTime()));
    }
    
    @Override
    public <T extends Entity> T copyOf() {
        return (T) new FireballLeftRight(getScene());
    }
}
