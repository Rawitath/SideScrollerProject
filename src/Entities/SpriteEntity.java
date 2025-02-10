/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Datas.Vector2;
import Scenes.Scene;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public abstract class SpriteEntity extends Entity implements GraphicLoopable{

    private BufferedImage sprite;
    private Vector2 spriteSize;

    public SpriteEntity(Scene s) {
        super(s);
        spriteSize = new Vector2(100,100);
    }

    public BufferedImage getSprite() {
        return sprite;
    }
    
    public void setSprite(BufferedImage sprite, boolean keepSpriteSize) {
        this.sprite = sprite;
        if(!keepSpriteSize){
            setSpriteSize(new Vector2(sprite.getWidth(), sprite.getHeight()));
        }
    }
    
    public void setSprite(BufferedImage sprite) {
        setSprite(sprite, false);
    }

    public Vector2 getSpriteSize() {
        return spriteSize;
    }

    public void setSpriteSize(Vector2 spriteSize) {
        this.spriteSize = spriteSize;
    }
    

    @Override
    public void draw(Graphics g, Vector2 posOffset, Vector2 scaleOffset) {
        Vector2 pos = Vector2.copy(getPosition());
        pos.add(posOffset);
        Vector2 scale = Vector2.copy(getScale());
        scale.add(scaleOffset);
        
//        g.drawImage(sprite, pos.getX(), pos.getY(), scale.getX() * spriteSize.getX(), scale.getY() * spriteSize.getY(), null);
        g.drawImage(sprite, pos.getX(),0, scale.getX() * spriteSize.getX(), scale.getY() * spriteSize.getY(), null);
    }
}
