/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Shifter;

import Datas.Vector2Int;
import java.awt.Dimension;

/**
 *
 * @author GA_IA
 */
public class UIView {
    private static Dimension screenSize;
    private static Vector2Int referenceResolution = new Vector2Int(1920, 1080);

    public static Dimension getScreenSize() {
        return screenSize;
    }

    public static void setScreenSize(Dimension screenSize) {
        UIView.screenSize = screenSize;
    }

    public static Vector2Int getReferenceResolution() {
        return referenceResolution;
    }

    public static void setReferenceResolution(Vector2Int referenceResolution) {
        UIView.referenceResolution = referenceResolution;
    }
    
}
