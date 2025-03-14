/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Datas.Vector2;
import Physics.Time;
import Scenes.Scene;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GA_IA
 */
public class MapBuilder {
    private static List<TileEntity> loadedTileEntities = new CopyOnWriteArrayList<>();
    
    private static EditorWindow editor = null;
    
    private static boolean useEditor;
    
    private static Scene currentScene;
    
    private static SelectorBox selector;

    public static boolean isUseEditor() {
        return useEditor;
    }

    public static void setUseEditor(boolean useEditor) {
        MapBuilder.useEditor = useEditor;
    }
    
    public static void useMapBuilder(Scene s){
        currentScene = s;
        if(useEditor){
            editor = new EditorWindow();
            selector = new SelectorBox(s);
            currentScene.addEntity(selector);
        }
    } 
    
    private static void buildMap(MapFile map){
        for(int i = 0; i < map.getTiles().length; i++){
            TileFile tile = map.getTiles()[i];
            TileEntity tileEntity = new TileEntity(currentScene);
            tileEntity.setPosition(new Vector2(
                    tile.getColumn() * map.getTileRatio() + map.getOffsetX(),
                    tile.getRow() * map.getTileRatio() + map.getOffsetY()
            ));
            tileEntity.setTag(tile.getTag());
            loadedTileEntities.add(tileEntity);
            currentScene.addEntity(tileEntity);
        }
    }
    
    public static void loadMap(String mapPath){
        if(!loadedTileEntities.isEmpty()){
            System.err.println("There's a map loaded, please unload them first.");
            return;
        }
        try(FileInputStream fin = new FileInputStream(mapPath);
            ObjectInputStream oin = new ObjectInputStream(fin);){
            MapFile map = (MapFile) oin.readObject();
            buildMap(map);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MapBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MapBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createMap(String mapPath, MapFile map){
        try(FileOutputStream fout = new FileOutputStream(mapPath + map.getName()+".map");
            ObjectOutputStream os = new ObjectOutputStream(fout);){
            os.writeObject(map);
        } catch (IOException ex) {
            Logger.getLogger(MapBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void unloadMap(Scene s){
        for(int i = 0; i < loadedTileEntities.size(); i++){
            s.removeEntity(loadedTileEntities.get(i));
        }
        loadedTileEntities.clear();
    }
}
