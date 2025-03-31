/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Example;

/**
 *
 * @author nirawith2548gmail.com
 */
import Entities.UI.UIImage;
import Scenes.Scene;
import Datas.Vector2;

public class BoxDialogueTest1 extends UIImage{
    private ScriptDialogueTest1 sd1;
    
    public BoxDialogueTest1(Scene s){
        super(s);
        sd1 = new ScriptDialogueTest1(s);
        this.addChild(this.sd1);
        this.setPosition(new Vector2(0,100));
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
