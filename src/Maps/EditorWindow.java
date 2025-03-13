/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class EditorWindow extends JFrame {
    private JTextField directoryField = new JTextField(20);
    private JButton selectDirButton = new JButton("Select");
    private JButton saveButton = new JButton("Save");
    private JPanel tilePanel = new JPanel();
    private String directory;
    private BufferedImage[] tiles;
    private JLabel[] tileLabels;
    private int selectedTile = -1;

    public EditorWindow() {
        setTitle("Map Editor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(directoryField);
        topPanel.add(selectDirButton);

        tilePanel.setLayout(new GridLayout(4, 4));
        selectDirButton.addActionListener(e ->selectDirectory());
        saveButton.addActionListener(e -> saveSelectedTile());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(saveButton);

        add(topPanel, BorderLayout.NORTH);
        add(tilePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(400, 450);
        setVisible(true);
    }

    private void selectDirectory() { //select
        JFileChooser fileChooser = new JFileChooser(); // find file
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) { //selected
            directory = fileChooser.getSelectedFile().getAbsolutePath();
            directoryField.setText(directory);
            loadTiles();
        }
    }

    private void loadTiles() {
        File dir = new File(directory);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".png") || name.endsWith(".jpg")); // show only jpg/png
        if (files != null) {
            tiles = new BufferedImage[files.length];
            tileLabels = new JLabel[files.length];
            tilePanel.removeAll();

            for (int i = 0; i < files.length; i++) { // empty file case
                try {
                    tiles[i] = ImageIO.read(files[i]);
                    ImageIcon icon = new ImageIcon(tiles[i].getScaledInstance(64, 64, Image.SCALE_SMOOTH));
                    tileLabels[i] = new JLabel(icon, JLabel.CENTER);

                    int index = i;
                    tileLabels[i].addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            highlightSelectedTile(index);
                        }
                    });
                    tilePanel.add(tileLabels[i]);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            tilePanel.revalidate();
            tilePanel.repaint();
        }
    }

    private void highlightSelectedTile(int index) { // smaller add border
        if (selectedTile == index) { // Deselect
            tileLabels[selectedTile].setIcon(new ImageIcon(tiles[selectedTile].getScaledInstance(64, 64, Image.SCALE_SMOOTH)));
            tileLabels[selectedTile].setBorder(null);
            selectedTile = -1;
        } else {
            if (selectedTile != -1) {
                tileLabels[selectedTile].setIcon(new ImageIcon(tiles[selectedTile].getScaledInstance(64, 64, Image.SCALE_SMOOTH)));
                tileLabels[selectedTile].setBorder(null);
            }
            selectedTile = index;
            tileLabels[index].setIcon(new ImageIcon(tiles[index].getScaledInstance(54, 54, Image.SCALE_SMOOTH)));
            tileLabels[index].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }
    }

    private void saveSelectedTile() { // save-comingsoon
        if (selectedTile != -1) {
            JOptionPane.showMessageDialog(this, "Selected Tile Index: " + selectedTile);
        } else {
            JOptionPane.showMessageDialog(this, "No tile selected!");
        }
    }
}
