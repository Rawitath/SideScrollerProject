/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.UI;

import Datas.Vector2;
import Entities.Entity;
import Scenes.Scene;
import java.awt.Graphics;

/**
 *
 * @author GA_IA
 */
public abstract class UIEntity extends Entity{
    private Vector2 screenAnchor;
    
    public UIEntity(Scene s) {
        super(s);
        screenAnchor = new Vector2();
    }

    public Vector2 getScreenAnchor() {
        return screenAnchor;
    }

    public void setScreenAnchor(Vector2 screenAnchor) {
        this.screenAnchor = screenAnchor;
    }
    
    public final Vector2 SA_CENTER(){
        return Vector2.zero();
    }
    public final Vector2 SA_LEFT(){
        return new Vector2(-(getScene().getUIView().getScreenSize().width / 2),
               0f);
    }
    public final Vector2 SA_RIGHT(){
        return new Vector2((getScene().getUIView().getScreenSize().width / 2),
               0f);
    }
    public final Vector2 SA_TOP(){
        return new Vector2(0f,
               -(getScene().getUIView().getScreenSize().height / 2));
    }
    public final Vector2 SA_BOTTOM(){
        return new Vector2(0f,
               (getScene().getUIView().getScreenSize().height / 2));
    }
    public final Vector2 SA_TOP_LEFT(){
        return new Vector2(-(getScene().getUIView().getScreenSize().width / 2),
               -(getScene().getUIView().getScreenSize().height / 2));
    }
    public final Vector2 SA_TOP_RIGHT(){
        return new Vector2((getScene().getUIView().getScreenSize().width / 2),
               -(getScene().getUIView().getScreenSize().height / 2));
    }
    public final Vector2 SA_BOTTOM_LEFT(){
        return new Vector2(-(getScene().getUIView().getScreenSize().width / 2),
               (getScene().getUIView().getScreenSize().height / 2));
    }
    public final Vector2 SA_BOTTOM_RIGHT(){
        return new Vector2((getScene().getUIView().getScreenSize().width / 2),
               (getScene().getUIView().getScreenSize().height / 2));
    }
    @Override
    public void draw(Graphics g, Vector2 posOffset, Vector2 scaleOffset, float zoom) {

    }
    
}
