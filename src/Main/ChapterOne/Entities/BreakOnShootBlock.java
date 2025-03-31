/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Entities;

import Entities.Copyable;
import Entities.Entity;
import Main.Entities.Main.Damagable;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class BreakOnShootBlock extends BreakableBlock implements Copyable, Damagable{
    public BreakOnShootBlock(Scene s) {
        super(s);
    }

    @Override
    public <T extends Entity> T copyOf() {
        BreakOnShootBlock b = new BreakOnShootBlock(getScene());
        return (T) b;
    }

    @Override
    public void damageTaken(int damage) {
        destroy();
    }

    @Override
    public void destroyBlock(Entity e) {

    }
}
