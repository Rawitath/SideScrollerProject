/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Saves;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GA_IA
 */
public class SaveSerializer {
    private static final String savePath = "saves/";
    
    public static void save(GameSave save){
        if(save.getSaveID() == null){
            save.setSaveID(UUID.randomUUID().toString());
        }
        try (FileOutputStream file = new FileOutputStream(savePath+save.getSaveID()+".lucy");
                ObjectOutputStream os = new ObjectOutputStream(file);){
            os.writeObject(save);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveSerializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            Logger.getLogger(SaveSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static GameSave[] getSaves(){
        File[] saveList = new File(savePath).listFiles((d, name) -> name.endsWith(".lucy"));
        GameSave[] saves = new GameSave[saveList.length];
        for(int i = 0; i < saveList.length; i++){
            try(FileInputStream file = new FileInputStream(savePath+saveList[i].getName());
                ObjectInputStream os = new ObjectInputStream(file);){
                saves[i] = (GameSave) os.readObject();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SaveSerializer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SaveSerializer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SaveSerializer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return saves;
    }
    public static GameSave load(String saveID){
        for(GameSave gs : getSaves()){
            if(gs == null){
                continue;
            }
            if(gs.getSaveID().equals(saveID)){
                return gs;
            }
        }
        System.err.println("Save ID: "+saveID+" not found!");
        return null;
    }
    public static GameSave load(int saveNumber){
        for(GameSave gs : getSaves()){
            if(gs == null){
                continue;
            }
            if(gs.getSaveNumber().equals(saveNumber)){
                return gs;
            }
        }
        System.err.println("Save Number: "+saveNumber+" not found!");
        return null;
    }
}
