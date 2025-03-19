/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author GA_IA
 */
public class MapFile implements Serializable{
    private static final long serialVersionUID = 8669086114808669426L;
    
    private String name;
    private TileFile[][] tiles;
    private float tileRatio;
    private float imageSizeMultiplier;
    private int columnOffset;
    private int rowOffset;
    private float offsetX;
    private float offsetY;
    private transient List<BufferedImage> usedImages;

    public MapFile(String name) {
        this.name = name;
        tileRatio = 1;
        imageSizeMultiplier = 1;
    }
    
    public float columnToWorldX(int column){
        return column * tileRatio - (tileRatio * tileRatio) / 2 + getOffsetX();
    }
    public float rowToWorldY(int row){
        return row * tileRatio - (tileRatio * tileRatio) / 2 + getOffsetY();
    }
    public int worldXToColumn(float x){
        return (int) ((x - getOffsetX()) / tileRatio + tileRatio / 2);
    }
    public int worldYToRow(float y){
        return (int) ((y - getOffsetY()) / tileRatio + tileRatio / 2);
    }

    public int getColumnOffset() {
        return columnOffset;
    }

    public void setColumnOffset(int columnOffset) {
        this.columnOffset = columnOffset;
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public void setRowOffset(int rowOffset) {
        this.rowOffset = rowOffset;
    }
    
    

    public List<BufferedImage> getUsedImages() {
        return usedImages;
    }

    public void setUsedImages(List<BufferedImage> usedImages) {
        this.usedImages = usedImages;
    }

    public String getName() {
        return name;
    }

    public TileFile[][] getTiles() {
        return tiles;
    }

    public void setTiles(TileFile[][] tiles) {
        this.tiles = tiles;
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

    public float getImageSizeMultiplier() {
        return imageSizeMultiplier;
    }

    public void setImageSizeMultiplier(float imageSizeMultiplier) {
        this.imageSizeMultiplier = imageSizeMultiplier;
    }
    
}
