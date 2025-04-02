/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Main;

import Animations.Animation;
import Animations.Animator;
import Datas.Vector2;
import Entities.Audios.AudioSource;
import Entities.Entity;
import Entities.SpriteEntity;
import Inputs.KeyControlable;
import Main.GameSystem.Cutscene.CutsceneControllable;
import Main.GameSystem.Door.Door;
import Main.GameSystem.Door.Key;
import Main.GameSystem.Inventory.Inventory;
import Main.GameSystem.Inventory.InventoryItem;
import Main.GameSystem.Inventory.Obtainable;
import Physics.Collider;
import Physics.Time;
import Saves.GameSave;
import Saves.SaveManager;
import Scenes.Scene;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author GA_IA
 */
public class Lucy extends PhysicableEntity implements KeyControlable, CutsceneControllable, Damagable{
    
    //Fundamental
    private float speed = 7.5f;
    private float jumpForce = 15.5f;
    
    private int health = 5;
    private int inventorySize = 6;
    
    //Wand
    private boolean attackable = false;
    private boolean isAttacking = false;
    private float attackTime = 0.5f;
    private float attackCountdown;
    private int facing = 1;
    
    //Wing
    private int maxJump = 1;
    private int currentJump = 0;
    
    //ETC.
    private boolean breakControl;
    private float damageCountdown = 0.3f;
    private float countdownLeft = 0f;
    
    private boolean both = false;
    
    private boolean isDamageTaken = false;
    private boolean statsOpen = false;
    
    private SpriteEntity bubble;
    
    private Interactable currentInteractable = null;
    
    //Gameplay System
    private GameSave save = SaveManager.getInstance().getCurrentSave();;
    private Inventory inventory;
    private Animator animator;
    private Map<String, Animation> animationMap;
    
    public Lucy(Scene s) {
        super(s);
        setName("Lucy");
        setTag("Player");
      
        inventory = new Inventory(inventorySize);
        
        animator = new Animator();
        
        animationMap = LucyAnimationMap.getInstance().getLucyMap();
        
        breakControl = false;
        
        bubble = new LucyBubble(s);
        addChild(bubble);
        
        refreshSave();
    }
    
    public void refreshSave(){
        health = save.getMaxHP();
        if(save.getWandAchived()){
            animationMap = LucyAnimationMap.getInstance().getLucyWandMap();
            attackable = true;
        }
        if(save.getWingAchived()){
            animationMap = LucyAnimationMap.getInstance().getLucyWingMap();
            maxJump = save.getMaxJump();
        }
        if(save.getHaloAchived()){
            animationMap = LucyAnimationMap.getInstance().getLucyHaloMap();
        }
    }
    
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    @Override
    public void start() {
        super.start();
        setPosition(getPosition().add(new Vector2(0, 1f)));
        setAnchor(new Vector2(0, 0.18f));
        setSpriteSize(getSpriteSize().multiply(1.65f));
        
        getCollider().setBound(getCollider().getBound().multiply(new Vector2(0.6f, 0.8f)));
        getCollider().setCenter(new Vector2(-0.05f, -0.16f));
        
        bubble.setLocalPosition(new Vector2(-0.37f, 0.37f));
        bubble.setLocalScale(bubble.getLocalScale().multiply(0.2f));
        
        addIgnore("Enemy");
        
        if(SaveManager.getInstance().getCurrentSave().getObtainedKey() == null){
            SaveManager.getInstance().getCurrentSave().setObtainedKey(new ArrayList<>());
        }
        for(Key key : SaveManager.getInstance().getCurrentSave().getObtainedKey()){
            inventory.addItem(key);
        }
    }

    @Override
    public void update() {
        super.update();
        
        if(currentInteractable != null){
            bubble.setActive(true);
        }
        else{
            bubble.setActive(false);
        }
        
        if(health > 0){
            if(!isDamageTaken && !isAttacking){
                if(!isGrounded()){
                    if(getVelocity().getY() > 0){
                        animator.setAnimation(animationMap.get("Jump"));
                    }
                    else{
                        animator.setAnimation(animationMap.get("Fall"));
                    }
                }
                else{
                    if(Math.abs(getVelocity().getX()) > 0.01f){
                        animator.setAnimation(animationMap.get("Run"));
                    }
                    else{
                        animator.setAnimation(animationMap.get("Breath"));
                    }
                }
            }
        }
        setSprite(animator.getFrame(Time.deltaTime()), true);
    }

    @Override
    public void fixedUpdate() {
        super.fixedUpdate();
        if(isDamageTaken)
        {
            if(Time.time() - countdownLeft >= damageCountdown){
                isDamageTaken = false;
            }
        }
        if(isAttacking)
        {
            if(Time.time() - attackCountdown >= attackTime){
                isAttacking = false;
            }
        }
    }

    public boolean isBreakControl() {
        return breakControl;
    }

    public void setBreakControl(boolean breakControl) {
        if(breakControl == this.breakControl){
            return;
        }
        this.breakControl = breakControl;
        setVelocity(new Vector2(0, getVelocity().getY()));
    }
    
