/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.GameSystem.SavePoint;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author GA_IA
 */
public class SaveSceneMap {
    private static SaveSceneMap instance;
    public Map<Integer, Integer> savemap = new HashMap<>();
    private SaveSceneMap(){
        savemap.put(0, 1);
        savemap.put(1, 1);
        savemap.put(2, 2);
        savemap.put(3, 3);
        savemap.put(4, 3);
        savemap.put(5, 3);
        savemap.put(6, 4);
        savemap.put(7, 4);
        savemap.put(8, 4);
        savemap.put(9, 5);
        savemap.put(10, 5);
        savemap.put(11, 5);
    }
    
    public static SaveSceneMap getInstance(){
        if(instance == null){
            instance = new SaveSceneMap();
        }
        return instance;
    }
}
