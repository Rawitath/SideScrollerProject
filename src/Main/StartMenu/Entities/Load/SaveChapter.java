/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Load;

import Datas.Vector2;
import Entities.UI.UIImage;
import Main.StartMenu.Entities.Fadable;
import Main.StartMenu.Entities.TextButton;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author nirawith2548gmail.com
 */
public class SaveChapter extends UIImage implements Fadable{
    private TextButton chapter;
    
    public SaveChapter(Scene s){
        super(s);
        this.setImage(FileReader.readImage("res/game/loadmenu/ChapterBorder.png"));
        
        chapter = new TextButton(s);
        this.addChild(chapter);
        chapter.setLocalPosition(new Vector2(-70,10));
        chapter.setSize(40);
    }
    
    public void setChapter(String s){
        this.chapter.setText(s);
    }
    
    public TextButton getChapter(){
        return this.chapter;
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
