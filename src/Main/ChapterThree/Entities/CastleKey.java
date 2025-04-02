/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterThree.Entities;

import Animations.Animator;
import Main.ChapterTwo.Animation.BlueKeyAnimation;
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
public class CastleKey extends ItemEntity{
     private Key key;
    private Animator animator;
    
    public CastleKey(Scene s) {
        super(s);
        key = new Key("Key", 4, 1);
        setItem(key);
        
        animator = new Animator();
        animator.setAnimation(new BlueKeyAnimation());
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
