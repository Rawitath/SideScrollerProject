/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Example;

/**
 *
 * @author nirawith2548gmail.com
 */

import Datas.Vector2;
import Scenes.Scene;
import java.awt.image.BufferedImage;
import Entities.CollidableEntity;
import Physics.Collider;
import Main.UI.Example.BoxDialogueTest1;
import Saves.SaveManager;
import Saves.GameSave;
import Saves.SaveSerializer;
import Utilities.FileReader;

public class CutsceneOne extends CollidableEntity{
    private BoxDialogueTest1 bd1;
    private GameSave gs;
    
    public CutsceneOne(Scene s){
        super(s);
        this.setSprite(new BufferedImage(1,1 ,BufferedImage.TYPE_INT_ARGB));
        this.getCollider().setSolid(false);
        this.bd1 = new BoxDialogueTest1(s);
        bd1.setActive(false);
        getScene().addEntity(bd1);
        
        gs = SaveManager.getInstance().getCurrentSave();
    }

    @Override
    public void start() {
        this.setPosition(new Vector2(20.5f, -10f));
        this.getCollider().setBound(new Vector2(10f, 10f));
    }

    @Override
    public void update() {
    }

    @Override
    public void fixedUpdate() {
        
    }

    @Override
    public void onColliderEnter(Collider other) {
        if(other.getEntity().getName().equals("Lucy")){
            bd1.setActive(true);
//            this.gs.cutscene[0] = true;
            SaveSerializer.save(this.gs);
        }
    }

    @Override
    public void onColliderStay(Collider other) {
        
    }

    @Override
    public void onColliderExit(Collider other) {
        if (other.getEntity().getName().equals("Lucy")){
            this.bd1.setActive(false);
        }
    }
    
}
