/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Physics;

import Engine.MainEngine;

/**
 *
 * @author GA_IA
 */
public class Time {
    private static MainEngine engine = null;

    public static void setEngine(MainEngine engine) {
        Time.engine = engine;
    }
    
    public static float time(){
        return System.nanoTime() / 1000000000f;
    }
    public static float deltaTime(){
        return (float)engine.getDeltaTime();
    }
    public static float fixedDeltaTime(){
        return (float)engine.getFixedDeltaTime();
    }
    public static float getTimeScale(){
        return (float)engine.getUpdateSpeed();
    }
    public static void setTimeScale(float timeScale){
        engine.setUpdateSpeed(timeScale);
    }
}
