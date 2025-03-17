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
    
    private EditorController controller;
    
    public SelectorBox(Scene s, EditorController controller) {    
        super(s);
        this.controller = controller;
        defaultIcon = FileReader.readImage("res/default/framesquare.png");
        activeIcon = FileReader.readImage("res/default/blacksquare.png");
        setSprite(defaultIcon, true);
        setPixelRatio(1);
        setSpriteSize(new Vector2Int(1, 1));
        setAlpha(0.75f);
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
    public void onMousePressed(MouseEvent e) {
        super.onMousePressed(e); 
        setSprite(activeIcon, true);
        controller.placeTile(getScene().getCamera().screenToWorldSpace(new Vector2(e.getPoint().x, e.getPoint().y)));
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        super.onMouseReleased(e); 
        setSprite(defaultIcon, true);
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
        controller.setSelectorPosition(mousePos);
    }
}
