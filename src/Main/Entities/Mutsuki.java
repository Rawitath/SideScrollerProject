/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities;

import Datas.Vector2;
import Entities.CollidableEntity;
import Entities.SpriteEntity;
import Physics.Collider;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Mutsuki extends CollidableEntity{

    public Mutsuki(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/mutsuki.png"));
    }

    @Override
    public void start() {
        setPosition(new Vector2(-9f, 0f));
        getCollider().setBound(new Vector2(4.2f, 4.2f));
        setColliderVisibled(true);
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
