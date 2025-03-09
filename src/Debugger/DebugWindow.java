/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Debugger;

import Entities.Entity;
import javax.swing.*;

/**
 *
 * @author GA_IA
 */
public class DebugWindow extends JFrame{
    private JPanel textPanel;
    private JLabel globalPosition;
    private JLabel localPosition;
    private JLabel globalScale;
    private JLabel localScale;
    public DebugWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Debug");
        setResizable(true);
        setSize(250, 400);
        setVisible(true);
        textPanel = new JPanel();
        
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        globalPosition = new JLabel();
        localPosition = new JLabel();
        globalScale = new JLabel();
        localScale = new JLabel();
        textPanel.add(new JLabel("Global Position :"));
        textPanel.add(globalPosition);
        textPanel.add(new JLabel("Global Scale :"));
        textPanel.add(globalScale);
        textPanel.add(new JLabel("Local Position :"));
        textPanel.add(localPosition);
        textPanel.add(new JLabel("Local Scale :"));
        textPanel.add(localScale);
        add(textPanel);
    }
    public void debugEntity(Entity e){
        if(e == null){
            globalPosition.setText("");
            globalScale.setText("");
            localPosition.setText("");
            localScale.setText("");
            return;
        }
        globalPosition.setText(
                "X : "+ e.getPosition().getX()+"\n"+
                "Y : "+ e.getPosition().getY()+"\n");
        globalScale.setText(
                "X : "+ e.getScale().getX()+"\n"+
                "Y : "+ e.getScale().getY()+"\n");
        localPosition.setText(
                "X : "+ e.getLocalPosition().getX()+"\n"+
                "Y : "+ e.getLocalPosition().getY()+"\n");
        localScale.setText(
                "X : "+ e.getLocalScale().getX()+"\n"+
                "Y : "+ e.getLocalScale().getY());
    }
}
