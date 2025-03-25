/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Datas.Vector2;
import Datas.Vector2Int;
import Scenes.Scene;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JOptionPane;


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
    private List<TileDisplayEntity> editedTile;
    
    private MapFile currentMap = null;
    private boolean isSaved = true;
    
    private int modeList;
    private int currentMode = 0;
    
    private Vector2Int currentEditTile = null;
    
    private final BufferedImage defaultVariableImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB); 
    
    public EditorController(Scene s){     
        currentScene = s;
        
        tileGrid = new ConcurrentHashMap<>();
        selectedTiles = new ConcurrentHashMap<>();
        imageUsage = new HashMap<>();
        usedImages = new ArrayList<>();
        editedTile = new CopyOnWriteArrayList<>();
        
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
        
        modeList = 4;
    }
    
    public void changeMode(){
        currentMode = (currentMode + 1) % modeList;
        clearSelect();
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
            case 3:
                name = "Variable";
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
                        (int)(
                                tile.getSprite().getWidth()
                                        * getMap().getImageSizeMultiplier()
                                        * tile.getTileFile().getImageSizeMultiplier().getX()),
                        (int)(
                                tile.getSprite().getHeight()
                                        * getMap().getImageSizeMultiplier()
                                        * tile.getTileFile().getImageSizeMultiplier().getY())
                ));
            }
        }
        if(selector != null){
            moveSelectorOnTop();
        }
    }
