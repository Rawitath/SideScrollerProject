/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine.Window;

import javax.swing.JFrame;

/**
 *
 * @author GA_IA
 */
public abstract class ControllableWindow extends JFrame implements WindowControlable{
    private int priority;
    
    public ControllableWindow(int priority){
        this.priority = priority;
    }
    
    public ControllableWindow(){
        this(0);
    }
    
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    
}
