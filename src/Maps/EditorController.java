/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Datas.Vector2;
import Datas.Vector2Int;
import Scenes.Scene;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * @author GA_IA
 */
public class EditorController{
    
    private EditorWindow editor;
    private SelectorBox selector;
    
    private Scene currentScene;
    
    // Concept of 2D Resizable Array - GA_IA
    private Map<Integer, Map<Integer, TileDisplayEntity>> tileGrid;
    private Map<Integer, Map<Integer, TileDisplayEntity>> selectedTiles;
    private List<BufferedImage> usedImages;
    private Map<BufferedImage, Integer> imageUsage;
    
    private MapFile currentMap = null;
    private boolean isSaved = true;
    
    private int modeList;
    private int currentMode = 0;
    
    public EditorController(Scene s){     
        currentScene = s;
        
        tileGrid = new ConcurrentHashMap<>();
        selectedTiles = new ConcurrentHashMap<>();
        imageUsage = new HashMap<>();
        usedImages = new ArrayList<>();
        
        this.editor = new EditorWindow(this);
        this.selector = new SelectorBox(s, this);
        currentScene.addEntity(selector);
        
        if(currentMap == null){
            selector.setActive(false);
        }
        else{
            selector.setActive(true);
            selector.setScale(Vector2.one().multiply(getMap().getTileRatio()));
        }
        
        modeList = 3;
    }
    
    public void changeMode(){
        currentMode = (currentMode + 1) % modeList;
    }

    public int getCurrentMode() {
        return currentMode;
    }
    
    public String getModeName(){
        String name;
        switch(currentMode){
            case 0:
                name = "Select";
                break;
            case 1:
                name = "Place";
                break;
            case 2:
                name = "Edit";
                break;
            default:
                name = "";
                break;
        }
        return name;
    }
    
    public void updateScreen(){
        if(currentMap == null){
            selector.setActive(false);
        }
        else{
            selector.setActive(true);
            selector.setScale(Vector2.one().multiply(getMap().getTileRatio()));
        }
        for(Integer ck : tileGrid.keySet()){
            for(Integer rk : tileGrid.get(ck).keySet()){
                TileDisplayEntity tile = tileGrid.get(ck).get(rk);
                tile.setPosition(new Vector2(
                    getMap().columnToWorldX(ck),
                    getMap().rowToWorldY(rk)
                ));
                tile.setScale(Vector2.one().multiply(getMap().getTileRatio()));
                tile.setSpriteSize(new Vector2Int(
                        (int)(tile.getSprite().getWidth() * getMap().getImageSizeMultiplier()),
                        (int)(tile.getSprite().getHeight() * getMap().getImageSizeMultiplier())
                ));
            }
        }
        if(selector != null){
            moveSelectorOnTop();
        }
    }
    
    public void selectTile(Vector2 mousePos){
        if(currentMap != null){
            int column = getMap().worldXToColumn(mousePos.getX());
                int row = getMap().worldYToRow(mousePos.getY());
                
                if(!tileGrid.containsKey(column)){
                    return;
                }
                if(!tileGrid.get(column).containsKey(row)){
                    return;
                }
                
                TileDisplayEntity tile = tileGrid.get(column).get(row);
                //Check if already selected
                if(selectedTiles.containsKey(column) && selectedTiles.get(column).containsKey(row)){
                    if(selectedTiles.get(column).get(row) == tile){
                        return;
                    }
                }   

                //Select tile 
                highlightTile(tile);

                if(selectedTiles.containsKey(column)){
                    selectedTiles.get(column).put(row, tile);
                    return;
                }
                selectedTiles.put(column, new ConcurrentHashMap<>());
                selectedTiles.get(column).put(row, tile);
        }
    }
    
    public void deselectTile(Vector2 mousePos){
        if(currentMap != null){
            int column = getMap().worldXToColumn(mousePos.getX());
                int row = getMap().worldYToRow(mousePos.getY());
 
                if(!selectedTiles.containsKey(column)){
                    return;
                }
                if(!selectedTiles.get(column).containsKey(row)){
                    return;
                }
                
                TileDisplayEntity tile = selectedTiles.get(column).get(row);

                //Select tile 
                unHighlightTile(tile);

                selectedTiles.get(column).remove(row);
                if(selectedTiles.get(column).keySet().size() <= 0){
                    selectedTiles.remove(column);
                }
        }
    }

