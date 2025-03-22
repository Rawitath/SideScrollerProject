/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Datas.Vector2;
import Datas.Vector2Int;
import Entities.Entity;
import Scenes.Scene;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author GA_IA
 */
public class MapBuilder {
    private static MapContainer container;
    
    private static boolean useEditor;
    
    private static Scene currentScene;
    
    private static EditorController controller;

    public static boolean isUseEditor() {
        return useEditor;
    }

    public static void setUseEditor(boolean useEditor) {
        MapBuilder.useEditor = useEditor;
    }
    
//    public static BufferedImage getCurrentTile(){
//        if(editor.getSelectedTile() != -1){
//            return editor.getTiles()[editor.getSelectedTile()];
//        }
//        return null;
//    }
    
    public static void useMapBuilder(Scene s){
        if(currentScene != null){
            unloadMap();
            currentScene.removeEntity(container);
        }
        currentScene = s;
        
        container = new MapContainer(currentScene);
        currentScene.addEntity(container);
        if(useEditor){
            if(controller == null){
                controller = new EditorController(s);
            }          
        }
    }
    
    private static void buildMap(MapFile map){
        if(map.getTiles() != null){
            for(int i = 0; i < map.getTiles().length; i++){
                if(map.getTiles()[i] != null){
                    for(int j = 0; j < map.getTiles()[i].length; j++){
                        if(map.getTiles()[i][j] != null){
                            TileDisplayEntity tile = new TileDisplayEntity(currentScene);
                            tile.setTileFile(map.getTiles()[i][j]);
                            BufferedImage tileImage = map.getUsedImages().get(tile.getTileFile().getTile());
                            tile.setSprite(tileImage);
                            tile.setPosition(new Vector2(
                                map.columnToWorldX(i + map.getColumnOffset()) + map.getOffsetX(),
                                map.rowToWorldY(j + map.getRowOffset()) + map.getOffsetY()
                            ));
                            tile.setScale(Vector2.one().multiply(map.getTileRatio()));
                            tile.setSpriteSize(new Vector2Int(
                                    (int)(tile.getSprite().getWidth() * map.getImageSizeMultiplier()),
                                    (int)(tile.getSprite().getHeight() * map.getImageSizeMultiplier())
                            ));
                            container.addChild(tile);
                        }
                    }
                }
            }
        }
    }
    
    public static void loadMap(String mapPath){
        if(currentScene == null){
            System.err.println("There is no scene using MapBuilder, please use it first.");
            return;
        }
        if(useEditor){
            System.err.println("You cannot load scene on Editor mode.");
            return;
        }
        if(!container.getChilds().isEmpty()){
            System.err.println("There's a map loaded, please unload it first.");
            return;
        }
        File mapDir = new File(mapPath);
        File[] checkFiles = mapDir.listFiles((d, name) -> name.endsWith(".map"));
        if(checkFiles.length != 1){
            throw new InvalidMapException();
        }
        File mapFile = checkFiles[0];
        try(FileInputStream fin = new FileInputStream(mapFile);
                ObjectInputStream os = new ObjectInputStream(fin);){
            
                String mapDirectory = mapFile.getParentFile().getAbsolutePath();
                MapFile map = (MapFile) os.readObject();
                File tileDir = new File(mapDirectory+"/"+"tile");
                
                if(!tileDir.exists()){
                    throw new InvalidMapException();
                }
                
                File[] imgs = tileDir.listFiles();
                if(map.getUsedImages() == null){
                    map.setUsedImages(new ArrayList<>());
                }
                if(imgs != null){
                    for(File img : imgs){
                        map.getUsedImages().add(ImageIO.read(img));
                    }
                }
                buildMap(map);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void unloadMap(){
        if(currentScene == null){
            System.err.println("There is no scene using MapBuilder, please use it first.");
            return;
        }
        if(useEditor){
            System.err.println("You cannot unload scene on Editor mode.");
            return;
        }
        for (Entity child : container.getChilds()) {
            container.removeChild(container);
        }
    }
}
