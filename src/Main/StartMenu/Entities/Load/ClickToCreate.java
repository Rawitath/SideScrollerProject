/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities.Load;

import Entities.UI.UIButton;
import Main.StartMenu.Entities.Fadable;
import Main.StartMenu.Entities.MenuController;
import Main.StartMenu.Entities.TextButton;
import Saves.SaveManager;
import Scenes.Scene;
import java.awt.image.BufferedImage;

/**
 *
 * @author GA_IA
 */
public class ClickToCreate extends UIButton implements Fadable{
private TextButton text;
private SaveGroup saveGroup;
private MenuController currentPage;
    public ClickToCreate(Scene s, SaveGroup saveGroup, MenuController currentPage) {
        super(s);
        this.currentPage = currentPage;
        text = new TextButton(s);
        this.saveGroup = saveGroup;
        addChild(text);
        setReleasedImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        setPressedImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        setHoverImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
    }

    @Override
    public void onButtonClicked() {
        
    }

    @Override
    public void onButtonHovered() {
        
    }

    @Override
    public void onButtonPressed() {
        if(currentPage.getCurrentPage() == 2){
            saveGroup.setSave(SaveManager.getInstance().createNewSave(saveGroup.getSaveID()));
        }   
    }

    public TextButton getText() {
        return text;
    }

    public void setText(TextButton text) {
        this.text = text;
    }

    @Override
    public void onButtonReleased() {

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
    public void fade(float alpha) {
        setAlpha(alpha);
    }
    
}
