/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Main;

import Animations.Animation;
import Main.Entities.Main.Animation.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author GA_IA
 */
public class LucyAnimationMap {
    public static LucyAnimationMap instance;
    private Map<String, Animation> lucyMap;
    private Map<String, Animation> lucyWandMap;
    private Map<String, Animation> lucyWingMap;
    private Map<String, Animation> lucyHaloMap;
    
    private LucyAnimationMap(){
        lucyMap = new HashMap<>();
        lucyWandMap = new HashMap<>();
        lucyWingMap = new HashMap<>();
        lucyHaloMap = new HashMap<>();
        
        lucyMap.put("Breath", new LucyBreath());
        lucyWandMap.put("Breath", new LucyWandBreath());
        lucyWingMap.put("Breath", new LucyWingBreath());
        lucyHaloMap.put("Breath", new LucyHaloBreath());
        
        lucyMap.put("Run", new LucyRun());
        lucyWandMap.put("Run", new LucyWandRun());
        lucyWingMap.put("Run", new LucyWingRun());
        lucyHaloMap.put("Run", new LucyHaloRun());
        
        lucyMap.put("Jump", new LucyJump());
        lucyWandMap.put("Jump", new LucyWandJump());
        lucyWingMap.put("Jump", new LucyWingJump());
        lucyHaloMap.put("Jump", new LucyHaloJump());
        
        lucyMap.put("Fall", new LucyFall());
        lucyWandMap.put("Fall", new LucyWandFall());
        lucyWingMap.put("Fall", new LucyWingFall());
        lucyHaloMap.put("Fall", new LucyHaloFall());
        
        lucyMap.put("Hurt", new LucyHurt());
        lucyWandMap.put("Hurt", new LucyWandHurt());
        lucyWingMap.put("Hurt", new LucyWingHurt());
        lucyHaloMap.put("Hurt", new LucyHaloHurt());
        
        lucyMap.put("Dead", new LucyDead());
        lucyWandMap.put("Dead", new LucyWandDead());
        lucyWingMap.put("Dead", new LucyWingDead());
        lucyHaloMap.put("Dead", new LucyHaloDead());
        
        lucyWandMap.put("Attack", new LucyWandAttack());
        lucyWingMap.put("Attack", new LucyWingAttack());
        lucyHaloMap.put("Attack", new LucyHaloAttack());
        
        lucyWandMap.put("Run Attack", new LucyWandRunAttack());
        lucyWingMap.put("Run Attack", new LucyWingRunAttack());
        lucyHaloMap.put("Run Attack", new LucyHaloRunAttack());
        
        lucyWingMap.put("Fly", new LucyWingFly());
        lucyHaloMap.put("Fly", new LucyHaloFly());
    }
    
    public static LucyAnimationMap getInstance(){
        if(instance == null){
            instance = new LucyAnimationMap();
        }
        return instance;
    }

    public Map<String, Animation> getLucyMap() {
        return lucyMap;
    }

    public Map<String, Animation> getLucyWandMap() {
        return lucyWandMap;
    }

    public Map<String, Animation> getLucyWingMap() {
        return lucyWingMap;
    }

    public Map<String, Animation> getLucyHaloMap() {
        return lucyHaloMap;
    }
    
}
