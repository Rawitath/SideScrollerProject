/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Saves;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GA_IA
 */
public class SaveSerializer {
    public static void save(GameSave save){
        try (FileOutputStream file = new FileOutputStream("saves/"+"save"+save.saveNumber+".lucy");){
            ObjectOutputStream os = new ObjectOutputStream(file);
            os.writeObject(save);
            os.close();
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveSerializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            Logger.getLogger(SaveSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static GameSave load(int saveNumber){
        try (FileInputStream file = new FileInputStream("saves/"+"save"+saveNumber+".lucy");){
            ObjectInputStream os = new ObjectInputStream(file);
            GameSave save = (GameSave) os.readObject();
            os.close();
            file.close();
            return save;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveSerializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            Logger.getLogger(SaveSerializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex){
            Logger.getLogger(SaveSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
