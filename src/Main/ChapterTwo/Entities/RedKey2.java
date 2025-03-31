/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterTwo.Entities;

import Animations.Animator;
import Main.ChapterTwo.Animation.RedKeyAnimation;
import Main.GameSystem.Door.Key;
import Main.GameSystem.Inventory.ItemEntity;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class RedKey2 extends ItemEntity{
    private Key key;
    private Animator animator;
    
    public RedKey2(Scene s) {
        super(s);
        key = new Key("Key", 1, 0);
        setItem(key);
        
        animator = new Animator();
        animator.setAnimation(new RedKeyAnimation());
        setSpriteSize(getSpriteSize().multiply(0.25f));
    }

    public Key getKey() {
        return key;
    }
    @Override
    public void update() {
        super.update();
        setSprite(animator.getFrame(Time.deltaTime()), true);
    }
}
