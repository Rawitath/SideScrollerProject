/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Datas.Vector2;
import Datas.Vector2Int;
import java.io.Serializable;

/**
 *
 * @author GA_IA
 */
public class TileFile implements Serializable{
    private static final long serialVersionUID = 8669086114808669427L;
    
    public static final int VARIABLE = 789514;
    
    private int tile;
    private String variableName;
    private int tileCutSize;
    private boolean hasCollider;
    private Boolean isSolid;
    private Vector2 colliderSize;
    private Vector2 imageSizeMultiplier;
    private Vector2Int anchor;
    private String tag;
    private Integer variableMode;
    
    public TileFile(){
        variableName = "";
        hasCollider = true;
        isSolid = true;
        tag = "<<Default>>";
        
        colliderSize = Vector2.one();
        imageSizeMultiplier = Vector2.one();
        anchor = Vector2Int.zero();
        variableMode = 0;
    }

    public boolean hasCollider() {
        return hasCollider;
    }

    public Boolean isSolid() {
        return isSolid;
    }

    public void setIsSolid(Boolean isSolid) {
        this.isSolid = isSolid;
    }
    
    public void setHasCollider(boolean hasCollider) {
        this.hasCollider = hasCollider;
    }
    
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getTile() {
        return tile;
    }

    public void setTileSheet(int tile) {
        this.tile = tile;
    }
    
    public int getTileCutSize() {
        return tileCutSize;
    }

    public void setTileCutSize(int tileCutSize) {
        this.tileCutSize = tileCutSize;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }
    
    public Vector2 getColliderSize() {
        return colliderSize;
    }

    public void setColliderSize(Vector2 colliderSize) {
        this.colliderSize = colliderSize;
    }

    public Vector2Int getAnchor() {
        return anchor;
    }

    public void setAnchor(Vector2Int anchor) {
        this.anchor = anchor;
    }

    public Vector2 getImageSizeMultiplier() {
        return imageSizeMultiplier;
    }

    public void setImageSizeMultiplier(Vector2 imageSizeMultiplier) {
        this.imageSizeMultiplier = imageSizeMultiplier;
    }

    public Integer getVariableMode() {
        return variableMode;
    }

    public void setVariableMode(Integer variableMode) {
        this.variableMode = variableMode;
    }
    
}
