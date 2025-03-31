/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main.Stats;

import Datas.Vector2;
import Entities.UI.UIEntity;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ObtainableGroup extends UIEntity{

    private ObtainableImage image;
    private CheckBox checkBox;
    
    private boolean isAchieved = false;
    
    public ObtainableGroup(Scene s) {
        super(s);
        image = new ObtainableImage(s);
        checkBox = new CheckBox(s);
        addChild(image);
        addChild(checkBox);
    }
    
    @Override
    public void start() {
        image.setScale(new Vector2(image.getImage().getWidth(), image.getImage().getHeight()));
        checkBox.setScale(new Vector2(checkBox.getImage().getWidth(), checkBox.getImage().getHeight()));
        image.setLocalPosition(new Vector2(-100, 0));
        checkBox.setLocalPosition(new Vector2(100, 0));
    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {

    }

    public ObtainableImage getImage() {
        return image;
    }
    
    public boolean isIsAchieved() {
        return isAchieved;
    }

    public void setIsAchieved(boolean isAchieved) {
        this.isAchieved = isAchieved;
        checkBox.setIsOn(isAchieved);
    }
    
}
