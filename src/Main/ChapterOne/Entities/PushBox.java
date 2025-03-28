/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.ChapterOne.Entities;

import Datas.Vector2;
import Entities.CollidableEntity;
import Entities.Copyable;
import Entities.Entity;
import Main.Entities.Main.PhysicableEntity;
import Main.Entities.Main.Pushable;
import Physics.Collider;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class PushBox extends PhysicableEntity implements Copyable, Pushable{

    public PushBox(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/chapter/Box.png"));
        setTag("Pushable");
        setAnchor(new Vector2(-0.05f, 0.4f));
        getCollider().setBound(new Vector2(0.5f, 0.45f));
    }

    @Override
    public void onGroundTouch(Collider ground) {

    }

    @Override
    public void onGroundExit(Collider ground) {

    }

    @Override
    public <T extends Entity> T copyOf() {
        PushBox pb = new PushBox(getScene());
        return (T) pb;
    }
    
}
