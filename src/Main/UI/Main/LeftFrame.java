/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main;

import Datas.Vector2;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class LeftFrame extends FrameThree{
    
    private StatsHeader header;
    private MonsterTextGroup skeleton;
    
    
    public LeftFrame(Scene s) {
        super(s);
        header = new StatsHeader(s);
        addChild(header);
        
        skeleton = new MonsterTextGroup(s);
        addChild(skeleton);
    }

    @Override
    public void start() {
        super.start();
        header.setScreenAnchor(TOP);
        header.setScale(new Vector2(header.getImage().getWidth(), header.getImage().getHeight()));
        header.setLocalPosition(new Vector2(0, -60));
    }
    
    
}
