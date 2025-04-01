/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animations;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author GA_IA
 */
public class Animator {
//    private List<Animation> animations;
    private Animation animation;
    private float time;
    private int frame;

    public Animator() {
//        animations = new CopyOnWriteArrayList<>();
    }
    
    public Animation getAnimation(){
        return animation;
    }

//    public List<Animation> getAnimations() {
//        return animations;
//    }
//    public void addAnimation(Animation anim){
//        animations.add(anim);
//    }
//    public void removeAnimation(Animation anim){
//        animations.remove(anim);
//    }
    public void setAnimation(Animation animation, boolean resetAnimation) {
        if(this.animation == null || (this.animation.getClass() != animation.getClass() || resetAnimation)){
            time = 0f;
            frame = 0;
            this.animation = animation;
        }
    }
    public void setAnimation(Animation animation) {
        setAnimation(animation, false);
    }
    
    public boolean isAnimationEnd(){
        return frame == animation.getSpriteFrame().length - 1;
    }

    public BufferedImage getFrame(float time) {
        if(animation == null){
            System.err.println("Animation has not been set");
            return null;
        }
        this.time += time;
        if(this.time >= 1f / animation.getFps()){
            this.time = 0f;
            if(frame + 1 >= animation.getSpriteFrame().length && animation.isLoop()){
                frame = 0;
            }
            else{
                if(frame < animation.getSpriteFrame().length - 1){
                    frame++;
                }     
            }
        }
        return animation.getSpriteFrame()[frame];
    }
}
