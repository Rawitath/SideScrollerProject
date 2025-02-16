/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI;

import Datas.Vector2;
import Entities.UI.UIEntity;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class HeartContainer extends UIEntity{
    HeartUI heart1, heart2, heart3;
    int lifeNum;
    public HeartContainer(Scene s) {
        super(s);
        setName("HeartContainer");
        heart1 = new HeartUI(s);
        heart2 = new HeartUI(s);
        heart3 = new HeartUI(s);
        setScreenAnchor(TOP_LEFT);
        setPosition(new Vector2(100,-100));
        addChild(heart1);
        addChild(heart2);
        addChild(heart3);
        heart1.setLocalPosition(new Vector2(0,0));
        heart2.setLocalPosition(new Vector2(160,0));
        heart3.setLocalPosition(new Vector2(320,0));
        
    }
    public void setHeart(int num){
        lifeNum = num;
    }
    @Override
    public void start() {
        setBoundaryVisibled(true);
    }

    @Override
    public void update() {
        heart1.setHeart(lifeNum > 0);
        heart2.setHeart(lifeNum > 1);
        heart3.setHeart(lifeNum > 2);
    }

    @Override
    public void fixedUpdate() {

    }
    
}
