/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Datas.Vector2;
import Datas.Vector2Int;
import Entities.SpriteEntity;
import Inputs.MouseControlable;
import Scenes.Scene;
import Utilities.FileReader;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public class SelectorBox extends SpriteEntity{

    private BufferedImage defaultIcon;
    private BufferedImage activeIcon;
    private Vector2 currentTilePosition;
    private Vector2 tileSize;
    
    private EditorBridge controller;
    
    public SelectorBox(Scene s, EditorBridge controller) {    
        super(s);
        this.controller = controller;
        defaultIcon = FileReader.readImage("res/default/framesquare.png");
        activeIcon = FileReader.readImage("res/default/blacksquare.png");
        setSprite(defaultIcon, true);
        setSpriteSize(new Vector2Int(300, 300));
        setAlpha(0.75f);
        
        tileSize = new Vector2(3f, 3f);
        currentTilePosition = Vector2.zero();
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

    public Vector2 getCurrentTilePosition() {
        return currentTilePosition;
    }

    public void setCurrentTilePosition(Vector2 currentTilePosition) {
        this.currentTilePosition = currentTilePosition;
    }

    public Vector2 getTileSize() {
        return tileSize;
    }

    public void setTileSize(Vector2 tileSize) {
        this.tileSize = tileSize;
    }
    
    @Override
    public void onMousePressed(MouseEvent e) {
        setSprite(activeIcon, true);
        super.onMousePressed(e); 
        TileEntity tile = controller.getTile();
        if(tile != null){
            getScene().addEntity(tile);
            tile.setPosition(getPosition());
        }
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        setSprite(defaultIcon, true);
        super.onMouseReleased(e); 
    }
    
    @Override
    public void onMouseDragged(MouseEvent e) {
        super.onMouseDragged(e);
        setSelectorPosition(e);
    }

    @Override
    public void onMouseMoved(MouseEvent e) {
        super.onMouseMoved(e);
        setSelectorPosition(e);
    }
    
    private void setSelectorPosition(MouseEvent e){
        Vector2 mousePos = getScene().getCamera().screenToWorldSpace(new Vector2(e.getPoint().x, e.getPoint().y));
        
        //right
        if(mousePos.getX() > getPosition().getX() + tileSize.getX() / 2){
            setPosition(getPosition().add(new Vector2(tileSize.getX(), 0)));
        }
        //left
        if(mousePos.getX() < getPosition().getX() - tileSize.getX() / 2){
            setPosition(getPosition().add(new Vector2(tileSize.getX(), 0).negative()));
        }
        //down
        if(mousePos.getY() > getPosition().getY() + tileSize.getY() / 2){
            setPosition(getPosition().add(new Vector2(0, tileSize.getY())));
        }
        //down
        if(mousePos.getY() < getPosition().getY() - tileSize.getY() / 2){
            setPosition(getPosition().add(new Vector2(0, tileSize.getY()).negative()));
        }
    }
}
