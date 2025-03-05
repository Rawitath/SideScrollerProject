/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Debugger;

import Entities.Entity;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author GA_IA
 */
public class DebugWindow extends JFrame{
    JLabel pos;
    public DebugWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Debug");
        setResizable(true);
        setSize(250, 400);
        setVisible(true);
        
        
        pos = new JLabel();
        add(pos);
    }
    public void debugEntity(Entity e){
        pos.setText(
                "Global Position\n"+
                "X : "+ e.getPosition().getX()+"\n"+
                "Y : "+ e.getPosition().getY()+"\n"+
                "Local Position\n"+
                "X : "+ e.getLocalPosition().getX()+"\n"+
                "Y : "+ e.getLocalPosition().getY()+"\n"+
                "Global Scale\n"+
                "X : "+ e.getScale().getX()+"\n"+
                "Y : "+ e.getScale().getY()+"\n"+
                "Local Scale\n"+
                "X : "+ e.getLocalScale().getX()+"\n"+
                "Y : "+ e.getLocalScale().getY()
        );
    }
}
