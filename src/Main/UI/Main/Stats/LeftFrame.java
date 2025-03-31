/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main.Stats;

import Datas.Vector2;
import Entities.Entity;
import Main.UI.Main.FrameThree;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class LeftFrame extends FrameThree{
    
    private StatsHeader header;
    private MonsterTextGroup skeleton;
    private MonsterTextGroup zombie;
    
    
    public LeftFrame(Scene s) {
        super(s);
        header = new StatsHeader(s);
        addChild(header);
        
        skeleton = new MonsterTextGroup(s);
        addChild(skeleton);
        
        zombie = new MonsterTextGroup(s);
        addChild(zombie);
    }

    @Override
    public void start() {
        super.start();
        header.setScreenAnchor(TOP);
        header.setScale(new Vector2(header.getImage().getWidth(), header.getImage().getHeight()));
        skeleton.setScreenAnchor(TOP);
        zombie.setScreenAnchor(TOP);
        float posY = -90;
        float spacing = 100;
        for(Entity e : getChilds()){
            e.setPosition(new Vector2(e.getPosition().getX(), posY));
            posY -= spacing;
        }
    }
    
    
}
