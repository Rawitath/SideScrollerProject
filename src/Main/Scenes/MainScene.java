/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Scenes;

import Datas.Vector2Int;
import Main.Entities.*;
import Main.UI.HelloWorld;
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
        
        addEntity(new HelloWorld(this));
        addEntity(new HeartContainer(this));
    }
    
}
