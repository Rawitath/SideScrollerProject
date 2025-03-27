/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Example;

/**
 *
 * @author nirawith2548gmail.com
 */
import Entities.UI.UIText;
import Scenes.Scene;
import Physics.Time;

public class ScriptDialogueTest1 extends UIText{
    private float start = Time.time();
    private float duration = 3f;
    private String[] dialogue = {"Hello World!" , "New message."};
    private byte dialoguePick = 0;

    public ScriptDialogueTest1(Scene s){
        super(s);
        this.setLocalPosition(UIText.CENTER);
    }
    
    public byte getLastDialogue(){
        return (byte) (dialogue.length - 1);
    }
    @Override
    public void start() {
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
        }
        
    }

    @Override
    public void fixedUpdate() {
    }
    
    
}