    public void moveTile(Vector2 direction){
        Map<Integer, Map<Integer, TileDisplayEntity>> t = tileGrid;
        Map<Integer, Map<Integer, TileDisplayEntity>> s = selectedTiles;
        
        List<Integer> columnList = new ArrayList<>();
        columnList.addAll(selectedTiles.keySet());
        Collections.sort(columnList);
        if(direction.equals(Vector2.right())){
            Collections.reverse(columnList);
        }
        for(Integer ck : columnList){
            List<Integer> rowList = new ArrayList<>();
            rowList.addAll(selectedTiles.get(ck).keySet());
            Collections.sort(rowList);
            if(direction.equals(Vector2.up())){
                Collections.reverse(rowList);
            }
            for(Integer rk : rowList){
                if(t.get(ck + (int)direction.getX()) != null){
                    if(t.get(ck + (int)direction.getX()).get(rk + (int)direction.getY()) != null){
                        return;
                    }
                }
                
                if(t.containsKey(ck + (int)direction.getX())){
                    t.get(ck + (int)direction.getX()).put(rk + (int)direction.getY(), t.get(ck).get(rk));
                }
                else{
                    t.put(ck + (int)direction.getX(), new ConcurrentHashMap<>());
                    t.get(ck + (int)direction.getX()).put(rk + (int)direction.getY(), t.get(ck).get(rk));
                }
                
                if(s.containsKey(ck + (int)direction.getX())){
                    s.get(ck + (int)direction.getX()).put(rk + (int)direction.getY(), s.get(ck).get(rk));
                }
                else{
                    s.put(ck + (int)direction.getX(), new ConcurrentHashMap<>());
                    s.get(ck + (int)direction.getX()).put(rk + (int)direction.getY(), s.get(ck).get(rk));
                }
                
                t.get(ck).remove(rk);
                if(t.get(ck).keySet().size() <= 0){
                    t.remove(ck);
                }
        
                s.get(ck).remove(rk);
                if(s.get(ck).keySet().size() <= 0){
                    s.remove(ck);
                }
            }
        }
        tileGrid = t;
        selectedTiles = s;
        
        isSaved = false;
        editor.notifySave();
        updateScreen();
    }
    
    private void highlightTile(TileDisplayEntity tile){
        tile.setAlpha(0.7f);
    }
    private void unHighlightTile(TileDisplayEntity tile){
        tile.setAlpha(1f);
    }
    
    public boolean placeTile(Vector2 mousePos, boolean overrideTile){
        boolean isReplaced = false;
        if(currentMap != null){
            if(editor.getSelectedTile() != -1){
                int column = getMap().worldXToColumn(mousePos.getX());
                int row = getMap().worldYToRow(mousePos.getY());
                
                int tileID = -1;
                for(int i = 0; i < usedImages.size() + 1; i++){
                    if(i < imageUsage.size()){
                        if(!usedImages.get(i).equals(editor.getTiles()[editor.getSelectedTile()])){
                            continue;
                        }
                        else{
                            tileID = i;
                            break;
                        }
                    }
                    //Not in list
                    usedImages.add(editor.getTiles()[editor.getSelectedTile()]);
                    imageUsage.put(usedImages.get(i), 0);
                    tileID = imageUsage.size() - 1;
                    break;
                }

                //Check if already placed
                if(tileGrid.containsKey(column) && tileGrid.get(column).containsKey(row)){
                    if(tileGrid.get(column).get(row).getTileFile().getTile() == tileID){
                        return isReplaced;
                    }
                    if(overrideTile){
                        removeTile(mousePos);
                        isReplaced = true;
                    }
                    else{
                        return isReplaced;
                    }
                }   
                
                imageUsage.put(usedImages.get(tileID), imageUsage.get(usedImages.get(tileID)) + 1);

                //Placing Tile
                TileFile tileFile = new TileFile();
                tileFile.setTileSheet(tileID);

                TileDisplayEntity tile = new TileDisplayEntity(currentScene);
                tile.setSprite(editor.getTiles()[editor.getSelectedTile()]);
                currentScene.addEntity(tile);

                tile.setPosition(new Vector2(getMap().columnToWorldX(column), getMap().rowToWorldY(row)));

                tile.setScale(Vector2.one().multiply(getMap().getTileRatio()));
                tile.setSpriteSize(new Vector2Int(
                        (int)(tile.getSprite().getWidth() * getMap().getImageSizeMultiplier()),
                        (int)(tile.getSprite().getHeight() * getMap().getImageSizeMultiplier())
                ));
                tile.setTileFile(tileFile);

                if(tileGrid.containsKey(column)){
                    tileGrid.get(column).put(row, tile);
                    return isReplaced;
                }
                tileGrid.put(column, new ConcurrentHashMap<>());
                tileGrid.get(column).put(row, tile);
            }
            isSaved = false;
            editor.notifySave();
        }
        return isReplaced;
    }
    public void removeTile(Vector2 mousePos){ 
        if(currentMap != null){
                int column = getMap().worldXToColumn(mousePos.getX());
                int row = getMap().worldYToRow(mousePos.getY());

                //Check if empty
                if(!tileGrid.containsKey(column)){
                    return;
                }
                if(!tileGrid.get(column).containsKey(row)){
                    return;
                }

                TileDisplayEntity tile = tileGrid.get(column).get(row);
                int imageIndex = tile.getTileFile().getTile();
                BufferedImage image = usedImages.get(imageIndex);

                currentScene.removeEntity(tile);
                imageUsage.put(image
                        , imageUsage.get(image) - 1);
                
                tileGrid.get(column).remove(row);
                if(tileGrid.get(column).keySet().size() <= 0){
                    tileGrid.remove(column);
                }
                if(imageUsage.get(image) <= 0){
                    imageUsage.remove(image);
                    usedImages.remove(imageIndex);
                    for(Integer ck : tileGrid.keySet()){
                        for(Integer rk : tileGrid.get(ck).keySet()){
                            TileDisplayEntity t = tileGrid.get(ck).get(rk);
                            if(t.getTileFile().getTile() >= imageIndex){
                                t.getTileFile().setTileSheet(t.getTileFile().getTile() - 1);
                                t.setSprite(usedImages.get(t.getTileFile().getTile()));
                            }
                        }
                    }
                }
                updateScreen();
        isSaved = false;
        editor.notifySave();
        }
    }
    
