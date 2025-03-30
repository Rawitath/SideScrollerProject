/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterTwo.Entities;

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
public class ChapterTwoManager extends ChapterManager{

    public ChapterTwoManager(Scene s, Lucy lucy, LucyUISet ui) {
        super(s, lucy, ui);
        setInitialZoom(85f);
        setName("Manager2");
    }
}
