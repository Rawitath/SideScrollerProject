/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main.Maps;

import Datas.Vector2;
import Entities.UI.UIEntity;
import Main.UI.Main.FrameOne;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class MapMenu extends UIEntity{

    private MapFrame mapFrame;
    
    public MapMenu(Scene s) {
        super(s);
        mapFrame = new MapFrame(s);
        addChild(mapFrame);     
    }

    @Override
    public void start() {
        
    }

    @Override
    public void onSetActive() {
        super.onSetActive();
        if(mapFrame != null){
             mapFrame.setScale(new Vector2(mapFrame.getImage().getWidth(), mapFrame.getImage().getHeight()));
        mapFrame.setScale(mapFrame.getScale().multiply(0.8f));
        }
       
    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {
       
    }
    
}
