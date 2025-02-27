/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Shifter;

import Datas.Vector2;
import Entities.Entity;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author GA_IA
 */
public class Shifter {
    private static Vector2 shiftOffset;
    private static List<Entity> ignoreShift = new CopyOnWriteArrayList<>();
    
    
    public static void addIgnoreShift(Entity e){
        
    }
    public static void removeIgnoreShift(Entity e){
        
    }
    public static void shift(){
        
    }
    public static Vector2 getShiftOffset(){
        return shiftOffset;
    }
}
