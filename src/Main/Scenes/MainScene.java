/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Scenes;

import Main.Entities.*;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class MainScene extends Scene{

    @Override
    public void load() {
        getCamera().setZoom(20f);
        addEntity(new HellBackground(this));
        addEntity(new Ground(this));
        addEntity(new Mutsuki(this));
        addEntity(new Lucy(this));
    }
    
}
