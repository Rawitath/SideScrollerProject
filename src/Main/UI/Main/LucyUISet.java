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
public class LucyUISet extends UIEntity{

    private HeartFrame heartFrame;
    
    public LucyUISet(Scene s) {
        super(s);
        setScale(Vector2.one().multiply(s.getUIView().getReferenceResolution()));
        
        heartFrame = new HeartFrame(s);
        addChild(heartFrame);
        heartFrame.setScale(new Vector2(1, 1));
        heartFrame.setLocalPosition(new Vector2(100, -80));
        
    }

    @Override
    public void start() {
        heartFrame.setScreenAnchor(TOP_LEFT);
    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {

    }

    public HeartFrame getHeartFrame() {
        return heartFrame;
    }

    public void setHeartFrame(HeartFrame heartFrame) {
        this.heartFrame = heartFrame;
    }
    
}
