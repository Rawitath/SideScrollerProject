/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main.Stats;

import Animations.Animation;
import Animations.Animator;
import Datas.Vector2;
import Entities.UI.UIEntity;
import Entities.UI.UIImage;
import Main.Entities.Main.LucyAnimationMap;
import Physics.Time;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class LucyDisplay extends UIImage{

    private Animator animator;
    private Animation lucyAnim;
    
    public LucyDisplay(Scene s) {
        super(s);
        animator = new Animator();
        lucyAnim = LucyAnimationMap.getInstance().getLucyMap().get("Breath");
        animator.setAnimation(lucyAnim);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        setImage(animator.getFrame(Time.deltaTime()));
        setScale(new Vector2(512,512));
    }

    @Override
    public void fixedUpdate() {

    }
    
}
