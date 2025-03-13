/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Example;

import Main.GameSystem.Inventory.Inventory;
import Animations.Animator;
import Main.UI.Example.HeartContainer;
import Datas.Constants;
import Datas.Vector2;
import Datas.Vector2Int;
import Debugger.DebugManager;
import Entities.Audios.AudioSource;
import Entities.CollidableEntity;
import Entities.UI.UIText;
import Inputs.KeyControlable;
import Main.Animations.Example.LucyBreathAnim;
import Main.GameSystem.Inventory.InventoryItem;
import Physics.Collider;
import Physics.Time;
import Saves.GameSave;
import Saves.SaveSerializer;
import Scenes.Scene;
import Scenes.SceneManager;
import Utilities.FileReader;
import java.awt.Font;
import java.awt.event.*; //Inventory update

/**
 *
 * @author GA_IA
 */
public class Lucy extends CollidableEntity implements KeyControlable{

    private Vector2 direction;
    private float speed = 18f;
    private float fallSpeed = 0f;
    private UIText lifeNum;
    private HeartContainer heartContainer;
    
    private Animator animator;
    
    private int life = 3;
    
    private Vector2 lockDirection;
    private Collider lockObject;
    
    private boolean grounded = false;
    private Collider groundObject = null;
    private Vector2 groundPosition = null;
    
    private Inventory inventory;//Inventory update
    public Lucy(Scene s) {
        super(s);
        setSprite(FileReader.readImage("res/game/lucypixel.png"));
        direction = Vector2.zero();
        lockDirection = Vector2.zero();
        
        animator = new Animator();
        animator.setAnimation(new LucyBreathAnim());
        
        inventory = new Inventory(); //Inventory update
        //inventory.addItem(new InventoryItem("Name", 1, FileReader.readImage("img location")));
    }

    @Override
    public void start() {
        setPosition(new Vector2(9f, 0f));
        getCollider().setBound(new Vector2(4.2f, 7.6f));
        getCollider().setCenter(new Vector2(0f, -0.8f));
        lifeNum = getScene().getEntity("Life");
        lifeNum.setFont("res/font/Itim-Regular.ttf", Font.TRUETYPE_FONT);
        heartContainer = getScene().getEntity("HeartContainer");
        heartContainer.setHeart(life);
        
        AudioSource a = getScene().getEntity("Music");
//        a.loop(true);
//        a.play();
        setSpriteSize(new Vector2Int(128, 128));
        setPixelRatio(0.1f);
    }

    @Override
    public void update() {
        lifeNum.setText("Life : "+ String.valueOf(life));
        setSprite(animator.getFrame(Time.deltaTime()));
        //getScene().getCamera().setPosition(getPosition().multiply(getScene().getCamera().getZoom()).multiply(Vector2.negativeY()));
    }
            
    @Override
    public void fixedUpdate() {
        setPosition(getPosition().translate(direction.add(lockDirection.negative()), speed * Time.fixedDeltaTime()));
        setPosition(getPosition().translate(Vector2.down(), fallSpeed * Time.fixedDeltaTime()));
        if(!grounded){
            fallSpeed += Constants.gravityValue;
        }
    }

    @Override
    public void onKeyPressed(KeyEvent e, int keyCode) {
        if(keyCode == KeyEvent.VK_D){
            if(direction.getX() <= -1){
                direction = Vector2.zero();
            }
            else{
                direction = Vector2.right();
                setFlip(Vector2.negativeX());
            }
        }
        if(keyCode == KeyEvent.VK_A){
            if(direction.getX() >= 1){
                direction = Vector2.zero();
            }
            else{
                direction = Vector2.left();
                setFlip(Vector2.one());
            }
        }
        if(keyCode == KeyEvent.VK_SPACE){
            if(grounded){
                fallSpeed = -80f;
            }
        }
        if(keyCode == KeyEvent.VK_P){
            SceneManager.loadScene(0);
//            GameSave save = new GameSave();
//            save.saveNumber = 3;
//            save.currentChapter = 1;
//            SaveSerializer.save(save);
        }
        if(keyCode == KeyEvent.VK_O){
            SceneManager.loadScene(0);
//            GameSave save = SaveSerializer.load(0);
//            System.out.println(save.saveNumber);
//            System.out.println(save.currentChapter);
        }
        if (keyCode == KeyEvent.VK_E) { //Inventory update E to use
            inventory.useSelectedItem();
        }
        if (keyCode == KeyEvent.VK_U) { //U to Scroll Forward
            
            inventory.scroll(-1);
        }
        if (keyCode == KeyEvent.VK_I) { //I to Scroll Backware
            inventory.scroll(1);
        }
        if (keyCode == KeyEvent.VK_T) { //T to add item(debug)
            inventory.removeItem(inventory.getSelectedSlot());
        }
        if (keyCode == KeyEvent.VK_Y) { //Y to remove item(debug)
            InventoryItem item = new InventoryItem("MyItem", 1, FileReader.readImage("res/game/kotori.jpg"));
            inventory.addItem(item);
        }
    }
    public Inventory getInventory() { //Inventory update getInventory method
        return inventory;
    }
    
