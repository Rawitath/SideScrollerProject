/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main;

import Datas.Vector2;
import Entities.UI.UIEntity;
import Entities.UI.UIImage;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class StatPanel extends UIImage{

    private int currentPage = 0;
    private StatsMenu stat;
    
    public StatPanel(Scene s) {
        super(s);
        setImage(FileReader.readImage("res/game/hud/statmenu/bgpanel.png"));
        setAlpha(0.7f);
        
        stat = new StatsMenu(s);
        addChild(stat);
        stat.setScale(new Vector2(1, 1));
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {

    }
    
}
