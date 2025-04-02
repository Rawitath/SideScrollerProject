/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.StartMenu.Entities;

import Main.StartMenu.Entities.Start.MenuButton;
import Entities.UI.UIEntity;
import Entities.UI.UIImage;
import Inputs.KeyControlable;
import Main.GameSystem.SavePoint.SaveSceneMap;
import Main.StartMenu.Entities.Option.OptionController;
import Saves.SaveManager;
import Scenes.Scene;
import Scenes.SceneManager;
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
    private OptionController option;
    private int currentPage;
    
    
    public void begin(){
        if(SaveManager.getInstance().getCurrentSave().getCurrentCheckpoint() == null){
            SceneManager.loadScene(1);
        }
        else{
            SceneManager.loadScene(SaveSceneMap.getInstance().savemap.get(SaveManager.getInstance().getCurrentSave().getCurrentCheckpoint()));
        }
    }
    public MenuController(Scene s) {
        super(s);
        allButtons = new ArrayList<>();
        currentPage = 0;
    }

    public void setOption(OptionController option) {
        this.option = option;
    }
    
    public void goToPage(int num){
        switch(num){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        currentPage = num;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
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
        if(keyCode == KeyEvent.VK_SPACE){
            allButtons.get(selectedButton).onButtonPressed();
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e, int keyCode) {

    }

    @Override
    public void onKeyTyped(KeyEvent e, int keyCode) {

    }
    
}
