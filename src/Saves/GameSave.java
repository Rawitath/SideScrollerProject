/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Saves;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author GA_IA
 */
public class GameSave implements Serializable{
    public LocalDateTime saveCreated;
    public long playTIme;
    public int saveNumber;
    public int currentChapter;
}
