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
    private JLabel entityName;
    private JLabel entityTag;
    private JLabel parentName;
    private JLabel globalPosition;
    private JLabel localPosition;
    private JLabel globalScale;
    private JLabel localScale;
    
    private Entity entity;
    
    public DebugWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Debug");
        setResizable(true);
        setSize(250, 400);
        setVisible(true);
        textPanel = new JPanel();
        
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        entityName = new JLabel();
        entityTag = new JLabel();
        parentName = new JLabel();
        globalPosition = new JLabel();
        localPosition = new JLabel();
        globalScale = new JLabel();
        localScale = new JLabel();
        textPanel.add(new JLabel("Name :"));
        textPanel.add(entityName);
        textPanel.add(new JLabel("Tag :"));
        textPanel.add(entityTag);
        textPanel.add(new JLabel("Parent :"));
        textPanel.add(parentName);
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
            entityName.setText("");
            entityTag.setText("");
            parentName.setText("");
            globalPosition.setText("");
            globalScale.setText("");
            localPosition.setText("");
            localScale.setText("");
            return;
        }
        if(entity != null){
            entity.setDebug(false);
            entity = e;
            entity.setDebug(true);
        }
        else{
            entity = e;
        }
        entityName.setText(entity.getName());
        entityTag.setText(entity.getTag());
        if(entity.getParent() != null){
            parentName.setText(entity.getParent().getName());
        }
        else{
            parentName.setText("null");
        }
        globalPosition.setText(
                "X : "+ entity.getPosition().getX()+"\n"+
                "Y : "+ entity.getPosition().getY()+"\n");
        globalScale.setText(
                "X : "+ entity.getScale().getX()+"\n"+
                "Y : "+ entity.getScale().getY()+"\n");
        localPosition.setText(
                "X : "+ entity.getLocalPosition().getX()+"\n"+
                "Y : "+ entity.getLocalPosition().getY()+"\n");
        localScale.setText(
                "X : "+ entity.getLocalScale().getX()+"\n"+
                "Y : "+ entity.getLocalScale().getY());
    }
}
