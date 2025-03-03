/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Shifter;

import Datas.Vector2;
import Entities.Entity;
import java.util.List;

/**
 *
 * @author GA_IA
 */
public class Shifter {
    private static Vector2 shiftOffset;
    private static Entity ignoreShift;

    public static Entity getIgnoreShift() {
        return ignoreShift;
    }

    public static void setIgnoreShift(Entity ignoreShift) {
        Shifter.ignoreShift = ignoreShift;
    }
    
    public static void shift(){
        
    }
    public static Vector2 getShiftOffset(){
        return shiftOffset;
    }
    
}
