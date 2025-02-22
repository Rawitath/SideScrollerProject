/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.Audios;

import Entities.Entity;
import Scenes.Scene;
import Utilities.FileReader;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author GA_IA
 */
public class AudioSource extends Entity{
    
    private Clip clip;

    public AudioSource(Scene s) {
        super(s);
    }
    
    public void setAudioClip(String path){
        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(FileReader.readFile(path));
            clip = AudioSystem.getClip();
            clip.open(stream);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void play(){
        clip.start();
    }
    public void stop(){
        clip.stop();
    }
    public void loop(boolean isLoop, int count){
        if(isLoop){
            clip.loop(count);
        }
        else{
            clip.loop(0);
        }
    }
    public void loop(boolean isLoop){
        if(isLoop){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        else{
            clip.loop(0);
        }
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
    public void remove() {
        super.remove();
        stop();
        clip.close();
    }
    
    
}
