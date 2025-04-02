/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.GameSystem.Cutscene;

import Main.GameSystem.Cutscene.UI.DialogueBox;
import Main.UI.Example.BoxDialogueTest1;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 * @author nirawith2548gmail.com
 */
public abstract class DialogueCutscene extends Cutscene{
    private DialogueBox bd1;
    
    public DialogueCutscene(Scene s) {
        super(s);
        this.bd1 = new DialogueBox(s);
        bd1.setActive(false);
        getScene().addEntity(bd1);
    }
    public void setScript(String[] script){
        bd1.setScript(script);
    }
    public boolean isDialougeEnd(){
        if(bd1.isDialogueFinished()){
            endDialouge();
        }
        return bd1.isDialogueFinished();
    }
    public void startDialouge(){
        bd1.setActive(true);
    }
    public void endDialouge(){
        bd1.setActive(false);
    }
}
