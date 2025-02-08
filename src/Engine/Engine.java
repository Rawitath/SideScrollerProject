/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine;

/**
 *
 * @author GA_IA
 */
public class Engine implements Runnable{

    private Thread thread;
    private boolean running;
    private double updateSpeed;
    private double maxFPS;
    
    public Engine(double updateSpeed, double maxFPS){
        this.updateSpeed = updateSpeed;
        this.maxFPS = maxFPS;
    }
    
    public Engine(){
        this(1.0, 60.0);
    }
    
    public void start(){
        if(running){
            return;
        }
        running = true;
        
        thread = new Thread(this);
        thread.start();
    }
    
    public void stop(){
        if(!running){
            return;
        }
        running = false;
        try{
            thread.join();
        }
        catch(InterruptedException e){
            System.err.println("Cannot join thread");
        }
    }
    
    //Update ASAP
    private void update(){
        System.out.println("update");
    }
    //Update according to updateSpeed
    private void fixedUpdate(){
        System.out.println("fixedUpdate");
    }
    //Update according to maxFPS
    private void render(){
        System.out.println("render");
    }
    
    @Override
    public void run() {
        double nUpdate = updateSpeed * 1000000;
        double current = System.nanoTime();
        double nFPS = 1 / maxFPS * 1000000000;
        double fpsCurrent = System.nanoTime();
        
        while(running){
            update();
            while(nUpdate <= System.nanoTime() - current){
                fixedUpdate();
                current = System.nanoTime();
            }
            while(nFPS <= System.nanoTime() - fpsCurrent){
                render();
                fpsCurrent = System.nanoTime();
            }
        }
    }
    
}
