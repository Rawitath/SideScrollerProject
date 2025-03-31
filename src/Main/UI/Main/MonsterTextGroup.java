/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.UI.Main;

import Datas.Vector2;
import Entities.UI.UIEntity;
import Scenes.Scene;

/**
 *
 * @author GA_IA
 */
public class MonsterTextGroup extends UIEntity{

    private MonsterText monsterName;
    private MonsterText monsterNum;
    
    public MonsterTextGroup(Scene s) {
        super(s);
        monsterName = new MonsterText(s);
        monsterNum = new MonsterText(s);
        
        addChild(monsterName);
        addChild(monsterNum);
        
        monsterName.setText("Monster");
        monsterNum.setText("0");
    }

    @Override
    public void start() {
        monsterName.setLocalPosition(new Vector2(-50, 0));
        monsterNum.setLocalPosition(new Vector2(50, 0));
    }

    public MonsterText getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(MonsterText monsterName) {
        this.monsterName = monsterName;
    }

    public MonsterText getMonsterNum() {
        return monsterNum;
    }

    public void setMonsterNum(MonsterText monsterNum) {
        this.monsterNum = monsterNum;
    }
    
    @Override
    public void update() {

    }

    @Override
    public void fixedUpdate() {

    }
    
}
