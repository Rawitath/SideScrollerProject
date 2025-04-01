/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main.Stats;

import Entities.UI.UIImage;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class StatsHeader extends UIImage{
    public StatsHeader(Scene s) {
        super(s);
        setImage(FileReader.readImage("res/game/hud/statmenu/Statistics.png"));
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
