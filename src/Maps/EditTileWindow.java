/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Datas.Vector2;
import Datas.Vector2Int;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author GA_IA
 */
public class EditTileWindow extends JDialog implements ActionListener, WindowListener{
    private JCheckBox hasColliderCheck = new JCheckBox();
    private JCheckBox isSolidCheck = new JCheckBox();
    private JComboBox colliderAnchor = new JComboBox();
    private JComboBox variableMode = new JComboBox();
    private JTextField colliderSizeXField = new JTextField();
    private JTextField colliderSizeYField = new JTextField();
    private JTextField imageSizeXField = new JTextField();
    private JTextField imageSizeYField = new JTextField();
    private JTextField variableIDField = new JTextField();
    private JTextField tagField = new JTextField();
    
    private JButton saveButton = new JButton("Edit");
    
    private EditorController controller;
    private TileFile tile;
    
    public EditTileWindow(EditorController controller, TileFile tile){
        this.controller = controller;
        this.tile = tile;
        
        addWindowListener(this);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Edit Tile");
        
        String[] anchors = {"Center", "Top", "Bottom", "Left", "Right", "Top Left", "Top Right", "Bottom Left", "Bottom Right"};
        String[] mode = {"Replace","Add"};
        
        for(String a : anchors){
            colliderAnchor.addItem(a);
        }
        for(String m : mode){
            variableMode.addItem(m);
        }
        
        hasColliderCheck.setSelected(tile.hasCollider());
        isSolidCheck.setSelected(tile.isSolid());
        
        colliderSizeXField.setText(String.valueOf(tile.getColliderSize().getX()));
        colliderSizeYField.setText(String.valueOf(tile.getColliderSize().getY()));
        
        imageSizeXField.setText(String.valueOf(tile.getImageSizeMultiplier().getX()));
        imageSizeYField.setText(String.valueOf(tile.getImageSizeMultiplier().getY()));
        
        if(tile.getAnchor().equals(Vector2Int.zero())){
            colliderAnchor.setSelectedIndex(0);
        }
        else if(tile.getAnchor().equals(Vector2Int.up())){
            colliderAnchor.setSelectedIndex(1);
        }
        else if(tile.getAnchor().equals(Vector2Int.down())){
            colliderAnchor.setSelectedIndex(2);
        }
        else if(tile.getAnchor().equals(Vector2Int.left())){
            colliderAnchor.setSelectedIndex(3);
        }
        else if(tile.getAnchor().equals(Vector2Int.right())){
            colliderAnchor.setSelectedIndex(4);
        }
        else if(tile.getAnchor().equals(Vector2Int.negativeX())){
            colliderAnchor.setSelectedIndex(5);
        }
        else if(tile.getAnchor().equals(Vector2Int.one())){
            colliderAnchor.setSelectedIndex(6);
        } 
        else if(tile.getAnchor().equals(Vector2Int.one().negative())){
            colliderAnchor.setSelectedIndex(7);
        }
        else if(tile.getAnchor().equals(Vector2Int.negativeY())){
            colliderAnchor.setSelectedIndex(8);
        }
        
        variableMode.setSelectedIndex(tile.getVariableMode());
        
        variableIDField.setText(tile.getVariableName());
        tagField.setText(tile.getTag());
        
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(10, 2));
        gridPanel.add(new JLabel("Has Collider : "));
        gridPanel.add(hasColliderCheck);
        gridPanel.add(new JLabel("Is Solid : "));
        gridPanel.add(isSolidCheck);
        gridPanel.add(new JLabel("Collider Size Multiplier X : "));
        gridPanel.add(colliderSizeXField);
        gridPanel.add(new JLabel("Collider Size Multiplier Y: "));
        gridPanel.add(colliderSizeYField);
        gridPanel.add(new JLabel("Collider Anchor : "));
        gridPanel.add(colliderAnchor);
        gridPanel.add(new JLabel("Image Size Multiplier X : "));
        gridPanel.add(imageSizeXField);
        gridPanel.add(new JLabel("Image Size Multiplier Y : "));
        gridPanel.add(imageSizeYField);
        gridPanel.add(new JLabel("Variable Name : "));
        gridPanel.add(variableIDField);
        gridPanel.add(new JLabel("Variable Mode : "));
        gridPanel.add(variableMode);
        gridPanel.add(new JLabel("Tag : "));
        gridPanel.add(tagField);
        
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
        tile.setHasCollider(hasColliderCheck.isSelected());
        tile.setIsSolid(isSolidCheck.isSelected());
        tile.setColliderSize(new Vector2(
                Float.parseFloat(colliderSizeXField.getText()),
                Float.parseFloat(colliderSizeYField.getText()))
        );
        tile.setImageSizeMultiplier(new Vector2(
                Float.parseFloat(imageSizeXField.getText()),
                Float.parseFloat(imageSizeYField.getText()))
        );
        tile.setTag(tagField.getText());
        tile.setVariableName(variableIDField.getText());
        tile.setVariableMode(variableMode.getSelectedIndex());
        Vector2Int a;
        
        switch(colliderAnchor.getSelectedIndex()){
            default:
                a = Vector2Int.zero();
                break;
            case 1:
                a = Vector2Int.up();
                break;
            case 2:
                a = Vector2Int.down();
                break;
            case 3:
                a = Vector2Int.left();
                break;
            case 4:
                a = Vector2Int.right();
                break;
            case 5:
                a = Vector2Int.negativeX();
                break;
            case 6:
                a = Vector2Int.one();
                break;
            case 7:
                a = Vector2Int.one().negative();
                break;
            case 8:
                a = Vector2Int.negativeY();
                break;
        }
        tile.setAnchor(a);
        controller.updateTile(tile);
        dispose();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        controller.updateTile(tile);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
