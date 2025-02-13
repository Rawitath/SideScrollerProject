/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.UI;

import Datas.Vector2;
import Datas.Vector2Int;
import Entities.Camera;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class UIView extends Camera{
    private float pixelRatio;
    private Vector2Int referenceResolution;
    
    public UIView(Scene s) {
        super(s);
        pixelRatio = 1f;
        referenceResolution = new Vector2Int(1920, 1080);
    }

    public Vector2Int getReferenceResolution() {
        return referenceResolution;
    }

    public void setReferenceResolution(Vector2Int referenceResolution) {
        this.referenceResolution = referenceResolution;
    }
    
    public float getPixelRatio() {
        return pixelRatio;
    }

    public void setPixelRatio(float pixelRatio) {
        this.pixelRatio = pixelRatio;
    }
    
    @Override
    public Vector2 getPositionOffset() {
        Vector2 output = new Vector2();
        return output.add(new Vector2(
               (getScreenSize().width / 2),
               (getScreenSize().height / 2)
        ));
    }
    @Override
    public Vector2 getScaleOffset() {
        Vector2 output = new Vector2();
        return output.add(pixelRatio);
    }
    
}
