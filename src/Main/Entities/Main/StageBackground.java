/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Entities.Main;

import Datas.Vector2;
import Datas.Vector2Int;
import Entities.SpriteEntity;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public abstract class StageBackground extends SpriteEntity{
    
    private Vector2 initialScale;
    private float factor = 1f;
    
    public StageBackground(Scene s) {
        super(s);
    }

    @Override
    public void start() {
        initialScale = getScale();
        factor = getScene().getCamera().getZoom();
        setPixelRatio(1f);
    }

    @Override
    public void update() {
        setPosition(getScene().getCamera().getPosition().multiply(Vector2Int.negativeY()));
        setScale(initialScale.multiply(factor / getScene().getCamera().getZoom()));
    }

    @Override
    public void fixedUpdate() {

    }
    
}
