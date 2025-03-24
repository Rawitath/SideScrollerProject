/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Entities.Entity;

/**
 *
 * @author GA_IA
 */
public class MapVariable {
    private String key;
    private Entity entity;

    public MapVariable(String key, Entity entity) {
        this.key = key;
        this.entity = entity;
    }

    public String getKey() {
        return key;
    }

    public Entity getEntity() {
        return entity;
    }
    
}
