/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Saves;

import Main.GameSystem.Door.Key;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author GA_IA
 */
public class GameSave implements Serializable{
    private static final long serialVersionUID = 789514L;
    
    private String saveID = null;
    private LocalDateTime saveCreated = null;
    private Long playTime = null;
    private Integer saveNumber = null;
    private Integer currentChapter = 0;
    private Integer[] items = null;
    private Integer death = 0;
    private Integer maxHP = 5;
    private Integer maxJump = 1;
    private Boolean prolouge = false;
    private Boolean epilogue = false;
    private Boolean wandAchived = false;
    private Boolean wingAchived = false;
    private Boolean haloAchived = false;
    private Integer currentCheckpoint = null;
    private List<Integer> unlockedCheckpoints = new ArrayList<>();
    private List<Integer> unlockedDoors = new ArrayList<>();
    private List<Integer> cutsceneBosses = new ArrayList<>();
    private List<Integer> defeatedBosses = new ArrayList<>();
    private List<Integer> uniqueItems = new ArrayList<>();
    
    private Boolean one_GroundDrop = false;
    private Boolean one_GroundFalling = false;
    private Boolean one_WallDestroy = false;
    
    private Boolean two_WallDestroy = false;
    
    private Boolean three_WallDestroy = false;
    
    private Map<Integer, Integer> defeatedEnemy = new HashMap<>();
    
    private transient Integer currentHearts = null;
    private transient String markerName = null;
    private transient List<Key> obtainedKey = new ArrayList<>();

    public List<Key> getObtainedKey() {
        return obtainedKey;
    }

    public void setObtainedKey(List<Key> obtainedKey) {
        this.obtainedKey = obtainedKey;
    }

    
    public Integer getCurrentHearts() {
        return currentHearts;
    }

    public void setCurrentHearts(Integer currentHearts) {
        this.currentHearts = currentHearts;
    }

    public String getMarkerName() {
        return markerName;
    }

    public void setMarkerName(String markerName) {
        this.markerName = markerName;
    }

    
    
    public String getSaveID() {
        return saveID;
    }

    public void setSaveID(String saveID) {
        if(this.saveID != null){
            System.err.println("Save already written!");
            return;
        }
        this.saveID = saveID;
    }

    public LocalDateTime getSaveCreated() {
        return saveCreated;
    }

    public void setSaveCreated(LocalDateTime saveCreated) {
        if(this.saveCreated != null){
            System.err.println("Save Date already written!");
            return;
        }
        this.saveCreated = saveCreated;
    }

    public Long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Long playTime) {
        this.playTime = playTime;
    }

    public Integer getSaveNumber() {
        return saveNumber;
    }

    public void setSaveNumber(Integer saveNumber) {
        if(this.saveNumber != null){
            System.err.println("Save Number already written!");
            return;
        }
        this.saveNumber = saveNumber;
    }

    public Integer getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(Integer currentChapter) {
        this.currentChapter = currentChapter;
    }

    public Integer[] getItems() {
        return items;
    }

    public void setItems(Integer[] items) {
        this.items = items;
    }

    public Integer getDeath() {
        return death;
    }

    public void setDeath(Integer death) {
        this.death = death;
    }
    
    public Integer getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(Integer maxHP) {
        this.maxHP = maxHP;
    }

    public Integer getMaxJump() {
        return maxJump;
    }

    public void setMaxJump(Integer maxJump) {
        this.maxJump = maxJump;
    }
    
    public Boolean getProlouge() {
        return prolouge;
    }

    public void setProlouge(Boolean prolouge) {
        this.prolouge = prolouge;
    }

    public Boolean getEpilogue() {
        return epilogue;
    }

    public void setEpilogue(Boolean epilogue) {
        this.epilogue = epilogue;
    }

    public Boolean getWandAchived() {
        return wandAchived;
    }

    public void setWandAchived(Boolean wandAchived) {
        this.wandAchived = wandAchived;
    }

    public Boolean getWingAchived() {
        return wingAchived;
    }

    public void setWingAchived(Boolean wingAchived) {
        this.wingAchived = wingAchived;
    }

    public Boolean getHaloAchived() {
        return haloAchived;
    }

    public void setHaloAchived(Boolean haloAchived) {
        this.haloAchived = haloAchived;
    }

    public Integer getCurrentCheckpoint() {
        return currentCheckpoint;
    }

    public void setCurrentCheckpoint(Integer currentCheckpoint) {
        this.currentCheckpoint = currentCheckpoint;
    }

    public List<Integer> getUnlockedCheckpoints() {
        return unlockedCheckpoints;
    }

    public void setUnlockedCheckpoints(List<Integer> unlockedCheckpoints) {
        this.unlockedCheckpoints = unlockedCheckpoints;
    }

    public List<Integer> getUnlockedDoors() {
        return unlockedDoors;
    }

    public void setUnlockedDoors(List<Integer> unlockedDoors) {
        this.unlockedDoors = unlockedDoors;
    }

    public List<Integer> getCutsceneBosses() {
        return cutsceneBosses;
    }

    public void setCutsceneBosses(List<Integer> cutsceneBosses) {
        this.cutsceneBosses = cutsceneBosses;
    }

    public List<Integer> getDefeatedBosses() {
        return defeatedBosses;
    }

    public void setDefeatedBosses(List<Integer> defeatedBosses) {
        this.defeatedBosses = defeatedBosses;
    }

    public List<Integer> getUniqueItems() {
        return uniqueItems;
    }

    public void setUniqueItems(List<Integer> uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    public Boolean getOne_GroundDrop() {
        return one_GroundDrop;
    }

    public void setOne_GroundDrop(Boolean one_GroundDrop) {
        this.one_GroundDrop = one_GroundDrop;
    }

    public Boolean getOne_GroundFalling() {
        return one_GroundFalling;
    }

    public void setOne_GroundFalling(Boolean one_GroundFalling) {
        this.one_GroundFalling = one_GroundFalling;
    }

    public Boolean getOne_WallDestroy() {
        return one_WallDestroy;
    }

    public void setOne_WallDestroy(Boolean one_WallDestroy) {
        this.one_WallDestroy = one_WallDestroy;
    }

    public Boolean getTwo_WallDestroy() {
        return two_WallDestroy;
    }

    public void setTwo_WallDestroy(Boolean two_WallDestroy) {
        this.two_WallDestroy = two_WallDestroy;
    }

    public Boolean getThree_WallDestroy() {
        return three_WallDestroy;
    }

    public void setThree_WallDestroy(Boolean three_WallDestroy) {
        this.three_WallDestroy = three_WallDestroy;
    }

    public Map<Integer, Integer> getDefeatedEnemy() {
        return defeatedEnemy;
    }

    public void setDefeatedEnemy(Map<Integer, Integer> defeatedEnemy) {
        this.defeatedEnemy = defeatedEnemy;
    }
    
}
