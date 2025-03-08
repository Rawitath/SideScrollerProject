/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.UI;

import Datas.Vector2;
import Datas.Vector2Int;
import Main.GameSystem.Inventory.Inventory;         //Inventory update
import Main.GameSystem.Inventory.InventoryItem;     //Inventory update
import Main.Entities.Example.Lucy;              //Inventory update
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
    private boolean clipping;
    private Vector2Int clippingPosition;
    private Vector2Int clippingSize;

    public boolean isClipping() {
        return clipping;
    }

    public void setClipping(boolean clipping) {
        this.clipping = clipping;
    }
    
    public Vector2Int getClippingPosition() {
        return clippingPosition;
    }

    public void setClippingPosition(Vector2Int clippingPosition) {
        this.clippingPosition = clippingPosition;
        setClippingSize(clippingSize);
    }

    public Vector2Int getClippingSize() {
        return clippingSize;
    }

    public void setClippingSize(Vector2Int clippingSize) {
        if(clippingPosition.getX() + clippingSize.getX() > image.getWidth()){
            clippingSize = new Vector2Int(image.getWidth() - clippingPosition.getX(), clippingSize.getY());
        }
        if(clippingSize.getX() <= 0){
            System.err.println("The minimum of clippingSize is 1");
            clippingSize = new Vector2Int(1, clippingSize.getY());
        }
        if(clippingSize.getY() <= 0){
            System.err.println("The minimum of clippingSize is 1");
            clippingSize = new Vector2Int(clippingSize.getX(), 1);
        }
        if(clippingPosition.getY() + clippingSize.getY() > image.getHeight()){
            clippingSize = new Vector2Int(clippingSize.getX(), image.getHeight() - clippingPosition.getY());
        }
        this.clippingSize = clippingSize;
    }
    
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
        clipping = false;
        clippingPosition = new Vector2Int();
        clippingSize = new Vector2Int(image.getWidth(), image.getHeight());
        
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
            BufferedImage img = image;
            if(clipping){
                img = image.getSubimage(clippingPosition.getX(), clippingPosition.getY(), clippingSize.getX(), clippingSize.getY());
            }
            
            g2d.drawImage(img, screenPoint.getX()
                
                ,screenPoint.getY(),
                screenSize.getX()
                , 
                screenSize.getY(), null);
        }
        super.draw(g, posOffset, scaleOffset, zoom);
    }
}
