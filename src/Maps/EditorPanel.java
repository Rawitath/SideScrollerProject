/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Datas.Vector2;
import Entities.UI.UIImage;
import Scenes.Scene;
import java.awt.Color;

/**
 *
 * @author GA_IA
 */
public class EditorPanel extends UIImage{

    private ActionButton save;
    
    public EditorPanel(Scene s) {
        super(s);
        setScreenAnchor(LEFT);
        setScale(new Vector2(s.getUIView().getReferenceResolution().getX() / 4,
                s.getUIView().getReferenceResolution().getY()));
        setPosition(new Vector2(s.getUIView().getReferenceResolution().getX() / 8, 0));
        setAlpha(0.5f);
        
        TextBox header = new TextBox(s);
        header.setColor(Color.BLACK);
        header.setScreenAnchor(TOP);
        header.setText("Map Editor");
        header.setLocalPosition(new Vector2(0, -50));
        addChild(header);
        
        save = new ActionButton(s);
        save.getText().setText("Save");
        addChild(save);
        save.setScreenAnchor(BOTTOM);
        save.setLocalPosition(new Vector2(0, 150));
        save.setScale(new Vector2(280,50));
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
