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

public class ScriptDialogueTest1 extends UIText{
    
    public ScriptDialogueTest1(Scene s){
        super(s);
//        try{
            this.setText("Hello World!!");
            this.setLocalPosition(UIText.CENTER);
//            sleep(3000);
            this.setText("New message.");
//        }
//        catch(InterruptedException ie){
//            ie.getStackTrace();
//        }
        
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
    
    
}
