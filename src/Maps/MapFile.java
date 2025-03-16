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
public class MapFile implements Serializable{
    private static final long serialVersionUID = 8669086114808669426L;
    private String name;
    private TileFile[] tiles;
    private float tileRatio;
    private float offsetX;
    private float offsetY;

    public MapFile(String name) {
        this.name = name;
        tileRatio = 1;
    }
    
    public float columnToWorldX(int column){
        return column * tileRatio + getOffsetX();
    }
    public float rowToWorldY(int row){
        return row * tileRatio + getOffsetY();
    }
    public int worldXToColumn(float x){
        return (int) ((x - getOffsetX() / tileRatio) + tileRatio / 2);
    }
    public int worldYToRow(float y){
        return (int) ((y - getOffsetY() / tileRatio) + tileRatio / 2);
    }

    public String getName() {
        return name;
    }

    public TileFile[] getTiles() {
        return tiles;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public float getTileRatio() {
        return tileRatio;
    }

    public void setTileRatio(float tileRatio) {
        this.tileRatio = tileRatio;
    }
    
}
