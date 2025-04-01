/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class EditorScene extends Scene{

    @Override
    public void load() {
        MapBuilder.useMapBuilder(this);
    }
    
}
