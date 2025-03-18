/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Engine.Window.ControllableWindow;
import Engine.Window.WindowEventManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class EditorWindow extends ControllableWindow{
    
    private static final String recentDir = "builder";
    private static final String recentFile = recentDir + "/recent.rf";
    
    private JTextField directoryField = new JTextField(20);
    private JButton selectDirButton = new JButton("Select");
    private JButton saveButton = new JButton("Save");
    private JButton editMapButton = new JButton("Edit Map");
    private JPanel tilePanel = new JPanel();
    private String mapDirectory;
    private String tileDirectory;
    private BufferedImage[] tiles;
    private JLabel[] tileLabels;
    private JMenu fileMenu;
    //private JMenu recentMenu;
    private JMenuItem newMenu;
    private JMenuItem loadMenu;
    private JMenuItem saveMenu;
    //private List<JMenuItem> recentList;
    private int selectedTile = -1;
    
    private EditorController controller;

    public EditorWindow(EditorController controller) {
        super(1);
        this.controller = controller;
        
        setTitle("<No Map Loaded>");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        JMenuBar menubar = new JMenuBar();
        fileMenu = new JMenu("File");
        
        newMenu = new JMenuItem("New");
        newMenu.addActionListener(e -> createNewMap());
        fileMenu.add(newMenu);
        
        loadMenu = new JMenuItem("Load");
        loadMenu.addActionListener(e -> openLoadMap());
        fileMenu.add(loadMenu);
        
//        recentMenu = new JMenu("Recent");
//        fileMenu.add(recentMenu);
//        
//        recentList = loadRecent();
        
        saveMenu = new JMenuItem("Save");
        saveMenu.addActionListener(e -> saveMap());
        fileMenu.addSeparator();
        fileMenu.add(saveMenu);
        
//        for(JMenuItem item : recentList){
//            item.addActionListener(e -> selectDirectoryFromMenu(item.getText()));
//            recentMenu.add(item);
//        }
        menubar.add(fileMenu);

        JPanel topPanel = new JPanel(new FlowLayout());

        topPanel.add(directoryField);
        topPanel.add(selectDirButton);

        tilePanel.setLayout(new GridLayout(4, 4));
        selectDirButton.addActionListener(e ->selectDirectory());
        saveButton.addActionListener(e -> saveSelectedTile());
        
        editMapButton.addActionListener(e -> openEditMap());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(saveButton);
        bottomPanel.add(editMapButton);
        
        setJMenuBar(menubar);
        add(topPanel, BorderLayout.NORTH);
        add(tilePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(400, 450);
        setVisible(true);
        
        setButtonsState(false);
        
        WindowEventManager.getInstance().addControlable(this);
    }

    public void notifySave(){
        if(controller.getMap() != null){
            if(controller.isSaved()){
                setTitle(controller.getMap().getName());
            }
            else{
                setTitle(controller.getMap().getName() + " (Not Saved)");
            }
        }  
    }
    
//    private void saveRecent(List<JMenuItem> recentsList){
//        try(FileOutputStream fout = new FileOutputStream(recentFile);
//                ObjectOutputStream os = new ObjectOutputStream(fout);){
//                os.writeObject(recentsList);
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        recentMenu.removeAll();
//        for(JMenuItem item : recentsList){
//            item.addActionListener(e -> selectDirectoryFromMenu(item.getText()));
//            recentMenu.add(item);
//        }
//    }
//    
//    private List<JMenuItem> loadRecent(){
//        File recentsDir = new File(recentDir);
//        File recents = new File(recentFile);
//        List<JMenuItem> recentsList = null;
//        if(!recentsDir.exists()){
//            recentsDir.mkdir();
//        }
//        if(!recents.exists()){
//            saveRecent(new ArrayList<>());
//        }
//        try(FileInputStream fin = new FileInputStream(recents);
//            ObjectInputStream os = new ObjectInputStream(fin)){
//            recentsList = (List<JMenuItem>) os.readObject();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return recentsList;
//    }
//    
    
    private void setButtonsState(boolean s){
        directoryField.setEditable(s);
        selectDirButton.setEnabled(s);
        editMapButton.setEnabled(s);
    }
    
    private void createNewMap(){
        JFileChooser fileChooser = new JFileChooser(); // find file
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setSelectedFile(new File("").getAbsoluteFile()); //Set default tileDirectory in fileChooser
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) { //selected
            JDialog newPane = new JDialog();
            newPane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            newPane.setTitle("Enter map name");
            JTextField textfield = new JTextField();
            newPane.setLayout(new BorderLayout());
            newPane.add(textfield, BorderLayout.NORTH);
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            JButton createbutton = new JButton("Create");
            
            createbutton.addActionListener(e -> createAndClose(newPane, 
                    fileChooser.getSelectedFile().getAbsolutePath(), 
                    textfield.getText()));
            
            buttonPanel.add(createbutton);
            newPane.add(buttonPanel, BorderLayout.SOUTH);
            newPane.pack();
            newPane.setVisible(true);
        }
    }
    private void createAndClose(JDialog d,String directory, String mapName){
        if(mapName.equals("")){
            JOptionPane.showMessageDialog(this, "Map name cannot be blank.");
            return;
        }
        d.dispose();
        MapFile map = new MapFile(mapName);
        File dir = new File(directory +"/"+map.getName());
        if(!dir.exists()){
            dir.mkdir();
            dir = new File(directory +"/"+map.getName()+"/"+"tile");
            dir.mkdir();
        }
        EditorWindow.this.saveMap(map, dir.getParentFile().getAbsolutePath());
        
        controller.setMap(map);
        setTitle(controller.getMap().getName());
        setButtonsState(true);
        controller.updateScreen();
//        //save to recent
//        for(JMenuItem item : recentList){
//            if(item.getText().equals(tileDirectory + "/" +mapName)){
//                return;
//            }
//        }
//        recentList.add(new JMenuItem(tileDirectory + "/" +mapName));
//        saveRecent(recentList);
    }
    
    private void saveMap(MapFile map, String directory){
        try(FileOutputStream fout = new FileOutputStream(directory +"/"+ map.getName() + ".map");
            ObjectOutputStream os = new ObjectOutputStream(fout);){
            os.writeObject(map);
            mapDirectory = directory;
            controller.setIsSaved(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void saveMap(){
        EditorWindow.this.saveMap(controller.getMap(), mapDirectory);
        notifySave();
    }
    public void updateMap(MapFile map){
        controller.setIsSaved(false);
        controller.updateScreen();
        notifySave();
    }
    public MapFile getCurrentMap(){
        return controller.getMap();
    }
    private void openLoadMap(){
        JFileChooser fileChooser = new JFileChooser(); // find file
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setSelectedFile(new File("").getAbsoluteFile()); //Set default tileDirectory in fileChooser
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) { //selected
            File mapDir = new File(fileChooser.getSelectedFile().getAbsolutePath());
            File[] checkFiles = mapDir.listFiles((d, name) -> name.endsWith(".map"));
            if(checkFiles.length != 1){
                JOptionPane.showMessageDialog(this, "Invalid map folder");
                return;
            }
            controller.setMap(loadMap(checkFiles[0]));
            setTitle(controller.getMap().getName());
            setButtonsState(true);
            controller.updateScreen();
        }
    }
    
    private MapFile loadMap(File mapFile){
            try(FileInputStream fin = new FileInputStream(mapFile);
                ObjectInputStream os = new ObjectInputStream(fin);){
                mapDirectory = mapFile.getParentFile().getAbsolutePath();
                return (MapFile) os.readObject();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
//            //save to recent
//        for(JMenuItem item : recentList){
//            if(item.getText().equals(tileDirectory)){
//                return;
//            }
//        }
//        recentList.add(new JMenuItem(tileDirectory));
//        saveRecent(recentList);
        
        return null;
    }
    
    private void setDirLoad(String dir){
        tileDirectory = dir;
        directoryField.setText(tileDirectory);
        loadTiles();
    }

    private void selectDirectory() { //select
        JFileChooser fileChooser = new JFileChooser(); // find file
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setSelectedFile(new File("").getAbsoluteFile()); //Set default tileDirectory in fileChooser
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) { //selected
            setDirLoad(fileChooser.getSelectedFile().getAbsolutePath());
        }
        
//        //save to recent
//        for(JMenuItem item : recentList){
//            if(item.getText().equals(tileDirectory)){
//                return;
//            }
//        }
//        recentList.add(new JMenuItem(tileDirectory));
//        saveRecent(recentList);
    }

    private void loadTiles() {
        selectedTile = -1;
        File dir = new File(tileDirectory);
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
    
    private void openEditMap(){
        EditMapWindow mapEdit = new EditMapWindow(this);
    }
    
    private void selectDirectoryFromMenu(String dir){
        setDirLoad(dir);
    }

    public BufferedImage[] getTiles() {
        return tiles;
    }

    public int getSelectedTile() {
        return selectedTile;
    }
    @Override
    public void onWindowOpened(WindowEvent e) {

    }

    @Override
    public void onWindowClosing(WindowEvent e) {
        if(!controller.isSaved()){
            if (JOptionPane.showConfirmDialog(this, "Map file is not saved yet. Do you want to save?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                saveMap();
            }
        }
        WindowEventManager.getInstance().removeControlable(this);
        if(e.getSource().equals(this)){
            dispose();
        }
    }

    @Override
    public void onWindowClosed(WindowEvent e) {

    }

    @Override
    public void onWindowIconified(WindowEvent e) {

    }

    @Override
    public void onWindowDeiconified(WindowEvent e) {

    }

    @Override
    public void onWindowActivated(WindowEvent e) {

    }

    @Override
    public void onWindowDeactivated(WindowEvent e) {

    }
}
