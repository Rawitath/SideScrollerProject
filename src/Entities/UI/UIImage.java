/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.UI;

import Datas.Vector2;
import Datas.Vector2Int;
import Scenes.Scene;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public abstract class UIImage extends UIEntity{

    private BufferedImage image;
    private Vector2Int imageSize;
    private Vector2 anchor;
    private boolean imageVisibled;
    private Vector2 flip;
    
    public UIImage(Scene s) {
        super(s);
        imageSize = new Vector2Int(100,100);
        anchor = new Vector2(0f, 0f);
//        pixelRatio = 0.01f;
        imageVisibled = true;
        
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

    public void setImage(BufferedImage image, boolean keepSpriteSize) {
        this.image = image;
        if(!keepSpriteSize){
            setImageSize(new Vector2Int(image.getWidth(), image.getHeight()));
        }
    }
    
    public void setImage(BufferedImage image) {
        setImage(image, false);
    }

    public Vector2Int getImageSize() {
        return imageSize;
    }

    public void setImageSize(Vector2Int imageSize) {
        this.imageSize = imageSize;
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
        super.draw(g, posOffset, scaleOffset, zoom);
        if(imageVisibled){
                    
            Dimension screen = getScene().getUIView().getScreenSize();
            Vector2Int reference = getScene().getUIView().getReferenceResolution();
            Vector2 pos = getPosition().add(getScreenAnchor()).multiply(Vector2.negativeY()).add(anchor)
                    .multiply(new Vector2((float)screen.width / (float)reference.getX(), (float)screen.height / (float)reference.getY()))
                    .add(posOffset);
            Vector2 scale = getScale().multiply(flip).multiply(scaleOffset).multiply(imageSize)
                    .multiply(new Vector2((float)screen.width / (float)reference.getX(), (float)screen.height / (float)reference.getY()));
            g.drawImage(image, 
                Math.round(pos.getX() - (scale.getX() / 2))
                ,Math.round(pos.getY() - (scale.getY() / 2)),
                Math.round(scale.getX())
                , Math.round(scale.getY())
                , null);
        }
    }
}
