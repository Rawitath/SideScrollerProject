/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GA_IA
 */
public class MainEngine extends Engine{
    private double updateSpeed;
    private double maxFPS;
    
    private List<EngineLoopable> loopables;
    
    public void addLoopable(EngineLoopable loopable){
        loopables.add(loopable);
    }
    public void removeLoopable(EngineLoopable loopable){
        loopables.remove(loopable);
    }
    
    public MainEngine(double updateSpeed, double maxFPS){
        this.updateSpeed = updateSpeed;
        this.maxFPS = maxFPS;
        
        loopables = new ArrayList<>();
    }
    
    public MainEngine(){
        this(1.0, 60.0);
    }
    
    //Update ASAP
    private void update(){
        for(var l : loopables){
            l.update();
        }
    }
    //Update according to updateSpeed
    private void fixedUpdate(){
        for(var l : loopables){
            l.fixedUpdate();
        }
    }
    //Update according to maxFPS
    private void render(){
        for(var l : loopables){
            l.render();
        }
    }
    
    @Override
    public void run() {
        double nUpdate = updateSpeed * 1000000;
        double current = System.nanoTime();
        double nFPS = 1 / maxFPS * 1000000000;
        double fpsCurrent = System.nanoTime();
        
        while(isRunning()){
            update();
            while(nUpdate <= System.nanoTime() - current){
                fixedUpdate();
                current += nUpdate;
            }
            while(nFPS <= System.nanoTime() - fpsCurrent){
                render();
                fpsCurrent = System.nanoTime();
//                fpsCurrent += nFPS;
            }
        }
    }
}