//////////////////////////////   Select Mode   /////////////////////////////////
  
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
    
    public void deselectTile(int column, int row){
        if(currentMap != null){
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
    
    public void deselectTile(Vector2 mousePos){
        if(currentMap != null){
            int column = getMap().worldXToColumn(mousePos.getX());
                int row = getMap().worldYToRow(mousePos.getY());
 
                deselectTile(column, row);
        }
    }
    
    public void clearSelect(){
        for(Integer column : tileGrid.keySet()){
            for(Integer row : tileGrid.get(column).keySet()){
                deselectTile(column, row);
            }
        }
    }

    public void moveTile(Vector2 direction){
        Map<Integer, Map<Integer, TileDisplayEntity>> t = new ConcurrentHashMap<>(tileGrid);
        Map<Integer, Map<Integer, TileDisplayEntity>> s = new ConcurrentHashMap<>(selectedTiles);
        
        List<Integer> columnList = new ArrayList<>();
        columnList.addAll(s.keySet());
        Collections.sort(columnList);
        if(direction.equals(Vector2.right())){
            Collections.reverse(columnList);
        }
        for(Integer ck : columnList){
            List<Integer> rowList = new ArrayList<>();
            rowList.addAll(s.get(ck).keySet());
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
        tile.setIsSelected(true);
    }
    private void unHighlightTile(TileDisplayEntity tile){
        tile.setIsSelected(false);
    }
    
//////////////////////////////   Place Mode   /////////////////////////////////
    
    public boolean placeTile(Vector2 mousePos, boolean overrideTile){
        boolean isReplaced = false;
        if(currentMap != null){
            if(editor.getSelectedTile() != -1){
                int column = getMap().worldXToColumn(mousePos.getX());
                int row = getMap().worldYToRow(mousePos.getY());
                
                int tileID = -1;
                for(int i = 0; i < usedImages.size() + 1; i++){
                    if(i < usedImages.size()){
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
                    tileID = usedImages.size() - 1;
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
                tile.setOnEdit(true);
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
                    
                    isSaved = false;
                    editor.notifySave();
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
                if(tileGrid.get(column).get(row).getTileFile().getTile() == TileFile.VARIABLE){
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
    
    ////////////////////////////   Edit Mode   ///////////////////////////////////////
    public void editTile(Vector2 mousePos){
        if(currentMap != null && currentEditTile == null){
            int column = getMap().worldXToColumn(mousePos.getX());
            int row = getMap().worldYToRow(mousePos.getY());
            //Check if empty
                if(!tileGrid.containsKey(column)){
                    return;
                }
                if(!tileGrid.get(column).containsKey(row)){
                    return;
                }
                currentEditTile = new Vector2Int(column, row);
                addEdit(tileGrid.get(column).get(row));
                EditTileWindow editTileWindow = new EditTileWindow(this, tileGrid.get(column).get(row).getTileFile());
        }
    }
    
    public void updateTile(TileFile tile){
        if(currentEditTile != null){
            if(tile != null){
                tileGrid.get(currentEditTile.getX()).get(currentEditTile.getY()).setTileFile(tile);
            }
            currentEditTile = null;
            isSaved = false;
            editor.notifySave();
            updateScreen();
        }
    }
    
    private void addEdit(TileDisplayEntity t){
        editedTile.add(t);
        t.setIsEdited(true);
    }
    private void removeEdit(TileDisplayEntity t){
        t.setIsEdited(false);
        editedTile.add(t);
    }
////////////////////////////   Variable Mode   ///////////////////////////////////
    public void placeVariable(Vector2 mousePos){
        if(currentMap != null){
            int column = getMap().worldXToColumn(mousePos.getX());
            int row = getMap().worldYToRow(mousePos.getY());
            
            //Check if already placed
            if(tileGrid.containsKey(column) && tileGrid.get(column).containsKey(row)){
                return;
            }
            //Placing Tile
            String varName = JOptionPane.showInputDialog("Variable Name", "lucy");
            if(varName == null){
                return;
            }
            TileFile tileFile = new TileFile();
            tileFile.setTileSheet(TileFile.VARIABLE);
            tileFile.setHasCollider(false);
            tileFile.setIsSolid(false);
            tileFile.setVariableMode(0);
            tileFile.setVariableName(varName);

            TileDisplayEntity tile = new TileDisplayEntity(currentScene);
            tile.setOnEdit(true);
            tile.setSprite(defaultVariableImage);

            tile.setPosition(new Vector2(getMap().columnToWorldX(column), getMap().rowToWorldY(row)));

            tile.setScale(Vector2.one().multiply(getMap().getTileRatio()));
            tile.setSpriteSize(new Vector2Int(
                    (int)(tile.getSprite().getWidth() * getMap().getImageSizeMultiplier()),
                    (int)(tile.getSprite().getHeight() * getMap().getImageSizeMultiplier())
            ));
            tile.setTileFile(tileFile);
            currentScene.addEntity(tile);

            if(tileGrid.containsKey(column)){
                tileGrid.get(column).put(row, tile);
                isSaved = false;
                editor.notifySave();
                return;
            }
            tileGrid.put(column, new ConcurrentHashMap<>());
            tileGrid.get(column).put(row, tile);
            
            isSaved = false;
            editor.notifySave();
        }
    }
    public void removeVariable(Vector2 mousePos){
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
                if(tileGrid.get(column).get(row).getTileFile().getTile() != TileFile.VARIABLE){
                    return;
                }

                TileDisplayEntity tile = tileGrid.get(column).get(row);

                currentScene.removeEntity(tile);
                tileGrid.get(column).remove(row);
                if(tileGrid.get(column).keySet().size() <= 0){
                    tileGrid.remove(column);
                }
                updateScreen();
        isSaved = false;
        editor.notifySave();
        }
    }
///////////////////////////   Read and Write   /////////////////////////////////
    
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
            editedTile = new CopyOnWriteArrayList<>();
            
            if(currentMap.getTiles() != null){
                usedImages = currentMap.getUsedImages();
                for(int i = 0; i < currentMap.getTiles().length; i++){
                    if(currentMap.getTiles()[i] != null){
                        for(int j = 0; j < currentMap.getTiles()[i].length; j++){
                            if(currentMap.getTiles()[i][j] != null){
                                TileDisplayEntity tile = new TileDisplayEntity(currentScene);
                                tile.setOnEdit(true);
                                tile.setTileFile(currentMap.getTiles()[i][j]);
                                if(tile.getTileFile().getTile() != TileFile.VARIABLE){
                                    BufferedImage tileImage = usedImages.get(tile.getTileFile().getTile());
                                    tile.setSprite(tileImage);
                                    if(!imageUsage.containsKey(tileImage)){
                                        imageUsage.put(tileImage, 0);
                                    }
                                    imageUsage.put(tileImage, imageUsage.get(tileImage) + 1);
                                }
                                else{
                                    tile.setSprite(defaultVariableImage);
                                }

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
                        TileFile t = tileGrid.get(i).get(row).getTileFile();
                        //Update
                        if(t.getColliderSize() == null){
                            t.setColliderSize(Vector2.one());
                        }
                        if(t.getImageSizeMultiplier() == null){
                            t.setImageSizeMultiplier(Vector2.one());
                        }
                        if(t.getTag() == null){
                            t.setTag("<<Default>>");
                        }
                        if(t.getVariableName() == null){
                            t.setVariableName("");
                        }
                        if(t.getAnchor() == null){
                            t.setAnchor(Vector2Int.zero());
                        }
                        if(t.isSolid() == null){
                            t.setIsSolid(true);
                        }
                        if(t.getVariableMode() == null){
                            t.setVariableMode(0);
                        }
                        tileFiles[i - columnList.getFirst()][row - minRow] = t;
                    }
                }
                currentMap.setColumnOffset(columnList.getFirst());
                currentMap.setRowOffset(minRow == null ? 0 : minRow);
                currentMap.setTiles(tileFiles);
                currentMap.setUsedImages(usedImages);
                
                for(TileDisplayEntity t : editedTile){
                    removeEdit(t);
                }
                return;
            }
            currentMap.setColumnOffset(0);
            currentMap.setRowOffset(0);
            currentMap.setTiles(null);
            currentMap.setUsedImages(usedImages);
        }
    }
    
///////////////////////////   ETC   /////////////////////////////////
    
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
