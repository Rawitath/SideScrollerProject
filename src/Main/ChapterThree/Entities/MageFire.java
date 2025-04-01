/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Datas.Vector2;
import Main.Entities.Main.AttackProjectile;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class MageFire extends AttackProjectile{
    
    public MageFire(Scene s, Vector2 direction, float speed, float duration) {
        super(s, direction, speed, duration);
        setTag("Mage Fire");
    }
    
}
