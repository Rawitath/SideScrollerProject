/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Entities;

import Datas.Vector2;
import Entities.Entity;
import Main.ChapterThree.Entities.Fireball;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class StaticFire extends Fireball{
    
    public StaticFire(Scene s) {
        super(s);
        setScale(getScale().multiply(0.7f));
        setFlip(Vector2.negativeY());
    }

    @Override
    public <T extends Entity> T copyOf() {
        StaticFire fire = new StaticFire(getScene());
        return (T) fire;
    }
    
}
