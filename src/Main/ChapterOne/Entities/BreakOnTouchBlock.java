/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Entities;

import Entities.Copyable;
import Entities.Entity;
import Physics.Collider;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class BreakOnTouchBlock extends BreakableBlock implements Copyable{

    public BreakOnTouchBlock(Scene s) {
        super(s);
    }

    @Override
    public void destroyBlock(Entity e) {
        if(e.getTag().equals("Player")){
            destroy();
        }
    }

    @Override
    public <T extends Entity> T copyOf() {
        BreakOnTouchBlock b = new BreakOnTouchBlock(getScene());
        return (T) b;
    }
    
}
