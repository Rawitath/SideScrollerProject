/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Example;

/**
 *
 * @author nirawith2548gmail.com
 */
import Entities.CollidableEntity;
import Physics.Collider;
import Physics.Time;
import Scenes.Scene;
import Utilities.FileReader;
import Datas.Vector2Int;
import Datas.Vector2;
import Datas.Constants;
import Main.UI.Example.BoxDialogueTest1;

public class Boss extends CollidableEntity{
    private float fallSpeed = 0f;
    private boolean ground;
    
    public Boss(Scene s){
        super(s);
        this.setSprite(FileReader.readImage("res/game/mutsuki.png"));
        this.setSpriteSize(new Vector2Int(700 , 700));
        this.getCollider().setSolid(true);
        this.setTag("Enemy");
        this.getCollider().setBound(new Vector2(7,7));
        this.setPosition(new Vector2(20,30));
    }
    
    @Override
    public void start() {
    }

    @Override
    public void update() {
        
    }

    @Override
    public void fixedUpdate() {
        this.setPosition(this.getPosition().translate(Vector2.down(), this.fallSpeed * Time.fixedDeltaTime()));
        if (!ground){
            this.fallSpeed += Constants.gravityValue;
        }
    }

    @Override
    public void onColliderEnter(Collider other) {
        if (other.getEntity().getTag().equals("Ground")){
            this.ground = true;
            this.fallSpeed = 0f;
        }
    }

    @Override
    public void onColliderStay(Collider other) {
        
    }

    @Override
    public void onColliderExit(Collider other) {
        if (other.getEntity().getTag().equals("Ground")){
            this.ground = false;
        }
    }
}
