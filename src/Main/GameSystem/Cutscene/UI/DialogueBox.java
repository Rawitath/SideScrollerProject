/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.GameSystem.Cutscene.UI;

import Datas.Vector2;
import Entities.UI.UIImage;
import Main.UI.Example.ScriptDialogueTest1;
import Physics.Time;
import Scenes.Scene;
import Utilities.FileReader;

/**
 *
 * @author GA_IA
 */
public class DialogueBox extends UIImage{
    private DialogueText sd1;
    private boolean finished = false;
    
    private float previous;
    private float previous2;
    
    public DialogueBox(Scene s){
        super(s);
        setImage(FileReader.readImage("res/game/hud/DialogBox.png"));
        setScale(new Vector2(getImage().getWidth(), getImage().getHeight()));
        setScreenAnchor(TOP);
        setPosition(new Vector2(0, -200));
        sd1 = new DialogueText(s);
        sd1.setActive(false);
        this.addChild(this.sd1);
    }

    @Override
    public void start() {
    }

    @Override
    public void onSetActive() {
        super.onSetActive();
        previous = Time.time();
    }
    
    @Override
    public void update() {
        if(!sd1.isTextCompleted()){
            if(Time.time() - previous < 0.5f){
                return;
            }
            sd1.setActive(true);
            previous2 = Time.time();
        }
        else{
            sd1.setActive(false);
            if(Time.time() - previous2 < 0.5f){
                return;
            }
            finished = true;
        }
    }

    @Override
    public void fixedUpdate() {
        
    }
    
    public void setScript(String[] script){
        sd1.setDialogue(script);
    }
    
    public boolean isDialogueFinished(){
        return finished;
    }
}
