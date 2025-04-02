/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Load;

import Datas.Vector2;
import Entities.UI.UIImage;
import Entities.UI.UIText;
import Main.StartMenu.Entities.Fadable;
import Main.StartMenu.Entities.MenuController;
import Scenes.Scene;
import Utilities.FileReader;
import Main.StartMenu.Entities.TextButton;
import Saves.GameSave;
import Saves.SaveManager;

/**
 *
 * @author nirawith2548gmail.com
 */

public class SaveGroup extends UIImage implements Fadable{
    private int saveID;
    
    private TextButton saveNumber;
    private SaveChapter chapterBord;
    private LoadButton load;
    private PlayTime playtime;
    private DeleteButton delete;
    private GameSave save;
    
    private ClickToCreate createNew;
    
    public SaveGroup(Scene s, MenuController currentPage){
        super(s);
        this.setImage(FileReader.readImage("res/game/loadmenu/SaveBorder.png"));
        
        saveNumber = new TextButton(s);
        this.addChild(saveNumber);
        saveNumber.setLocalPosition(new Vector2(-370,67));
        saveNumber.setSize(82);
        
        chapterBord = new SaveChapter(s);
        this.addChild(chapterBord);
        chapterBord.setScale(new Vector2(chapterBord.getImage().getWidth()-190 , chapterBord.getImage().getHeight()-17));
        chapterBord.setLocalPosition(new Vector2(-70,47));
        
        load = new LoadButton(s, currentPage, this);
        this.addChild(load);
        load.setScale(new Vector2(load.getImage().getWidth()-176,load.getImage().getHeight()-40));
        load.setLocalPosition(new Vector2(-285,-40));
        
        playtime = new PlayTime(s);
        this.addChild(playtime);
        playtime.setScreenAnchor(LEFT);
        playtime.setLocalPosition(new Vector2(-110,-28));
        
        delete = new DeleteButton(s,this);
        this.addChild(delete);
        delete.setScale(new Vector2(delete.getImage().getWidth()-90,delete.getImage().getHeight()-37));
        delete.setPosition(new Vector2(280,6));
        
        createNew = new ClickToCreate(s, this, currentPage);
        this.addChild(createNew);
        createNew.getText().setSize(72);
        createNew.getText().setText("Click to Create New");
        createNew.getText().setHorizontalAlignment(UIText.CENTER);
        createNew.setLocalScale(Vector2.one());
    }

    public GameSave getSave() {
        return save;
    }

    public void setSave(GameSave save) {
        this.save = save;
        if(this.save == null){
            saveNumber.setActive(false);
            chapterBord.setActive(false);
            load.setActive(false);
            playtime.setActive(false);
            delete.setActive(false);
            
            createNew.setActive(true);
        }
        else{
            saveNumber.setActive(true);
            chapterBord.setActive(true);
            load.setActive(true);
            playtime.setActive(true);
            delete.setActive(true);
            
            createNew.setActive(false);
            
        setSaveNumber("Save 1");
        setChapter("Chapter 1");
        setPlayTime("Time Played");
        
        setSaveNumber("Save " + save.getSaveNumber());
        setChapter("Chapter " + save.getCurrentChapter());
        setPlayTime("Time Played:" + save.getPlayTime());
        }
    }
    
    public void setSaveNumber(String s){
        this.saveNumber.setText(s);
    }
    
    public TextButton getSaveNumber(){
        return saveNumber;
    }
    
    public int getSaveID() {
        return saveID;
    }

    public void setSaveID(int saveID) {
        this.saveID = saveID;
    }
    
    public void setChapter(String s){
        this.chapterBord.getChapter().setText(s);
    }
    
    public SaveChapter getChapter(){
        return this.chapterBord;
    }
    
    public void setPlayTime(String s){
        this.playtime.setText(s);
    } 
    
    public PlayTime getPlayTime(){
        return this.playtime;
    }
    
    @Override
    public void start() {
        setSave(SaveManager.getInstance().getSave(saveID));
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
