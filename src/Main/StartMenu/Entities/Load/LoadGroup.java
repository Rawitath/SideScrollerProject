/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Load;

import Datas.Vector2;
import Entities.UI.UIEntity;
import Entities.UI.UIImage;
import Main.StartMenu.Entities.Fadable;
import Main.StartMenu.Entities.MenuController;
import Scenes.Scene;
import Utilities.FileReader;
import java.util.ArrayList;
import java.util.List;
import Saves.GameSave;
import Saves.SaveManager;

/**
 *
 * @author nirawith2548gmail.com
 */
public class LoadGroup extends UIImage implements Fadable{
    
    private BackButton backButton;
    private TitlePage title;
    private SaveGroup first , second , third;
//    private GameSave save1 , save2 , save3;
    
    public LoadGroup(Scene s , MenuController controller) {
        super(s);
        
        this.setImage(FileReader.readImage("res/game/loadmenu/MainBorder.png"));
        this.setScale(new Vector2(1000,950));
        
        backButton = new BackButton(s, controller);
        addChild(backButton);
        backButton.setLocalPosition(new Vector2(-300, 400));
        backButton.setScale(new Vector2(backButton.getImage().getWidth()-120,backButton.getImage().getHeight()-50));
        
        title = new TitlePage(s);
        this.addChild(title);
        title.setScale(new Vector2(title.getImage().getWidth()-100 , title.getImage().getHeight()-20));
        title.setLocalPosition(new Vector2(0,400));
        
        first = new SaveGroup(s, controller);
        first.setSaveID(0);
//        save1 = new GameSave();
        this.addChild(first);
        first.setScale(new Vector2(870 , 180));
//        first.setLocalPosition(new Vector2(0,200));

//        first.setSaveNumber("Save 1");
//        first.setChapter("Chapter 1");
//        first.setPlayTime("Time Played");
        
//        first.setSave(SaveManager.getInstance().getSave(0));
//        first.setSaveNumber("Save " + save1.saveNumber);
//        first.setChapter("Chapter " + save1.currentChapter);
//        first.setPlayTime("Time Played:" + save1.playTime);
        
        second = new SaveGroup(s, controller);
        second.setSaveID(1);
//        save2 = new GameSave();
        this.addChild(second);
        second.setScale(new Vector2(870 , 180));
        
//        second.setSaveNumber("Save 2");
//        second.setChapter("Chapter 2");
//        second.setPlayTime("Time Played");
        
//        second.setSave(SaveManager.getInstance().getSave(1));
//        second.setSaveNumber("Save " + save2.saveNumber);
//        second.setChapter("Chapter " + save2.currentChapter);
//        second.setPlayTime("Time Played:" + save2.playTime);
        
        third = new SaveGroup(s, controller);
        third.setSaveID(2);
//        save3 = new GameSave();
        this.addChild(third);
        third.setScale(new Vector2(870 , 180));
        
//        third.setSaveNumber("Save 3");
//        third.setChapter("Chapter 3");
//        third.setPlayTime("Time Played");
        
//        third.setSave(SaveManager.getInstance().getSave(third.getsave));
//        third.setSaveNumber("Save " + save3.saveNumber);
//        third.setChapter("Chapter " + save3.currentChapter);
//        third.setPlayTime("Time Played:" + save3.playTime);
        
        List<UIEntity> e = new ArrayList<>();
        e.add(first);
        e.add(second);
        e.add(third);
        
        float posY = 200;
        float space = 250;
        
        for(UIEntity ui : e){
            ui.setLocalPosition(new Vector2(0, posY));
            posY -= space;
        }
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

    @Override
    public void fade(float alpha) {
        this.setAlpha(alpha);
    }
}
