/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Entities;

/**
 *
 * @author GA_IA
 */
public interface Copyable {
    public abstract <T extends Entity> T copyOf();
}
