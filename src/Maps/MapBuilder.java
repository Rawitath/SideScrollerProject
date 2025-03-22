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
import java.util.Map;
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
    
    private static TileCollider[][] tileColliders;
    
    private static String defaultTileTag = "Ground";

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

    public static String getDefaultTileTag() {
        return defaultTileTag;
    }

    public static void setDefaultTileTag(String defaultTileTag) {
        MapBuilder.defaultTileTag = defaultTileTag;
    }
    
    private static TileCollider createTileCollider(){
        TileCollider t = new TileCollider(currentScene);
        t.setTag(defaultTileTag);
        return t;
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
    
    private static void buildMap(MapFile map){
        if(map.getTiles() != null){
            for(int i = 0; i < map.getTiles().length; i++){
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
        tileColliders = new TileCollider[map.getTiles().length][map.getTiles()[0].length];
        placeCollider(map);
    }
    
    private static void placeCollider(MapFile map){
        //corner
        int bl = 1, br = 1, tl = 1, tr = 1;
        for(int i = 0; i <= map.getTiles().length; i+= map.getTiles().length - 1){
            for(int j = 0; j <= map.getTiles()[i].length; j+= map.getTiles()[i].length - 1){
                if(map.getTiles()[i][j] == null){
                    continue;
                }
                placeNew(map, i, j);
                if(i == 0){
                    if(j == 0){
                        bl = 0;
                    }
                    else{
                        tl = 0;
                    }
                }
                else{
                    if(j == 0){
                        br = 0;
                    }
                    else{
                        tr = 0;
                    }
                }
            }
        }
        //bottom edge
        for(int i = 1 - bl; i < map.getTiles().length - 1 + br; i++){
            int j = 0;
            if(map.getTiles()[i][j] == null){
                    continue;
                }
                if(i != 0){
                    if(tileColliders[i - 1][j] != null && tileColliders[i - 1][j].getRow() == 1){ //Check Left
                        pullLeft(map, i, j);
                        continue;
                    }
                }
                if(i != map.getTiles().length - 1){
                    if(tileColliders[i + 1][j] != null && tileColliders[i + 1][j].getRow() == 1){ //Check Right
                        pullRight(map, i, j);
                        continue;
                    }
                }
                if(tileColliders[i][j + 1] != null && tileColliders[i][j + 1].getColumn() == 1){ //Check Up
                    pullUp(map, i, j);
                    continue;
                }
                placeNew(map, i, j);
        }
        //top edge
        for(int i = 1 - tl; i < map.getTiles().length - 1 + tr; i++){
            int j = map.getTiles()[0].length - 1;
            if(map.getTiles()[i][j] == null){
                    continue;
                }
                if(i != 0){
                    if(tileColliders[i - 1][j] != null && tileColliders[i - 1][j].getRow() == 1){ //Check Left
                        pullLeft(map, i, j);
                        continue;
                    }
                }
                if(i != map.getTiles().length - 1){
                    if(tileColliders[i + 1][j] != null && tileColliders[i + 1][j].getRow() == 1){ //Check Right
                        pullRight(map, i, j);
                        continue;
                    }
                }
                if(tileColliders[i][j - 1] != null && tileColliders[i][j - 1].getColumn() == 1){ //Check Down
                    pullDown(map, i, j);
                    continue;
                }
                placeNew(map, i, j);
        }
        //left right edge
        for(int i = 0; i <= map.getTiles().length; i+= map.getTiles().length - 1){
            for(int j = 1 - bl; j < map.getTiles()[i].length - 1 + tl; j++){
                if(map.getTiles()[i][j] == null){
                        continue;
                    }
                    if(i != 0){
                        if(tileColliders[i - 1][j] != null && tileColliders[i - 1][j].getRow() == 1){ //Check Left
                        pullLeft(map, i, j);
                        continue;
                        }
                    }
                    if(i != map.getTiles().length - 1){
                        if(tileColliders[i + 1][j] != null && tileColliders[i + 1][j].getRow() == 1){ //Check Right
                        pullRight(map, i, j);
                        continue;
                        }
                    }
                    if(j != 0){
                        if(tileColliders[i][j - 1] != null && tileColliders[i][j - 1].getColumn() == 1){ //Check Down
                            pullDown(map, i, j);
                            continue;
                        }
                    }
                    if(j != map.getTiles()[i].length - 1){
                        if(tileColliders[i][j + 1] != null && tileColliders[i][j + 1].getColumn() == 1){ //Check Up
                            pullUp(map, i, j);
                            continue;
                        }
                    }
                    placeNew(map, i, j);
            }
        }
        //inside
        for(int i = 1; i < map.getTiles().length - 1; i++){
            for(int j = 1; j < map.getTiles()[i].length - 1; j++){
                if(map.getTiles()[i][j] == null){
                    continue;
                }
                if(map.getTiles()[i - 1][j] != null && //Check Left
                   map.getTiles()[i + 1][j] != null && //Check Right
                   map.getTiles()[i][j - 1] != null && //Check Down
                   map.getTiles()[i][j + 1] != null){ //Check Up
                    continue;
                }
                if(tileColliders[i - 1][j] != null && tileColliders[i - 1][j].getRow() == 1){ //Check Left
                    pullLeft(map, i, j);
                    continue;
                }
                if(tileColliders[i + 1][j] != null && tileColliders[i + 1][j].getRow() == 1){ //Check Right
                    pullRight(map, i, j);
                    continue;
                }
                if(tileColliders[i][j - 1] != null && tileColliders[i][j - 1].getColumn() == 1){ //Check Down
                    pullDown(map, i, j);
                    continue;
                }
                if(tileColliders[i][j + 1] != null && tileColliders[i][j + 1].getColumn() == 1){ //Check Up
                    pullUp(map, i, j);
                    continue;
                }
                placeNew(map, i, j);
           }
        }
    }
    
    private static void placeNew(MapFile map, int i, int j){
        TileCollider tc = createTileCollider();
        tc.setColumn(1);
        tc.setRow(1);
        container.addChild(tc);
        tc.setScale(tc.getScale().multiply(map.getTileRatio()));
        tc.setPosition(new Vector2(map.columnToWorldX(i + map.getColumnOffset()), map.rowToWorldY(j + map.getRowOffset())));
        tileColliders[i][j] = tc;
    }
    
    private static void pullLeft(MapFile map, int i, int j){
        TileCollider tc = tileColliders[i - 1][j];
        tc.setColumn(tc.getColumn()+ 1);
        tc.setScale(tc.getScale().add(
                Vector2.right().multiply(map.getTileRatio())
        ));
        tc.setPosition(tc.getPosition().add(
                Vector2.right().multiply(map.getTileRatio() / 2f)
        ));
        tileColliders[i][j] = tc;
    }
    
    private static void pullRight(MapFile map, int i, int j){
        TileCollider tc = tileColliders[i + 1][j];
        tc.setColumn(tc.getColumn() + 1);
        tc.setScale(tc.getScale().add(
                Vector2.right().multiply(map.getTileRatio())
        ));
        tc.setPosition(tc.getPosition().add(
                Vector2.left().multiply(map.getTileRatio() / 2f)
        ));
        tileColliders[i][j] = tc;
    }
    
    private static void pullUp(MapFile map, int i, int j){
        TileCollider tc = tileColliders[i][j + 1];
        tc.setRow(tc.getRow() + 1);
        tc.setScale(tc.getScale().add(
                Vector2.up().multiply(map.getTileRatio())
        ));
        tc.setPosition(tc.getPosition().add(
                Vector2.down().multiply(map.getTileRatio() / 2f)
        ));
        tileColliders[i][j] = tc;
    }
    
    private static void pullDown(MapFile map, int i, int j){
        TileCollider tc = tileColliders[i][j - 1];
        tc.setRow(tc.getRow() + 1);
        tc.setScale(tc.getScale().add(
                Vector2.up().multiply(map.getTileRatio())
        ));
        tc.setPosition(tc.getPosition().add(
                Vector2.up().multiply(map.getTileRatio() / 2f)
        ));
        tileColliders[i][j] = tc;
    }
}
