/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Engine.Window;

import Inputs.KeyControlable;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 *
 * @author GA_IA
 */
public interface WindowControlable{
    public abstract void onWindowOpened(WindowEvent e);
    public abstract void onWindowClosing(WindowEvent e);
    public abstract void onWindowClosed(WindowEvent e);
    public abstract void onWindowIconified(WindowEvent e);
    public abstract void onWindowDeiconified(WindowEvent e);
    public abstract void onWindowActivated(WindowEvent e);
    public abstract void onWindowDeactivated(WindowEvent e);
}
