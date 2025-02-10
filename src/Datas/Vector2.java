/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datas;

/**
 *
 * @author GA_IA
 */
public class Vector2 {
    private int x, y;
    public Vector2(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Vector2(){
        this(0, 0);
    }

    public static Vector2 copy(Vector2 origin){
        Vector2 output = new Vector2(origin.getX(), origin.getY());
        return output;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void add(Vector2 vec2){
        x += vec2.getX();
        y += vec2.getY();
    }
    public void multiply(double m){
        x *= m;
        y *= m;
    }
}