    private void attack(){
        if(!attackable || isAttacking){
            return;
        }
        LucyProjectile p = new LucyProjectile(getScene(), Vector2.right().multiply(facing), 10f);
        p.setDamage(1);
        p.setPosition(getPosition().add(new Vector2(0.5f * facing, -0.18f)));
        getScene().addEntity(p);
        
        if(Math.abs(getVelocity().getX()) > 0.01f){
            animator.setAnimation(animationMap.get("Run Attack"));
        }
        else{
            animator.setAnimation(animationMap.get("Attack"));
        }
        isAttacking = true;
        attackCountdown = Time.time();
    }
    
    @Override
    public void onKeyPressed(KeyEvent e, int keyCode) {
        if(!breakControl){
            if(keyCode == KeyEvent.VK_D){
                if(getVelocity().getX() < 0){
                    setVelocity(new Vector2(0, getVelocity().getY()));
                    both = true;
                }
                else{
                    setVelocity(new Vector2(speed, getVelocity().getY()));
                    setFlip(Vector2.negativeX());
                    facing = 1;
                }
            }
            if(keyCode == KeyEvent.VK_A){
                if(getVelocity().getX() > 0){
                    setVelocity(new Vector2(0, getVelocity().getY()));
                    both = true;
                }
                else{
                    setVelocity(new Vector2(-speed, getVelocity().getY()));
                    setFlip(Vector2.one());
                    facing = -1;
                }
            }
            if(keyCode == KeyEvent.VK_SPACE){
                if(currentJump < maxJump){
                    setVelocity(new Vector2(getVelocity().getX(), jumpForce));
                    currentJump++;
                }
            }
            if(keyCode == KeyEvent.VK_J){
                if(currentInteractable != null){
                    currentInteractable.interact(this);
                    if(currentInteractable instanceof Entity ce){
                        if(ce.getTag().equals("Key Item")){
                            refreshSave();
                        }
                    }
                }
            }
            if(keyCode == KeyEvent.VK_ENTER){
                attack();
            }
            if(keyCode == KeyEvent.VK_E){
                inventory.scroll(1);
            }
            if(keyCode == KeyEvent.VK_Q){
                inventory.scroll(-1);
            }
        }
        if(keyCode == KeyEvent.VK_I){
            statsOpen = !statsOpen;
        }
    }
    
    @Override
    public void onKeyReleased(KeyEvent e, int keyCode) {
        if(!breakControl){
            if(getVelocity().getX() == 0 && both){
                if(keyCode == KeyEvent.VK_D){
                    setVelocity(new Vector2(-speed, getVelocity().getY()));
                    setFlip(Vector2.one());
                    facing = -1;
                    both = false;
                }
                else if(keyCode == KeyEvent.VK_A){
                    setVelocity(new Vector2(speed, getVelocity().getY()));
                    setFlip(Vector2.negativeX());
                    facing = 1;
                    both = false;
                }
                return;
            }
            if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_A){
                setVelocity(new Vector2(0, getVelocity().getY()));
            }
        }
    }

    @Override
    public void onKeyTyped(KeyEvent e, int keyCode) {
        
    }

    @Override
    public void onColliderEnter(Collider other) {
        super.onColliderEnter(other);
        if(other.getEntity() instanceof Obtainable){
            if(inventory.getItemCount() >= inventory.getSize()){
                return;
            }
            Obtainable ob = (Obtainable) other.getEntity();
            InventoryItem item = ob.obtain();
            if(item instanceof Key){
                SaveManager.getInstance().getCurrentSave().getObtainedKey().add(((Key) item));
            }
            inventory.addItem(item);
        }
    }

    @Override
    public void onColliderStay(Collider other) {
        super.onColliderStay(other);
        if(other.getEntity() instanceof Door){
            Door door = (Door) other.getEntity();
            if(!door.isIsOpen()){
                if(inventory.getSelectedItem() instanceof Key){
                    if(door.open((Key) (inventory.getSelectedItem()))){
                        Key key = (Key) inventory.getSelectedItem();
                        SaveManager.getInstance().getCurrentSave().getObtainedKey().remove(key);
                        inventory.removeItem(inventory.getSelectedSlot());
                    }
                }
            }
        }
        if(other.getEntity() instanceof Interactable){
            currentInteractable = (Interactable) other.getEntity();
        }
    }

    @Override
    public void onColliderExit(Collider other) {
        super.onColliderExit(other);
        if(other.getEntity() instanceof Interactable){
            currentInteractable = null;
        }
    }
    
    

    @Override
    public void onGroundTouch(Collider ground) {
        currentJump = 0;
    }

    @Override
    public void onGroundExit(Collider ground) {
        
    }
    
    @Override
    public void moveLeft() {
        setVelocity(new Vector2(-speed, getVelocity().getY()));
    }

    @Override
    public void moveRight() {
        setVelocity(new Vector2(speed, getVelocity().getY()));
    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {
        
    }

    @Override
    public void stop() {
        setVelocity(new Vector2(0, getVelocity().getY()));
    }

    @Override
    public Vector2 currentPosition() {
        return getPosition();
    }

    @Override
    public void damageTaken(int damage) {
        if(breakControl || isDamageTaken){
            return;
        }
        health -= damage;
        if(health < 1){
            stop();
            animator.setAnimation(animationMap.get("Dead"));
        }
        else{
            animator.setAnimation(animationMap.get("Hurt"));
            isDamageTaken = true;
            countdownLeft = Time.time();
            statsOpen = false;
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isStatsOpen() {
        return statsOpen;
    }
    
    public void setStatsOpen(boolean statsOpen) {
        this.statsOpen = statsOpen;
    }
    
}
