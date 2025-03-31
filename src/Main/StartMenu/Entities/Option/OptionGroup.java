/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Option;

import Datas.Vector2;
import Entities.Entity;
import Entities.UI.UIEntity;
import Main.StartMenu.Entities.Fadable;
import Main.StartMenu.Entities.MenuController;
import Physics.Time;
import Scenes.Scene;
import java.util.List;

/**
 *
 * @author GA_IA
 */
public class OptionGroup extends UIEntity{

    private HeaderFrame headerFrame;
    private BodyFrame bodyFrame;
    private BackButton backButton;
    private SideWing sidewing;
    
    private MenuController controller;
    
    private float waitTime = 0.1f;
    private float fadeTime = 2f;
    
    private float beginWait;
    
    private float alpha = 0f;
    
    private boolean opaque = true;
    
    public OptionGroup(Scene s, MenuController controller) {
        super(s);
        
        this.controller = controller;
        setPosition(new Vector2(0, -100));
        
        bodyFrame = new BodyFrame(s);
        addChild(bodyFrame);
        
        headerFrame = new HeaderFrame(s);
        addChild(headerFrame);
        headerFrame.setLocalPosition(new Vector2(0, 530));
        
        backButton = new BackButton(s, controller);
        addChild(backButton);
        backButton.setLocalPosition(new Vector2(-750, 520));
        
        sidewing = new SideWing(s);
        addChild(sidewing);
        sidewing.setLocalPosition(new Vector2(0,0));
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
    
    private void loopFade(List<Entity> e, float alpha){
        for(Entity i : e){
            if(i instanceof Fadable){
                ((Fadable) i).fade(alpha);
            }
            if(!i.getChilds().isEmpty()){
                loopFade(i.getChilds(), alpha);
            }
        }
    }

    @Override
    public void start() {
        beginWait = Time.time();
        loopFade(getChilds(), 0f);
    }

    @Override
    public void update() {
        if(Time.time() - beginWait < waitTime){
            return;
        }
        
        if(controller.getCurrentPage() == 3){
            opaque = true;
        }
        else{
            opaque = false;
        }
        
        if(opaque){
            if(alpha < 1f){
                if(alpha + fadeTime * Time.deltaTime() < 1f){
                    alpha += fadeTime * Time.deltaTime();
                    loopFade(getChilds(), alpha);
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
                    loopFade(getChilds(), alpha);
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
