/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Datas.*;
import Scenes.Scene;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.math.*;

/**
 *
 * @author GA_IA
 */
public abstract class SpriteEntity extends Entity{

    private BufferedImage sprite;
    private Vector2Int spriteSize;
    private float pixelRatio;
    private Vector2 anchor;
    private boolean spriteVisibled;
    private Vector2 flip;

    public SpriteEntity(Scene s) {
        super(s);
        spriteSize = new Vector2Int(100,100);
        anchor = new Vector2(0f, 0f);
        pixelRatio = 0.01f;
        setSpriteVisibled(true);
        
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

    public boolean isSpriteVisibled() {
        return spriteVisibled;
    }

    public void setSpriteVisibled(boolean spriteVisibled) {
        this.spriteVisibled = spriteVisibled;
    }
    
    public BufferedImage getSprite() {
        return sprite;
    }
    
    public void setSprite(BufferedImage sprite, boolean keepSpriteSize) {
        this.sprite = sprite;
        if(!keepSpriteSize){
            setSpriteSize(new Vector2Int(sprite.getWidth(), sprite.getHeight()));
        }
    }
    
    public void setSprite(BufferedImage sprite) {
        setSprite(sprite, false);
    }

    public Vector2Int getSpriteSize() {
        return spriteSize;
    }

    public void setSpriteSize(Vector2Int spriteSize) {
        this.spriteSize = spriteSize;
    }

    @Override
    public void draw(Graphics g, Vector2 posOffset, Vector2 scaleOffset, float zoom) {
        if(spriteVisibled){
            Vector2 pos = getPosition().multiply(zoom).add(posOffset);
            Vector2 scale = getScale().multiply(flip).multiply(scaleOffset).multiply(spriteSize).multiply(pixelRatio);
            g.drawImage(sprite, 
                Math.round(pos.getX() - (scale.getX() / 2) + anchor.getX())
                ,Math.round(pos.getY() - (scale.getY() / 2) + anchor.getY()),
                Math.round(scale.getX())
                , Math.round(scale.getY())
                , null);
        }
        super.draw(g, posOffset, scaleOffset, zoom);
    }
}
