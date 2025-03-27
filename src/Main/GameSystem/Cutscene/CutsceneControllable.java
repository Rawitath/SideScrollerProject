/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Main.GameSystem.Cutscene;

import Datas.Vector2;

/**
 *
 * @author GA_IA
 */
public interface CutsceneControllable {
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void stop();
    public abstract Vector2 currentPosition();
}
