/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Physics;

/**
 *
 * @author GA_IA
 */
public interface Collidable {
    public abstract Collider sendCollider();
    public abstract void onColliderEnter(Collider other);
    public abstract void onColliderStay(Collider other);
    public abstract void onColliderExit(Collider other);
}
