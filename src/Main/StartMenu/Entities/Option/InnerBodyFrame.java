/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Option;

import Datas.Vector2;
import Entities.UI.UIImage;
import Main.StartMenu.Entities.Fadable;
import Scenes.Scene;
import Utilities.FileReader;
import Datas.Vector2;
import Entities.UI.UIEntity;
import static Entities.UI.UIEntity.LEFT;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GA_IA
 */
public class InnerBodyFrame extends UIImage implements Fadable{
    private OptionTitle master ,music , SFX , window;
    private OptionSlider sMaster , sMusic , sSFX;
    private OptionTitle full , other;
    private OptionTitle[] mode = {full , other};
    private byte seletedMode;
    private NextModeButton next;
    private PreviousModeButton previous;

    public InnerBodyFrame(Scene s) {
        super(s);
        setImage(FileReader.readImage("res/game/settingsmenu/Border3.png"));
        
        master = new OptionTitle(s);
        master.setText("Master Volume");
        this.addChild(master);
        music = new OptionTitle(s);
        music.setText("Music Volume");
        this.addChild(music);
        SFX = new OptionTitle(s);
        SFX.setText("SFX Volume");
        this.addChild(SFX);
        window = new OptionTitle(s);
        window.setText("Window Mode");
        this.addChild(window);
        
        sMaster = new OptionSlider(s);
        this.addChild(sMaster);
        sMaster.setScale(new Vector2(1,1));
        sMusic = new OptionSlider(s);
        this.addChild(sMusic);
        sMusic.setScale(new Vector2(1,1));
        sSFX = new OptionSlider(s);
        this.addChild(sSFX);
        sSFX.setScale(new Vector2(1,1));
        
        full = new OptionTitle(s);
        full.setText("Fullscreen");
        other = new OptionTitle(s);
        other.setText("OtherMode");
        this.addChild(full);
        full.setLocalPosition(new Vector2(130,-170));
//        this.addChild(mode[0]); // I don't know how to use this
//        mode[0].setLocalPosition(new Vector2(300,-500));
        
//        setScreenAnchor(CENTER);
//        setLocalPosition(new Vector2(0,0));
        
        next = new NextModeButton(s);
        this.addChild(next);
        next.setLocalPosition(new Vector2(510,-190));
        next.setScale(new Vector2(7,13));
        previous = new PreviousModeButton(s);
        this.addChild(previous);
        previous.setLocalPosition(new Vector2(25,-190));
        previous.setScale(new Vector2(7,13));
        
        List<UIEntity> e = new ArrayList<>();
        e.add(master);
        e.add(music);
        e.add(SFX);
        e.add(window);
        
        float posY = 220;
        float space = 130;
        
        for(UIEntity ui : e){
            ui.setLocalPosition(new Vector2(-700, posY));
            posY -= space;
        }
        
        e.clear();
        e.add(sMaster);
        e.add(sMusic);
        e.add(sSFX);
        
        float slideY = 194;
        
        for(UIEntity ui : e){
            ui.setLocalPosition(new Vector2(300, slideY));
            slideY -= space;
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
        setAlpha(alpha);
    }
    
    public byte getSelectedMode(){
        return seletedMode;
    }
}
