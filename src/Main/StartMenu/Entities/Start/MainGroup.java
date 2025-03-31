/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Start;

import Datas.Vector2;
import Entities.Entity;
import Entities.UI.UIEntity;
import Entities.UI.UIImage;
import Main.StartMenu.Entities.Fadable;
import Main.StartMenu.Entities.MenuController;
import Physics.Time;
import Scenes.Scene;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GA_IA
 */
public class MainGroup extends UIEntity{
    
    private StartButton start;
    private NewGameButton newGame;
    private LoadButton load;
    private OptionButton option;
    private UIImage buttonFrame;
    
    private MenuController controller;
    
    private float waitTime = 2f;
    private float fadeTime = 2f;
    
    private float beginWait;
    
    private float alpha = 0f;
    
    private boolean opaque = true;
    
    public MainGroup(Scene s, MenuController controller) {
        super(s);
        this.controller = controller;
        buttonFrame = new ButtonFrame(s);
        
        controller.setButtonFrame(buttonFrame);
        
        start = new StartButton(s, controller);
        newGame = new NewGameButton(s, controller);
        load = new LoadButton(s, controller);
        option = new OptionButton(s, controller);
        
        addChild(buttonFrame);
        addChild(start);
        addChild(newGame);
        addChild(load);
        addChild(option);
        addChild(new Logo(s));
        //addChild(new CopyrightText(s));
        
        setScreenAnchor(LEFT);
        setPosition(new Vector2(420, 385));
        
        List<UIEntity> e = new ArrayList<>();
        e.add(start);
        e.add(newGame);
        e.add(load);
        e.add(option);
        
        float posY = -180;
        float space = 150;
        
        for(UIEntity ui : e){
            ui.setLocalPosition(new Vector2(-80, posY));
            posY -= space;
        }
        
    }
    
    public float getFadeTime() {
        if(fadeTime == 0){
            return 0f;
        }
        return 1f / fadeTime;
    }

    public void setFadeTime(float fadeTime) {
        if(fadeTime == 0){
            this.fadeTime = 0;
        }
        this.fadeTime = 1f / fadeTime;
    }

    @Override
    public void start() {
        beginWait = Time.time();
        for(Entity i : getChilds()){
            if(i instanceof Fadable){
                ((Fadable) i).fade(0);
            }
        }
    }

    @Override
    public void update() {
        if(Time.time() - beginWait < waitTime){
            return;
        }
        
        if(!controller.getAllButtons().isEmpty()){
            buttonFrame.setPosition(controller.getAllButtons().get(controller.getSelectedButton()).getPosition());
        }
        
        if(controller.getCurrentPage() == 0){
            opaque = true;
        }
        else{
            if(controller.getCurrentPage() != 1){
                setFadeTime(0.1f);
            }
            opaque = false;
        }
        
        if(opaque){
            if(alpha < 1f){
                if(alpha + fadeTime * Time.deltaTime() < 1f){
                    alpha += fadeTime * Time.deltaTime();
                    for(Entity i : getChilds()){
                        if(i instanceof Fadable){
                            ((Fadable) i).fade(alpha);
                        }
                    }
                }
                else{
                    alpha = 1f;
                }
            }
        }
        else{
            if(alpha > 0f){
                if(alpha - fadeTime * Time.deltaTime() > 0f){
                    alpha -= fadeTime * Time.deltaTime();
                    for(Entity i : getChilds()){
                        if(i instanceof Fadable){
                            ((Fadable) i).fade(alpha);
                        }
                    }
                }
                else{
                    alpha = 0f;
                }
            }
        }
    }

    @Override
    public void fixedUpdate() {

    }
    
}