    public void readMap(){
        if(currentMap != null){
            //Clear the old one
            if(!tileGrid.isEmpty()){
                for(Integer ck : tileGrid.keySet()){
                    for(Integer rk : tileGrid.get(ck).keySet()){
                        currentScene.removeEntity(tileGrid.get(ck).get(rk));
                    }
                }
            }
            tileGrid = new ConcurrentHashMap<>();
            imageUsage = new HashMap<>();
            usedImages = new ArrayList<>();
            
            if(currentMap.getTiles() != null){
                usedImages = currentMap.getUsedImages();
                for(int i = 0; i < currentMap.getTiles().length; i++){
                    if(currentMap.getTiles()[i] != null){
                        for(int j = 0; j < currentMap.getTiles()[i].length; j++){
                            if(currentMap.getTiles()[i][j] != null){
                                TileDisplayEntity tile = new TileDisplayEntity(currentScene);
                                tile.setTileFile(currentMap.getTiles()[i][j]);
                                BufferedImage tileImage = usedImages.get(tile.getTileFile().getTile());
                                tile.setSprite(tileImage);

                                if(!imageUsage.containsKey(tileImage)){
                                    imageUsage.put(tileImage, 0);
                                }
                                imageUsage.put(tileImage, imageUsage.get(tileImage) + 1);

                                currentScene.addEntity(tile);
                                if(tileGrid.containsKey(i + currentMap.getColumnOffset())){
                                    tileGrid.get(i + currentMap.getColumnOffset()).put(j + currentMap.getRowOffset(), tile);
                                    continue;
                                }
                                tileGrid.put(i + currentMap.getColumnOffset(), new HashMap<>());
                                tileGrid.get(i + currentMap.getColumnOffset()).put(j + currentMap.getRowOffset(), tile);
                            }
                        }
                    }
                }
            }
            updateScreen();
        }
    }
    public void writeMap(){
        if(currentMap != null){
            if(!tileGrid.keySet().isEmpty()){
                List<Integer> columnList = new ArrayList<>();
                columnList.addAll(tileGrid.keySet());
                Collections.sort(columnList);
                TileFile[][] tileFiles = new TileFile[(columnList.getLast() - columnList.getFirst()) + 1][];

                //Making this nullable
                Integer minRow = null;
                Integer maxRow = null;
                for(int i = 0; i < columnList.size(); i++){
                    List<Integer> rowList = new ArrayList<>();
                    rowList.addAll(tileGrid.get(columnList.get(i)).keySet());
                    Collections.sort(rowList);
                    minRow = minRow == null ? rowList.getFirst() : minRow;
                    maxRow = maxRow == null ? rowList.getLast() : maxRow;
                    //Check for size to allocate array
                    minRow = rowList.getFirst() < minRow ? rowList.getFirst() : minRow;
                    maxRow = rowList.getLast() > maxRow ? rowList.getLast() : maxRow;
                }
                
                for(Integer i : columnList){
                    tileFiles[i - columnList.getFirst()] = new TileFile[(maxRow - minRow) + 1];
                    
                    List<Integer> rowList = new ArrayList<>();
                    rowList.addAll(tileGrid.get(i).keySet());
                    Collections.sort(rowList);
                    for(Integer row : rowList){
                        tileFiles[i - columnList.getFirst()][row - minRow] = tileGrid.get(i).get(row).getTileFile();
                    }
                }
                currentMap.setColumnOffset(columnList.getFirst());
                currentMap.setRowOffset(minRow == null ? 0 : minRow);
                currentMap.setTiles(tileFiles);
                currentMap.setUsedImages(usedImages);
                return;
            }
            currentMap.setColumnOffset(0);
            currentMap.setRowOffset(0);
            currentMap.setTiles(null);
            currentMap.setUsedImages(usedImages);
        }
    }
    
    public void setSelectorPosition(Vector2 mousePos){
        if(getMap() != null){
            selector.setScale(Vector2.one().multiply(getMap().getTileRatio()));
            selector.setPosition(new Vector2(
                        getMap().columnToWorldX(getMap().worldXToColumn(mousePos.getX())),
                        getMap().rowToWorldY(getMap().worldYToRow(mousePos.getY()))
                ));
        }
    }
    public void moveSelectorOnTop(){
        //Inefficient jud jud
        currentScene.removeEntity(selector);
        currentScene.addEntity(selector);
    }
    
    public MapFile getMap(){
        return currentMap;
    }

    public void setMap(MapFile currentMap) {  
        this.currentMap = currentMap;
    }
    
    public boolean isSaved() {
        return isSaved;
    }

    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }
}
