/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterFour.Entities;

import Entities.Entity;
import Main.ChapterThree.Entities.Fireball;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class FireballStaticHorizontal extends Fireball{

    public FireballStaticHorizontal(Scene s) {
        super(s);
    }
    @Override
    public <T extends Entity> T copyOf() {
        FireballStaticHorizontal fireball = new FireballStaticHorizontal(getScene());
        return (T) fireball;
    }
}
