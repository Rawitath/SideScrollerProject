/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.GameSystem.SavePoint;

/**
 *
 * @author GA_IA
 */
public class SaveSceneMap {
    private static SaveSceneMap instance;
    private SaveSceneMap(){
        
    }
    
    public static SaveSceneMap getInstance(){
        if(instance == null){
            instance = new SaveSceneMap();
        }
        return instance;
    }
}
