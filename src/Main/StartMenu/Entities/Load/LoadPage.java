/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Load;

import Datas.Vector2;
import Entities.Entity;
import Entities.UI.UIEntity;
import Main.StartMenu.Entities.Fadable;
import Main.StartMenu.Entities.MenuController;
import Main.StartMenu.Entities.Load.LoadGroup;
import Physics.Time;
import Scenes.Scene;
import Utilities.FileReader;
import java.util.List;

/**
 *
 * @author nirawith2548gmail.com
 */
public class LoadPage extends UIEntity{
    private LoadGroup loadGroup;
    
    private MenuController controller;
    
    private float waitTime = 0.1f;
    private float fadeTime = 2f;
    
    private float beginWait;
    
    private float alpha = 0f;
    
    private boolean opaque = true;
    
    public LoadPage(Scene s, MenuController controller) {
        super(s);
        
        this.controller = controller;
        
//        this.setScreenAnchor(CENTER);
        
        loadGroup = new LoadGroup(s , this.controller);
        this.addChild(loadGroup);
        loadGroup.setLocalPosition(new Vector2(-370,0));
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
        
        if(controller.getCurrentPage() == 2){
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
