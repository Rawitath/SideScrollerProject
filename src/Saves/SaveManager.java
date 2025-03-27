/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Saves;

/**
 *
 * @author GA_IA
 */
public class SaveManager {
    private static SaveManager instance;
    private GameSave currentSave;
    
    private SaveManager(){
        //temporary
        currentSave = SaveSerializer.load(0);
    }
    
    public static SaveManager getInstance(){
        if(instance == null){
            instance = new SaveManager();
        }
        return instance;
    }

    public GameSave getCurrentSave() {
        return currentSave;
    }
    
    public void saveCurrentSave(){
        SaveSerializer.save(currentSave);
    }
    
}
