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
    private String tilePath;
    private int column;
    private int row;
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

    public String getTileSheet() {
        return tilePath;
    }

    public void setTileSheet(String tilePath) {
        this.tilePath = tilePath;
    }
    
    public int getTileCutSize() {
        return tileCutSize;
    }

    public void setTileCutSize(int tileCutSize) {
        this.tileCutSize = tileCutSize;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    
}
