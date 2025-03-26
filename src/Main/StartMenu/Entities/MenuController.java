/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities;

import Entities.UI.UIEntity;
import Entities.UI.UIImage;
import Inputs.KeyControlable;
import Scenes.Scene;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GA_IA
 */
public class MenuController extends UIEntity implements KeyControlable{

    private List<MenuButton> allButtons;
    private UIImage buttonFrame;
    private int selectedButton;
    private ButtonGroup group;
    
    public MenuController(Scene s) {
        super(s);
        allButtons = new ArrayList<>();
    }

    public ButtonGroup getGroup() {
        return group;
    }

    public void setGroup(ButtonGroup group) {
        this.group = group;
    }
    
    public void setButtonFrame(UIImage buttonFrame) {
        this.buttonFrame = buttonFrame;
    }

    public List<MenuButton> getAllButtons() {
        return allButtons;
    }
    
    public void addButton(MenuButton b){
        allButtons.add(b);
    }
    
    public void removeButton(MenuButton b){
        allButtons.remove(b);
    }

    @Override
    public void start() {
        
    }

    @Override
    public void update() {
        if(!allButtons.isEmpty()){
            buttonFrame.setPosition(allButtons.get(selectedButton).getPosition());
        }
    }

    public int getSelectedButton() {
        return selectedButton;
    }

    public void setSelectedButton(int selectedButton) {
        this.selectedButton = selectedButton;
    }
    
    @Override
    public void fixedUpdate() {

    }

    @Override
    public void onKeyPressed(KeyEvent e, int keyCode) {
        if(keyCode == KeyEvent.VK_W){
            if(selectedButton == 0){
                selectedButton = allButtons.size() - 1;
            }
            else{
                selectedButton = selectedButton - 1;
            }
        }
        if(keyCode == KeyEvent.VK_S){
            selectedButton = (selectedButton + 1) % allButtons.size();
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e, int keyCode) {

    }

    @Override
    public void onKeyTyped(KeyEvent e, int keyCode) {

    }
    
}
