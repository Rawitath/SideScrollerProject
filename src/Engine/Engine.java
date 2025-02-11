/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine;

/**
 *
 * @author GA_IA
 */
public abstract class Engine implements Runnable{

    private Thread thread;
    private boolean running;
    
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
    
    public boolean isRunning(){
        return running;
    }
}