    @Override
    public void onKeyReleased(KeyEvent e, int keyCode) {
        if(direction.getX() == 0){
            if(keyCode == KeyEvent.VK_D){
                direction = Vector2.left();
                setFlip(Vector2.one());
            }
            else if(keyCode == KeyEvent.VK_A){
                direction = Vector2.right();
                setFlip(Vector2.negativeX());
            }
            return;
        }
        if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_A){
            direction = Vector2.zero();
        }
    }

    @Override
    public void onKeyTyped(KeyEvent e, int keyCode) {
        
    }
    
    private void onGroundTouch(Collider other){
        if(fallSpeed >= 0f){
            fallSpeed = 0f;
            grounded = true;
            groundObject = other;
            groundPosition = other.getEntity().getPosition();
        }
    }
    private void onGroundExit(){
        groundObject = null;
            grounded = false;
            groundPosition = null;
    }

    @Override
    public void onColliderEnter(Collider other) {
        if(other.isSolid()){
            if(!grounded){
                //Check the bottom is colliding with top of other
                if((getPosition().getY() + getCollider().getCenter().getY() - getCollider().getBound().getY() / 2
                        <=
                    other.getEntity().getPosition().getY() + other.getCenter().getY() + other.getBound().getY() / 2
                    
                        &&
                        getPosition().getY() + getCollider().getCenter().getY() - getCollider().getBound().getY() / 2
                        >
                    (other.getEntity().getPosition().getY() + other.getCenter().getY() + other.getBound().getY() / 2) - 0.15f)){
                    onGroundTouch(other);
                }
                //Check the top is colliding with bottom of other
                if(getPosition().getY() + getCollider().getCenter().getY() + getCollider().getBound().getY() / 2
                        >=
                    other.getEntity().getPosition().getY() + other.getCenter().getY() - other.getBound().getY() / 2
                        &&
                        getPosition().getY() + getCollider().getCenter().getY() + getCollider().getBound().getY() / 2
                        <
                    (other.getEntity().getPosition().getY() + other.getCenter().getY() - other.getBound().getY() / 2) + 0.15f){
                    fallSpeed = 0f;
                }
            }
                
                
        }
        
        if(other.getEntity().getTag().equals("Enemy")){            
            life--;
            heartContainer.setHeart(life);
        }
    }
    
    @Override
    public void onColliderStay(Collider other) {
        if(other.isSolid()){
            //Check if standing on top of solid object
            if(groundObject == other){
                if (!groundPosition.equals(other.getEntity().getPosition())){
                    Vector2 drift = getPosition().add(groundPosition.add(other.getEntity().getPosition().negative()).multiply(Vector2.one().negative()));
                    if(!lockDirection.equals(Vector2.zero())){
                        drift = drift.multiply(Vector2.up());
                    }
                    setPosition(drift);
                }
                groundPosition = other.getEntity().getPosition();
            }
            //Check if approch right or left of other, lockDirection will lock if approch is true
            if(groundObject != other){
                        if(getPosition().getX() + getCollider().getCenter().getX() > other.getEntity().getPosition().getX() + other.getCenter().getX()){
                            lockDirection = Vector2.left();
                            lockObject = other;
                            if(other.getEntity().getTag().equals("Pushable")){
                                other.getEntity().setPosition(other.getEntity().getPosition().translate(Vector2.left(), speed * Time.fixedDeltaTime()));
                            }
                        }
                        else if(getPosition().getX() + getCollider().getCenter().getX() < other.getEntity().getPosition().getX() + other.getCenter().getX()){
                            lockDirection = Vector2.right();
                            lockObject = other;
                            if(other.getEntity().getTag().equals("Pushable")){
                                other.getEntity().setPosition(other.getEntity().getPosition().translate(Vector2.right(), speed * Time.fixedDeltaTime()));
                            }
                        }
           }                      
        }
    }

    @Override
    public void onColliderExit(Collider other) {
        if(other.isSolid()){
            if(groundObject == other){
                onGroundExit();
            }
            if(lockObject == other){
                lockDirection = Vector2.zero();
                lockObject = null;
            }
        }
        if(other.getEntity().getTag().equals("Enemy")){
            
        }
    }
}
