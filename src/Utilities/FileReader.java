/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import javax.imageio.ImageIO;

/**
 *
 * @author GA_IA
 */
public class FileReader {
    public static File readFile(String path){
        return new File(path);
    }
    
    public static BufferedImage readImage(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(readFile(path));
        } catch (IOException ex) {
            System.err.println("Cannot Read file at path "+path);
        }
        return image;
    }
}
