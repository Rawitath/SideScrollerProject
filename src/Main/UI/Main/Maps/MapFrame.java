/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main.Maps;

import Datas.Vector2;
import Main.UI.Main.FrameOne;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class MapFrame extends FrameOne{
    
    private MapImage image;
    
    public MapFrame(Scene s) {
        super(s);
        image = new MapImage(s);
        addChild(image);
        image.setLocalScale(Vector2.one());
    }

    @Override
    public void start() {
        super.start();
        
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
    }
    
}
