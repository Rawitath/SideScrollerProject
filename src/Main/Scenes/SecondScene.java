/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Scenes;

import Main.Entities.Lucy;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class SecondScene extends Scene{

    @Override
    public void load() {
        addEntity(new Lucy(this));
    }
    
}
