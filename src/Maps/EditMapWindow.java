/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author GA_IA
 */
public class EditMapWindow extends JDialog implements ActionListener{
    private JTextField ratioField = new JTextField();
    private JTextField imageSizeField = new JTextField();
    private JTextField offsetXField = new JTextField();
    private JTextField offsetYField = new JTextField();
    
    private JButton saveButton = new JButton("Edit");
    
    private EditorWindow editor;
    
    public EditMapWindow(EditorWindow editor){
        
        this.editor = editor;
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Edit " + editor.getCurrentMap().getName());
        
        ratioField.setText(String.valueOf(editor.getCurrentMap().getTileRatio()));
        imageSizeField.setText(String.valueOf(editor.getCurrentMap().getImageSizeMultiplier()));
        offsetXField.setText(String.valueOf(editor.getCurrentMap().getOffsetX()));
        offsetYField.setText(String.valueOf(editor.getCurrentMap().getOffsetY()));
        
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 2));
        gridPanel.add(new JLabel("Space per Tile : "));
        gridPanel.add(ratioField);
        gridPanel.add(new JLabel("Image Size Multiplier : "));
        gridPanel.add(imageSizeField);
        gridPanel.add(new JLabel("Offset X : "));
        gridPanel.add(offsetXField);
        gridPanel.add(new JLabel("Offset Y : "));
        gridPanel.add(offsetYField);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);
        
        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(saveButton)){
            MapFile map = editor.getCurrentMap();
            map.setTileRatio(Float.parseFloat(ratioField.getText()));
            map.setImageSizeMultiplier(Float.parseFloat(imageSizeField.getText()));
            map.setOffsetX(Float.parseFloat(offsetXField.getText()));
            map.setOffsetY(Float.parseFloat(offsetYField.getText()));
            editor.updateMap(map);
            dispose();
        }
    }
}
