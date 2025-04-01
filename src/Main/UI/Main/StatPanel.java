/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main;

import Main.UI.Main.Stats.StatsMenu;
import Datas.Vector2;
import Entities.UI.UIEntity;
import Entities.UI.UIImage;
import Main.UI.Main.Maps.MapMenu;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class StatPanel extends UIImage{

    private int currentPage = 0;
    private StatsMenu stat;
    private MapMenu map;
    
    private ToMap toMap;
    private ToStats toStats;
    //private ToAchievement toAchievement;
    
    public StatPanel(Scene s) {
        super(s);
        setImage(FileReader.readImage("res/game/hud/statmenu/bgpanel.png"));
        setAlpha(0.7f);
        
        stat = new StatsMenu(s);
        addChild(stat);
        stat.setScale(new Vector2(1, 1));
        
        map = new MapMenu(s);
        addChild(map);
        map.setScale(new Vector2(1, 1));
        
        toMap = new ToMap(s, this);
        toStats = new ToStats(s, this);
        addChild(toMap);
        addChild(toStats);
        //toAchievement = new ToAchievement(s);
    }

    @Override
    public void start() {
        toStats.setScreenAnchor(TOP_RIGHT);
        toMap.setScreenAnchor(TOP_LEFT);
        
        toStats.setScale(new Vector2(toStats.getImage().getWidth(), toStats.getImage().getHeight()));
        toMap.setScale(new Vector2(toMap.getImage().getWidth(), toMap.getImage().getHeight()));
        
        toStats.setPosition(new Vector2(-100, -80));
        toMap.setPosition(new Vector2(100, -80));
        
        setCurrentPage(0);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        if(currentPage == 0){
            stat.setActive(true);
            map.setActive(false);
        }
        else if(currentPage == 1){
            stat.setActive(false);
            map.setActive(true);
        }
    }
    
    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {

    }
    
}
