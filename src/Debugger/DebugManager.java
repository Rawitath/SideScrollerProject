/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Debugger;

import Entities.Entity;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 *
 * @author GA_IA
 */
public class DebugManager {
    private static boolean debug;
    private static List<Debuggable> debugObjects = new CopyOnWriteArrayList<>();
    
    private static DebugWindow debugWindow = null;

    public static boolean isDebug() {
        return debug;
    }
    public static void addDebugObject(Debuggable d){
        debugObjects.add(d);
        if(debug){
            d.onDebugActivate();
        }
    }
    public static void removeDebugObject(Debuggable d){
        debugObjects.remove(d);
    }
    public static void useDebug(){
        DebugManager.debug = true;
        debugWindow = new DebugWindow();
        for(Debuggable d : debugObjects){
            d.onDebugActivate();
        }
    }
    public static void debugEntity(Entity e){
        debugWindow.debugEntity(e);
    }
}
