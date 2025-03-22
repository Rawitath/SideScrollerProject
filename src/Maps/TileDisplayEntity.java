/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Entities.SpriteEntity;
import Physics.Collider;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class TileDisplayEntity extends SpriteEntity{
    private TileFile tileFile;
            
    public TileDisplayEntity(Scene s) {
        super(s);
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

    public TileFile getTileFile() {
        return tileFile;
    }

    public void setTileFile(TileFile tileFile) {
        this.tileFile = tileFile;
    }
    
}
