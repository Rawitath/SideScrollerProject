/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main.Stats;

import Datas.Vector2;
import Entities.Entity;
import static Entities.UI.UIEntity.TOP;
import Main.UI.Main.FrameThree;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class RightFrame extends FrameThree{
    
    private HeaderText lucyHeader;
    private HeaderText hearts;
    private HeaderText obtainableHeader;
    private ObtainableGroup wings;
    private ObtainableGroup halo;
    
    public RightFrame(Scene s) {
        super(s);
        lucyHeader = new HeaderText(s);
        hearts = new HeaderText(s);
        obtainableHeader = new HeaderText(s);
        wings = new ObtainableGroup(s);
        halo = new ObtainableGroup(s);
        addChild(lucyHeader);
        addChild(hearts);
        addChild(obtainableHeader);
        addChild(wings);
        addChild(halo);
        setHeart(3, 5);
    }
    @Override
    public void start() {
        super.start();
        lucyHeader.setScreenAnchor(TOP);
        hearts.setScreenAnchor(TOP);
        obtainableHeader.setScreenAnchor(TOP);
        wings.setScreenAnchor(TOP);
        halo.setScreenAnchor(TOP);
        float posY = -90;
        float spacing = 100;
        for(Entity e : getChilds()){
            e.setPosition(new Vector2(e.getPosition().getX(), posY));
            posY -= spacing;
        }
        lucyHeader.setText("Lucy");
        obtainableHeader.setText("Obtainable");
        
        wings.getImage().setImage(FileReader.readImage("res/game/hud/statmenu/Layer_3_copy.png"));
        halo.getImage().setImage(FileReader.readImage("res/game/hud/statmenu/Layer_4.png"));
        
    }
    public void setHeart(int currentHeart, int maxHeart){
        hearts.setText("Health : "+currentHeart+"/"+maxHeart);
    }
}
