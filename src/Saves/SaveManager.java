/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Saves;

import java.time.LocalDateTime;

/**
 *
 * @author GA_IA
 */
public class SaveManager {
    private static SaveManager instance;
    private GameSave currentSave;
    private boolean useDebugSave = false;
    
    private GameSave debugSave(){
        GameSave gs = new GameSave();
        gs.setSaveID("DEBUG");
        gs.setSaveNumber(0);
        gs.setSaveCreated(LocalDateTime.now());
        gs.setPlayTime(0L);
        
        
        return gs;
    }
    
    private SaveManager(){
        currentSave = null;
        if(useDebugSave){
            currentSave = debugSave();
        }
    }
    
    public static SaveManager getInstance(){
        if(instance == null){
            instance = new SaveManager();
        }
        return instance;
    }
    
    public void createNewSave(int saveNumber){
        if(SaveSerializer.load(saveNumber) != null){
            
        }
        GameSave gs = new GameSave();
        gs.setSaveNumber(saveNumber);
        gs.setSaveCreated(LocalDateTime.now());
        gs.setPlayTime(0L);
        currentSave = gs;
        saveCurrentSave();
    }
    
    public void loadSave(int saveNumber){
        if(useDebugSave){
            System.out.println("Debug Loaded!");
            return;
        }
        currentSave = SaveSerializer.load(saveNumber);
    }

    public GameSave getCurrentSave() {
        return currentSave;
    }
    
    public void saveCurrentSave(){
        if(useDebugSave){
            System.out.println("Debug Saved!");
            return;
        }
        SaveSerializer.save(currentSave);
    }
    
}
