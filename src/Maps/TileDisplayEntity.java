/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Datas.Vector2;
import Entities.SpriteEntity;
import Physics.Collider;
import Scenes.Scene;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author GA_IA
 */
public class TileDisplayEntity extends SpriteEntity{
    private TileFile tileFile;
    
    private boolean isSelected;
    private boolean isEdited;
            
    public TileDisplayEntity(Scene s) {
        super(s);
        isSelected = false;
        isEdited = false;
    }

    @Override
    public void start() {
        
    }

    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {

    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isIsEdited() {
        return isEdited;
    }

    public void setIsEdited(boolean isEdited) {
        this.isEdited = isEdited;
    }
    
    public TileFile getTileFile() {
        return tileFile;
    }

    public void setTileFile(TileFile tileFile) {
        this.tileFile = tileFile;
    }

    @Override
    public void draw(Graphics g, Vector2 posOffset, Vector2 scaleOffset, float zoom) {
        super.draw(g, posOffset, scaleOffset, zoom);
        if(isSelected){
            Vector2 pos = getPosition().multiply(Vector2.negativeY()).multiply(zoom).add(posOffset);
            Vector2 scale = getScale().multiply(scaleOffset);
            g.setColor(new Color(1.0f, 0.0f, 0.74f, 0.4f));
            g.fillRect(Math.round(pos.getX() - scale.getX() / 2), Math.round(pos.getY() - scale.getY() / 2), Math.round(scale.getX()), Math.round(scale.getY()));
        }
        if(isEdited){
            Vector2 pos = getPosition().multiply(Vector2.negativeY()).multiply(zoom).add(posOffset);
            Vector2 scale = getScale().multiply(scaleOffset);
            g.setColor(new Color(1.0f, 0.0f, 0.0f, 0.4f));
            g.fillRect(Math.round(pos.getX() - scale.getX() / 2), Math.round(pos.getY() - scale.getY() / 2), Math.round(scale.getX()), Math.round(scale.getY()));
        }
    }
}
