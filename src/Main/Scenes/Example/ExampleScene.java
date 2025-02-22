/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.Scenes.Example;

import Main.UI.Example.HeartContainer;
import Main.UI.Example.HelloWorld;
import Main.Entities.Example.HellBackground;
import Main.Entities.Example.Ground;
import Main.Entities.Example.Lucy;
import Main.Entities.Example.Mutsuki;
import Entities.Audios.AudioSource;
import Main.UI.Example.MySlider;
import Main.UI.Example.ThisIsExample;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class ExampleScene extends Scene{

    @Override
    public void load() {
        getCamera().setZoom(20f);
        
        AudioSource audio = new AudioSource(this);
        audio.setAudioClip("res/sound/whereami.wav");
        audio.setName("Music");
        addEntity(audio);
        
        addEntity(new HellBackground(this));
        addEntity(new Ground(this));
        addEntity(new Mutsuki(this));
        addEntity(new Lucy(this));
        
        addEntity(new HelloWorld(this));
        addEntity(new HeartContainer(this));
        addEntity(new ThisIsExample(this));
        addEntity(new MySlider(this));
//        addEntity(new MyProgressBar(this));
    }
    
}
