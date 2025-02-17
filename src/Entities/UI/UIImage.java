/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.UI;

import Datas.Vector2;
import Datas.Vector2Int;
import Scenes.Scene;
import Utilities.FileReader;
import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public abstract class UIImage extends UIEntity{

    private BufferedImage image;
    private Vector2 anchor;
    private boolean imageVisibled;
    private Vector2 flip;
    private Vector2Int screenPoint;
    private Vector2Int screenSize;
    private float alpha;

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
    
    
    
    protected Vector2Int getScreenPoint(){
        return screenPoint;
    }
    protected void setScreentPoint(Vector2Int screenPoint){
        this.screenPoint = screenPoint;
    }

    protected Vector2Int getScreenSize() {
        return screenSize;
    }

    protected void setScreenSize(Vector2Int screenSize) {
        this.screenSize = screenSize;
    }
    
    public UIImage(Scene s) {
        super(s);
        anchor = new Vector2(0f, 0f);
        alpha = 1f;
        imageVisibled = true;
        setLocalScale(new Vector2(100, 100));
        image = FileReader.readImage("res/default/whitesquare.png");
        
        flip = Vector2.one();
    }
    
    public Vector2 getFlip() {
        return flip;
    }

    public void setFlip(Vector2 flip) {
        this.flip = flip;
    }
    
    public void flipX(){
        flip = flip.multiply(Vector2.negativeX());
    }
    public void flipY(){
        flip = flip.multiply(Vector2.negativeY());
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public Vector2 getAnchor() {
        return anchor;
    }

    public void setAnchor(Vector2 anchor) {
        this.anchor = anchor;
    }

    public boolean isImageVisibled() {
        return imageVisibled;
    }

    public void setImageVisibled(boolean imageVisibled) {
        this.imageVisibled = imageVisibled;
    }
    @Override
    public void draw(Graphics g, Vector2 posOffset, Vector2 scaleOffset, float zoom) {
        Graphics2D g2d = (Graphics2D)g;
        super.draw(g, posOffset, scaleOffset, zoom);
        if(imageVisibled){
                    
            Dimension screen = getScene().getUIView().getScreenSize();
            Vector2Int reference = getScene().getUIView().getReferenceResolution();
            Vector2 pos = getPosition().add(getScreenAnchor()).multiply(Vector2.negativeY()).add(anchor)
                    .multiply(new Vector2((float)screen.width / (float)reference.getX(), (float)screen.height / (float)reference.getY()))
                    .add(posOffset);
            Vector2 scale = getScale().multiply(flip).multiply(scaleOffset)
                    .multiply(new Vector2((float)screen.width / (float)reference.getX(), (float)screen.height / (float)reference.getY()));
            screenPoint = new Vector2Int(Math.round(pos.getX() - (scale.getX() / 2)), Math.round(pos.getY() - (scale.getY() / 2)));
            screenSize = new Vector2Int(Math.round(scale.getX()), Math.round(scale.getY()));
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(ac);
            g2d.drawImage(image, screenPoint.getX()
                
                ,screenPoint.getY(),
                screenSize.getX()
                , 
                screenSize.getY(), null);
        }
    }
}
