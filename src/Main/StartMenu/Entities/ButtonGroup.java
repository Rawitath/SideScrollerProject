/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities;

import Entities.Entity;
import Entities.UI.UIEntity;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ButtonGroup extends UIEntity{
    
    private boolean isOpaque = false;
    private float alpha = 0f;
    
    private float waitTime = 1.8f;
    public ButtonGroup(Scene s) {
        super(s);
    }
    private float beginWait;
    @Override
    public void start() {
        for(Entity ui : getScene().getEntities()){
            if(ui instanceof InButtonGroup){
                addChild(ui);
                ((InButtonGroup) ui).fadeOut(0);
            }
        }
        isOpaque = true;
        beginWait = Time.time();
    }
    
    @Override
    public void update() {
        if(Time.time() - beginWait < waitTime){
            return;
        }
        if(isOpaque){
            if(alpha < 1f){
                if(alpha + 2 * Time.deltaTime() < 1f){
                    alpha += 2 * Time.deltaTime();
                    for(Entity i : getChilds()){
                        if(i instanceof InButtonGroup){
                            ((InButtonGroup) i).fadeIn(alpha);
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
                if(alpha - 2 * Time.deltaTime() > 0f){
                    alpha -= 2 * Time.deltaTime();
                    for(Entity i : getChilds()){
                        if(i instanceof InButtonGroup){
                            ((InButtonGroup) i).fadeIn(alpha);
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

    public boolean isIsOpaque() {
        return isOpaque;
    }

    public void setIsOpaque(boolean isOpaque) {
        this.isOpaque = isOpaque;
    }
    
}
