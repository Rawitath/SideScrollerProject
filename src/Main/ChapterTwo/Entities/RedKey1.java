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
import Saves.SaveManager;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class RedKey1 extends ItemEntity{
    
    private Key key;
    private Animator animator;
    
    public RedKey1(Scene s) {
        super(s);
        key = new Key("Key", 0, 0);
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
        if(SaveManager.getInstance().getCurrentSave().getUnlockedDoors().contains(key.getKeyID()) || 
                SaveManager.getInstance().getCurrentSave().getObtainedKey().contains(key)){
            setActive(false);
            getScene().removeEntity(this);
        }
        super.update();
        setSprite(animator.getFrame(Time.deltaTime()), true);
    }
    
}
