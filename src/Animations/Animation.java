/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animations;

import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public abstract class Animation {
    private BufferedImage spriteSheet;
    private BufferedImage[] spriteFrame;
    private int fps;
    private boolean loop;
    
    public Animation(){
        this.spriteSheet = null;
        fps = 12;
        loop = false;
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public void setSpriteSheet(BufferedImage spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public BufferedImage[] getSpriteFrame() {
        return spriteFrame;
    }
    
    public void sliceSprite(int column, int spriteWidth, int spriteHeight, int spriteCount){
        spriteFrame = new BufferedImage[spriteCount];
        for(int i = 0; i < spriteCount; i++){
            spriteFrame[i] = spriteSheet.getSubimage(
                    (spriteWidth * (i - (column * (int)Math.floor(i / column))))
                    , spriteHeight * (int)Math.floor(i / column)
                    , spriteWidth
                    , spriteHeight);
        }
        fps = spriteCount;
    }
}
