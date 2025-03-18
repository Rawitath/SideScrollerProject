/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine.Window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author GA_IA
 */
public class WindowEventManager implements WindowListener{
    
    private static WindowEventManager instance;
    
    private List<ControllableWindow> winCons;
    
    private WindowEventManager(){
        winCons = new CopyOnWriteArrayList<>();
    }
    
    public static WindowEventManager getInstance(){
        if(instance == null){
            instance = new WindowEventManager();
        }
        return instance;
    }
    
    public void addControlable(ControllableWindow winCon){
        winCon.addWindowListener(this);
        if(winCons.isEmpty()){
            winCons.add(winCon);
            return;
        }
        for(int i = 0; i < winCons.size(); i++){
            if(winCons.get(i).getPriority() > winCon.getPriority()){
                winCons.add(i, winCon);
                return;
            }
        }
        winCons.add(0, winCon);
    }
    
    public void removeControlable(ControllableWindow winCon){
        winCon.removeWindowListener(this);
        winCons.remove(winCon);
    }
    
    public void clearControlable(){
        for(ControllableWindow winCon : winCons){
            removeControlable(winCon);
        }
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
        for(ControllableWindow winCon : winCons){
            winCon.onWindowOpened(e);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        for(ControllableWindow winCon : winCons){
            winCon.onWindowClosing(e);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        for(ControllableWindow winCon : winCons){
            winCon.onWindowClosed(e);
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {
        for(ControllableWindow winCon : winCons){
            winCon.onWindowIconified(e);
        }
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        for(ControllableWindow winCon : winCons){
            winCon.onWindowDeiconified(e);
        }
    }

    @Override
    public void windowActivated(WindowEvent e) {
        for(ControllableWindow winCon : winCons){
            winCon.onWindowActivated(e);
        }
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        for(ControllableWindow winCon : winCons){
            winCon.onWindowDeactivated(e);
        }
    }
    
}
