/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main;

import Datas.Vector2;
import Entities.UI.UIEntity;
import Scenes.Scene;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GA_IA
 */
public class HeartFrame extends UIEntity{
    private int currrentHeart;
    private int maxHeart = 5;
    private int spacing = 120;
    
    private List<Heart> hearts;
    public HeartFrame(Scene s) {
        super(s);
        hearts = new ArrayList<>();
    }

    @Override
    public void start() {
        for(int i = 0; i < maxHeart; i++){
            Heart h = new Heart(getScene());
            hearts.add(h);
            addChild(h);
            h.setScreenAnchor(LEFT);
            h.setLocalPosition(new Vector2(spacing * i, 0f));
        }
    }

    @Override
    public void update() {
        
    }

    @Override
    public void fixedUpdate() {

    }

    public int getMaxHeart() {
        return maxHeart;
    }

    public void setMaxHeart(int maxHeart) {
        this.maxHeart = maxHeart;
    }
    
    private void updateHeart(){
        for(int i = 0; i < hearts.size(); i++){
            if(i < currrentHeart){
                hearts.get(i).setIsFull(true);
            }
            else{
                hearts.get(i).setIsFull(false);
            }
        }
    }
    
    public int getCurrrentHeart() {
        return currrentHeart;
    }

    public void setCurrrentHeart(int currrentHeart) {
        if(this.currrentHeart == currrentHeart){
            return;
        }
        this.currrentHeart = currrentHeart;
        updateHeart();
    }
}
