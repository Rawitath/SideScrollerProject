/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main;

import Datas.Vector2;
import Entities.UI.UIEntity;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class StatsMenu extends UIEntity{
    
    private FrameTwo lucyFrame;
    private FrameThree leftFrame;
    private FrameThree rightFrame;

    public StatsMenu(Scene s) {
        super(s);
        lucyFrame = new FrameTwo(s);
        leftFrame = new LeftFrame(s);
        rightFrame = new RightFrame(s);
        
        addChild(lucyFrame);
        addChild(leftFrame);
        addChild(rightFrame);
        
    }

    @Override
    public void start() {
        lucyFrame.setScale(new Vector2(lucyFrame.getImage().getWidth(), lucyFrame.getImage().getHeight()));
        leftFrame.setScale(new Vector2(leftFrame.getImage().getWidth(), leftFrame.getImage().getHeight()));
        rightFrame.setScale(new Vector2(rightFrame.getImage().getWidth(), rightFrame.getImage().getHeight()));
        lucyFrame.setPosition(new Vector2(0, -30));
        leftFrame.setPosition(new Vector2(-570, -30));
        rightFrame.setPosition(new Vector2(570, -30));
    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {

    }
    
}
