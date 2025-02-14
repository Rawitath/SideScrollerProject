/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Scenes;

import Datas.Vector2Int;
import Entities.Audios.AudioSource;
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
        
        AudioSource audio = new AudioSource(this);
        audio.setAudioClip("res/sound/whereami.wav");
        audio.setName("Music");
        addEntity(audio);
        AudioSource audio2 = new AudioSource(this);
        audio2.setAudioClip("res/sound/whereami.wav");
        audio2.setName("Music2");
        addEntity(audio2);
        
        addEntity(new HellBackground(this));
        addEntity(new Ground(this));
        addEntity(new Mutsuki(this));
        addEntity(new Lucy(this));
        
        addEntity(new HelloWorld(this));
        addEntity(new HeartContainer(this));
    }
    
}
