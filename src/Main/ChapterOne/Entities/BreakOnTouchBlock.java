/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Entities;

import Entities.Copyable;
import Entities.Entity;
import Physics.Collider;
import Saves.SaveManager;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class BreakOnTouchBlock extends BreakableBlock implements Copyable{

    public BreakOnTouchBlock(Scene s) {
        super(s);
        setName("Drop");
    }

    @Override
    public void destroyBlock(Entity e) {
        if(e.getTag().equals("Player")){
            SaveManager.getInstance().getCurrentSave().setOne_GroundDrop(true);
            setSpriteVisibled(false);
            destroy();
        }
    }

    @Override
    public <T extends Entity> T copyOf() {
        BreakOnTouchBlock b = new BreakOnTouchBlock(getScene());
        return (T) b;
    }
    
}
