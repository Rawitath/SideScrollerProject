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
    
    public ChapterOneManager(Scene s, Lucy lucy, LucyUISet ui) {
        super(s, lucy, ui);
        setInitialZoom(85f);
        setName("Manager1");
        setMinCameraLimit(new Vector2(-9.51f, -3.55f));
        setMaxCameraLimit(new Vector2(19.43f, 4.96f));
    }
}
