  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.UI;

import Datas.Vector2;
import Datas.Vector2Int;
import Entities.Entity;
import Scenes.Scene;
import Shifter.UIView;
import java.awt.Dimension;
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

    @Override
    public Vector2 getPosition() {
        return super.getPosition(); 
    }

    @Override
    public Vector2 getScale() {
        return super.getScale();
    }
    
    public Vector2 getScreenAnchor() {
        if(getParent() != null){
            UIEntity parent = (UIEntity) getParent();
            return screenAnchor.add(parent.getScreenAnchor());
        }
        return screenAnchor;
    }
    public Vector2 getLocalScreenAnchor() {
        return screenAnchor;
    }

    public void setScreenAnchor(Vector2 screenAnchor) {
        if(getParent() != null){
            UIEntity parent = (UIEntity) getParent();
            this.screenAnchor = parent.getScale().multiply(0.5f).multiply(screenAnchor);
        }
        else{
            this.screenAnchor = new Vector2((UIView.getReferenceResolution().getX() / 2),
               (UIView.getReferenceResolution().getY() / 2)).multiply(screenAnchor);
        }
        
    }
    public static final Vector2 CENTER = Vector2.zero();
    public static final Vector2 LEFT = Vector2.left();
    public static final Vector2 RIGHT = Vector2.right();
    public static final Vector2 TOP = Vector2.up();
    public static final Vector2 BOTTOM = Vector2.down();
    public static final Vector2 TOP_LEFT = Vector2.negativeX();
    public static final Vector2 TOP_RIGHT = Vector2.one();
    public static final Vector2 BOTTOM_LEFT = Vector2.one().negative();
    public static final Vector2 BOTTOM_RIGHT = Vector2.negativeY();
//    public final Vector2 SA_CENTER(){
//        return Vector2.zero();
//    }
//    public final Vector2 SA_LEFT(){
//        return new Vector2(-(getScene().getUIView().getReferenceResolution().getX() / 2),
//               0f);
//    }
//    public final Vector2 SA_RIGHT(){
//        return new Vector2((getScene().getUIView().getReferenceResolution().getX() / 2),
//               0f);
//    }
//    public final Vector2 SA_TOP(){
//        return new Vector2(0f,
//               (getScene().getUIView().getReferenceResolution().getY() / 2));
//    }
//    public final Vector2 SA_BOTTOM(){
//        return new Vector2(0f,
//               -(getScene().getUIView().getReferenceResolution().getY() / 2));
//    }
//    public final Vector2 SA_TOP_LEFT(){
//        return new Vector2(-(getScene().getUIView().getReferenceResolution().getX() / 2),
//               (getScene().getUIView().getReferenceResolution().getY() / 2));
//    }
//    public final Vector2 SA_TOP_RIGHT(){
//        return new Vector2((getScene().getUIView().getReferenceResolution().getX() / 2),
//               (getScene().getUIView().getReferenceResolution().getY() / 2));
//    }
//    public final Vector2 SA_BOTTOM_LEFT(){
//        return new Vector2(-(getScene().getUIView().getReferenceResolution().getX() / 2),
//               -(getScene().getUIView().getReferenceResolution().getY() / 2));
//    }
//    public final Vector2 SA_BOTTOM_RIGHT(){
//        return new Vector2((getScene().getUIView().getReferenceResolution().getX() / 2),
//               -(getScene().getUIView().getReferenceResolution().getY() / 2));
//    }
    @Override
    public void draw(Graphics g) {
        if(isBoundaryVisibled()){
            Dimension screen = UIView.getScreenSize();
            Vector2Int reference = UIView.getReferenceResolution();
            Vector2 pos = getPosition().add(getScreenAnchor()).multiply(Vector2.negativeY())
                    .multiply(new Vector2((float)screen.width / (float)reference.getX(), (float)screen.height / (float)reference.getY()));
            Vector2 scale = getScale()
                    .multiply(new Vector2((float)screen.width / (float)reference.getX(), (float)screen.height / (float)reference.getY()));
        g.drawRect(Math.round(pos.getX() - scale.getX() / 2), Math.round(pos.getY() - scale.getY() / 2), Math.round(scale.getX()), Math.round(scale.getY()));
        }
    }
    
}
