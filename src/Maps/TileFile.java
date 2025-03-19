/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import java.io.Serializable;

/**
 *
 * @author GA_IA
 */
public class TileFile implements Serializable{
    private static final long serialVersionUID = 8669086114808669427L;
    
    private int tile;
    private int tileCutSize;
    private boolean hasCollider;
    private String tag;

    public boolean hasCollider() {
        return hasCollider;
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
}
