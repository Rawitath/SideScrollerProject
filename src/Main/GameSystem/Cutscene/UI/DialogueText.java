/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.GameSystem.Cutscene.UI;

import Datas.Vector2;
import Entities.UI.UIEntity;
import Entities.UI.UIText;
import Physics.Time;
import Scenes.Scene;
import java.awt.Font;

/**
 *
 * @author GA_IA
 */
public class DialogueText extends UIText {
    private float start;
    private float duration = 3f;
    private String[] dialogue = {"Hello World!" , "New message."};
    private byte dialoguePick = 0;
    private boolean isTextCompleted;

    public DialogueText(Scene s){
        super(s);
        isTextCompleted = false;
        setFont("res/font/MOON Night DEMO.otf", Font.TRUETYPE_FONT);
        setSize(36);
        setHorizontalAlignment(UIText.CENTER);
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
    
    public String[] getDialogue() {
        return dialogue;
    }

    public void setDialogue(String[] dialogue) {
        this.dialogue = dialogue;
    }
    
    public byte getLastDialogue(){
        return (byte) (dialogue.length - 1);
    }
    @Override
    public void start() {
        this.setScreenAnchor(UIEntity.CENTER);
    }

    public boolean isTextCompleted() {
        return isTextCompleted;
    }

    @Override
    public void onSetActive() {
        start = Time.time();
    }

    @Override
    public void update() {
        if (Time.time() - start < duration){
            this.setText(dialogue[dialoguePick]);

        }
        else{
            this.start = Time.time();
            if (this.dialoguePick < this.getLastDialogue()){
                dialoguePick +=1;
            }
            else{
                isTextCompleted = true;
            }
        }
    }

    @Override
    public void fixedUpdate() {
    }
}
