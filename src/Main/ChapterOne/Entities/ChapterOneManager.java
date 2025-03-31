/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Entities;

import Datas.Vector2;
import Entities.Entity;
import Main.Entities.Main.ChapterManager;
import Main.Entities.Main.Lucy;
import Main.GameSystem.Cutscene.Cutscene;
import Main.UI.Main.LucyUISet;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ChapterOneManager extends ChapterManager{
    
    private Lucy lucy;
    
    public ChapterOneManager(Scene s, Lucy lucy, LucyUISet ui) {
        super(s, lucy, ui);
        this.lucy = lucy;
        setInitialZoom(85f);
        setName("Manager1");
        setMinCameraLimit(new Vector2(-9.51f, -3.55f));
        setMaxCameraLimit(new Vector2(19.43f, 4.96f));
    }

    @Override
    public void start() {
        if(getSave().getCurrentCheckpoint() == null){
            lucy.setPosition(getScene().getEntity("Mark1").getPosition());
            getScene().addEntity(lucy);
        }
        super.start();
    }
    
}
