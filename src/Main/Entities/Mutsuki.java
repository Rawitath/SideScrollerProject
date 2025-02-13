/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities;

import Datas.Constants;
import Datas.Vector2;
import Entities.CollidableEntity;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Mutsuki extends CollidableEntity{
    
    private Vector2 direction;
    private float speed = 18f;
    private float fallAcceration = 0f;
    private boolean grounded = false;

    public Mutsuki(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/mutsuki.png"));
    }

    @Override
    public void start() {
        setPosition(new Vector2(-170f, 0f));
        getCollider().setBound(new Vector2(4.2f, 4.2f));
//        setColliderVisibled(true);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void fixedUpdate() {
        setPosition(getPosition().translate(Vector2.right(), speed * Time.fixedDeltaTime()));
        setPosition(getPosition().translate(Vector2.down(), fallAcceration * Time.fixedDeltaTime()));
        if(!grounded){
            fallAcceration += Constants.gravityValue;
        }
    }

    @Override
    public void onColliderEnter(Collider other) {
        if(other.getEntity().getTag().equals("Ground")){
            fallAcceration = 0f;
            grounded = true;
        }
    }

    @Override
    public void onColliderStay(Collider other) {
        
        
    }

    @Override
    public void onColliderExit(Collider other) {
        if(other.getEntity().getTag().equals("Ground")){
            grounded = false;
        }
    }
    
}
