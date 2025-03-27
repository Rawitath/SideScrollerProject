/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Datas.Vector2;
import Datas.Vector2Int;
import Entities.Entity;
import Entities.SpriteEntity;
import Scenes.Scene;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
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
    
    private static Map<String, Entity> variableMap = new HashMap<>();
    private static Map<String, Entity> parentMap = new HashMap<>();
    
    private static Queue<MapVariable> variableQueue = new LinkedList<>();
    
    public static void addVariable(String key, Entity value){
        variableMap.put(key, value);
    }
    public static void serVariableParent(String key, Entity value){
        parentMap.put(key, value);
    }

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
                if(imgs != null){
                    BufferedImage[] usedImages = new BufferedImage[imgs.length];
                    for (File img : imgs) {
                        String n = img.getName().replace(".png", "");
                        usedImages[Integer.parseInt(n)] = ImageIO.read(img);
                    }
                    map.setUsedImages(new ArrayList<>());
                    map.getUsedImages().addAll(Arrays.asList(usedImages));
                }
                else{
                    map.setUsedImages(new ArrayList<>());
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
                if(map.getTiles()[i] != null) {
                    for(int j = 0; j < map.getTiles()[i].length; j++){
                        if(map.getTiles()[i][j] != null){
                            if(!map.getTiles()[i][j].getVariableName().equals("")){
                                
                                TileFile tile = map.getTiles()[i][j];
                                int tileType = tile.getTile();
                                String varName = tile.getVariableName();
                                int varMode = map.getTiles()[i][j].getVariableMode();
                                
                                if(!variableMap.containsKey(varName)){
                                    System.err.print("Variable Key : " + varName + " not found! ");
                                    if(varMode == 0 || tileType == TileFile.VARIABLE){
                                        System.err.println("Removing this tile...");
                                        map.getTiles()[i][j] = null;
                                        continue;
                                    }
                                    else if(varMode == 1){
                                        System.err.println("Using the same tile...");
                                    }
                                }
                                else{
                                    if(varMode == 0 || tileType == TileFile.VARIABLE){
                                        Entity e = variableMap.get(varName);
                                        e.setPosition(new Vector2(
                                            map.columnToWorldX(i + map.getColumnOffset()) + map.getOffsetX(),
                                            map.rowToWorldY(j + map.getRowOffset()) + map.getOffsetY()
                                        ));
                                        variableQueue.add(new MapVariable(varName, e));
                                        map.getTiles()[i][j] = null;
                                        continue;
                                    }
                                    else if(varMode == 1){
                                        Entity e = variableMap.get(varName);
                                        if(e instanceof SpriteEntity){
                                            BufferedImage tileImage = map.getUsedImages().get(tile.getTile());
                                            ((SpriteEntity) e).setSprite(tileImage);
                                            e.setPosition(new Vector2(
                                                map.columnToWorldX(i + map.getColumnOffset()) + map.getOffsetX(),
                                                map.rowToWorldY(j + map.getRowOffset()) + map.getOffsetY()
                                            ));
                                            e.setScale(Vector2.one().multiply(map.getTileRatio()));
                                            ((SpriteEntity) e).setSpriteSize(new Vector2Int(
                                                    (int)(((SpriteEntity) e).getSprite().getWidth() * map.getImageSizeMultiplier()
                                                            * tile.getImageSizeMultiplier().getX()),
                                                    (int)(((SpriteEntity) e).getSprite().getHeight() * map.getImageSizeMultiplier()
                                                            * tile.getImageSizeMultiplier().getY())
                                            ));
                                            
                                            variableQueue.add(new MapVariable(varName, e));
                                            map.getTiles()[i][j] = null;
                                            continue;
                                        }
                                        else{
                                            System.err.println("Non SpriteEntity cannot be use in Add mode. Using Replace mode Instead");
                                            e.setPosition(new Vector2(
                                                map.columnToWorldX(i + map.getColumnOffset()) + map.getOffsetX(),
                                                map.rowToWorldY(j + map.getRowOffset()) + map.getOffsetY()
                                            ));
                                            variableQueue.add(new MapVariable(varName, e));
                                            map.getTiles()[i][j] = null;
                                            continue;
                                        }
                                   }
                                }
                            }
                            
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
                        (int)(
                                tile.getSprite().getWidth()
                                        * map.getImageSizeMultiplier()
                                        * tile.getTileFile().getImageSizeMultiplier().getX()),
                        (int)(
                                tile.getSprite().getHeight()
                                        * map.getImageSizeMultiplier()
                                        * tile.getTileFile().getImageSizeMultiplier().getY())
                ));
                            container.addChild(tile);
                        }
                    }
                }
            }
        }
        tileColliders = new TileCollider[map.getTiles().length][map.getTiles()[0].length];
        placeCollider(map);
        placeVariable();
    }
    
    private static void placeCollider(MapFile map){
        //corner
        int bl = 1, br = 1, tl = 1, tr = 1;
        for(int i = 0; i <= map.getTiles().length; i+= map.getTiles().length - 1){
            for(int j = 0; j <= map.getTiles()[i].length; j+= map.getTiles()[i].length - 1){
                if(!checkValid(map.getTiles()[i][j])){
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
            if(map.getTiles()[i] != null){
                if(!checkValid(map.getTiles()[i][j])){
                    continue;
                }
                if(i != 0){
                    if(checkPullLeft(map, i, j)) continue;
                }
                if(i != map.getTiles().length - 1){
                    if(checkPullRight(map, i, j)) continue;
                }
                if(checkPullUp(map, i, j)) continue;
                placeNew(map, i, j);
            }
        }
        //top edge
        for(int i = 1 - tl; i < map.getTiles().length - 1 + tr; i++){
            if(map.getTiles()[i] != null){
                int j = map.getTiles()[i].length - 1;
                if(!checkValid(map.getTiles()[i][j])){
                    continue;
                }
                if(i != 0){
                    if(checkPullLeft(map, i, j)) continue;
                }
                if(i != map.getTiles().length - 1){
                    if(checkPullRight(map, i, j)) continue;
                }
                if(checkPullDown(map, i, j)) continue;
                placeNew(map, i, j);
            }
        }
        //left right edge
        for(int i = 0; i <= map.getTiles().length; i+= map.getTiles().length - 1){
            for(int j = 1 - bl; j < map.getTiles()[i].length - 1 + tl; j++){
                if(!checkValid(map.getTiles()[i][j])){
                    continue;
                }
                    if(i != 0){
                        if(checkPullLeft(map, i, j)) continue;
                    }
                    if(i != map.getTiles().length - 1){
                        if(checkPullRight(map, i, j)) continue;
                    }
                    if(j != 0){
                        if(checkPullDown(map, i, j)) continue;
                    }
                    if(j != map.getTiles()[i].length - 1){
                        if(checkPullUp(map, i, j)) continue;
                    }
                    placeNew(map, i, j);
            }
        }
        //inside
        for(int i = 1; i < map.getTiles().length - 1; i++){
            if(map.getTiles()[i] != null){
            for(int j = 1; j < map.getTiles()[i].length - 1; j++){
                if(!checkValid(map.getTiles()[i][j])){
                    continue;
                }
                    
                    if((map.getTiles()[i - 1] != null && map.getTiles()[i - 1][j] != null 
                            && map.getTiles()[i - 1][j].getColliderSize().equals(map.getTiles()[i][j].getColliderSize())
                            && map.getTiles()[i - 1][j].hasCollider() != false) && //Check Left
                       (map.getTiles()[i + 1] != null && map.getTiles()[i + 1][j] != null
                            && map.getTiles()[i + 1][j].getColliderSize().equals(map.getTiles()[i][j].getColliderSize())
                            && map.getTiles()[i + 1][j].hasCollider() != false) && //Check Right
                       (map.getTiles()[i][j - 1] != null 
                            && map.getTiles()[i][j - 1].getColliderSize().equals(map.getTiles()[i][j].getColliderSize())
                            && map.getTiles()[i][j - 1].hasCollider() != false) && //Check Down
                       (map.getTiles()[i][j + 1] != null
                            && map.getTiles()[i][j + 1].getColliderSize().equals(map.getTiles()[i][j].getColliderSize())
                            && map.getTiles()[i][j + 1].hasCollider() != false)){ //Check Up
                        continue;
                    }
                    if(checkPullLeft(map, i, j)) continue;
                    if(checkPullRight(map, i, j)) continue;
                    if(checkPullUp(map, i, j)) continue;
                    if(checkPullDown(map, i, j)) continue;
                    
                    placeNew(map, i, j);     
           }
            }
        }
    }
    
    private static void placeVariable(){
        while(!variableQueue.isEmpty()){
            MapVariable e = variableQueue.poll();
            if(parentMap.containsKey(e.getKey())){
                parentMap.get(e.getKey()).addChild(e.getEntity());
            }
            else{
                currentScene.addEntity(e.getEntity());
            }
        }
    }
    
    private static boolean checkValid(TileFile tile){
        if(tile == null){
            return false;
        }
        if(!tile.hasCollider()){
            return false;
        }
        return true;
    }
    
    private static boolean checkPullLeft(MapFile map, int i, int j){
        if(tileColliders[i - 1][j] != null && tileColliders[i - 1][j].getRow() == 1){ //Check Left
            if(!(map.getTiles()[i - 1][j].isSolid() ^ map.getTiles()[i][j].isSolid())
                    &&
               map.getTiles()[i - 1][j].getTag().equals(map.getTiles()[i][j].getTag())
                    &&
               map.getTiles()[i - 1][j].getVariableName().equals(map.getTiles()[i][j].getVariableName())
                    &&
               map.getTiles()[i - 1][j].getColliderSize().equals(map.getTiles()[i][j].getColliderSize())){
                
                pullLeft(map, i, j);
                return true;
            }
        }     
        return false;
    }
    private static boolean checkPullRight(MapFile map, int i, int j){
        if(tileColliders[i + 1][j] != null && tileColliders[i + 1][j].getRow() == 1){ //Check Right
            if(!(map.getTiles()[i + 1][j].isSolid() ^ map.getTiles()[i][j].isSolid())
                        &&
                   map.getTiles()[i + 1][j].getTag().equals(map.getTiles()[i][j].getTag())
                        &&
                   map.getTiles()[i + 1][j].getVariableName().equals(map.getTiles()[i][j].getVariableName())
                        &&
                   map.getTiles()[i + 1][j].getColliderSize().equals(map.getTiles()[i][j].getColliderSize())){
            pullRight(map, i, j);
            return true;
            }
        }
        return false;
    }
    private static boolean checkPullUp(MapFile map, int i, int j){
        if(tileColliders[i][j + 1] != null && tileColliders[i][j + 1].getColumn() == 1){ //Check Up
            if(!(map.getTiles()[i][j + 1].isSolid() ^ map.getTiles()[i][j].isSolid())
                                &&
                           map.getTiles()[i][j + 1].getTag().equals(map.getTiles()[i][j].getTag())
                                &&
                           map.getTiles()[i][j + 1].getVariableName().equals(map.getTiles()[i][j].getVariableName())
                                &&
                           map.getTiles()[i][j + 1].getColliderSize().equals(map.getTiles()[i][j].getColliderSize())){
            pullUp(map, i, j);
            return true;
        }
        }
        return false;
    }
    private static boolean checkPullDown(MapFile map, int i, int j){
        if(tileColliders[i][j - 1] != null && tileColliders[i][j - 1].getColumn() == 1){ //Check Down
            if(!(map.getTiles()[i][j - 1].isSolid() ^ map.getTiles()[i][j].isSolid())
                    &&
               map.getTiles()[i][j - 1].getTag().equals(map.getTiles()[i][j].getTag())
                    &&
               map.getTiles()[i][j - 1].getVariableName().equals(map.getTiles()[i][j].getVariableName())
                    &&
               map.getTiles()[i][j - 1].getColliderSize().equals(map.getTiles()[i][j].getColliderSize())){
                
            pullDown(map, i, j);
            return true;
            }
        }
        return false;
    }
    
    private static void placeNew(MapFile map, int i, int j){
        TileCollider tc = new TileCollider(currentScene);
        if(!map.getTiles()[i][j].getTag().equals("<<Default>>")){
            tc.setTag(map.getTiles()[i][j].getTag());
        }
        else{
            tc.setTag(defaultTileTag);
        }
        tc.setColumn(1);
        tc.setRow(1);
        container.addChild(tc);
        tc.setScale(tc.getScale().multiply(map.getTileRatio()).multiply(map.getTiles()[i][j].getColliderSize()));
        tc.setPosition(new Vector2(map.columnToWorldX(i + map.getColumnOffset()), map.rowToWorldY(j + map.getRowOffset()))
                .add(Vector2.one().multiply(map.getTiles()[i][j].getAnchor()).multiply(tc.getScale().multiply(0.5f)))
        );
        if(map.getTiles()[i][j].isSolid()){
            tc.getCollider().setSolid(true);
        }
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
        )
        );
        tc.setPosition(tc.getPosition().add(
                Vector2.down().multiply(map.getTileRatio() / 2f)
        )
        );
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
