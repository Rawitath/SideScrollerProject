/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Entities.CollidableEntity;
import Physics.Collider;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class TileEntity extends CollidableEntity{
    private TileFile tileFile;
            
    public TileEntity(Scene s) {
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

    @Override
    public void onColliderEnter(Collider other) {

    }

    @Override
    public void onColliderStay(Collider other) {

    }

    @Override
    public void onColliderExit(Collider other) {

    }

    public TileFile getTileFile() {
        return tileFile;
    }

    public void setTileFile(TileFile tileFile) {
        this.tileFile = tileFile;
    }
    
}
