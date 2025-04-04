/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author GA_IA
 */
public interface MouseControlable {
    public abstract void onMouseClicked(MouseEvent e);
    public abstract void onMousePressed(MouseEvent e);
    public abstract void onMouseReleased(MouseEvent e);
    public abstract void onMouseEntered(MouseEvent e);
    public abstract void onMouseExited(MouseEvent e);
    public abstract void onMouseDragged(MouseEvent e);
    public abstract void onMouseMoved(MouseEvent e);
    public abstract void onMouseWheelMoved(MouseWheelEvent e);
}
