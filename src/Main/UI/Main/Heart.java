/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main;

import Entities.UI.UIImage;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class Heart extends UIImage{
    
    private boolean isFull;

    public Heart(Scene s) {
        super(s);
        setImage(FileReader.readImage("res/game/hud/huajai.png"));
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

    public boolean isFull() {
        return isFull;
    }

    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
        if(this.isFull){
            setImage(FileReader.readImage("res/game/hud/huajai.png"));
        }
        else{
            setImage(FileReader.readImage("res/game/hud/huajaidam.png"));
        }
    }
    
}
