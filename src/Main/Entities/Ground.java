/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities;

import Datas.Vector2;
import Datas.Vector2Int;
import Entities.CollidableEntity;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Ground extends CollidableEntity{
    
    public Ground(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/ground.png"));
        setPosition(new Vector2(0, -16f));
        setScale(new Vector2(20f ,getScale().getY()));
        getCollider().setBound(new Vector2(20f ,3f));
        setTag("Ground");
//        setColliderVisibled(true);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {
        
    }

    @Override
    public void onColliderEnter(Collider other) {

    }

    @Override
    public void onColliderStay(Collider other) {

    }

    @Override
    public void onColliderExit(Collider other) {

    }
    
}
