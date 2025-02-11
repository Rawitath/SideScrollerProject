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
    private float x, y;
    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
    }
    public Vector2(){
        this(0.0f, 0.0f);
    }

    public static Vector2 copy(Vector2 origin){
        Vector2 output = new Vector2(origin.getX(), origin.getY());
        return output;
    }
    
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public Vector2 negative(){
        return new Vector2(-x, -y);
    }
    public Vector2 add(Vector2 vec2){
        return new Vector2(x + vec2.getX(), y + vec2.getY());
    }
    public Vector2 add(float a){
        return new Vector2(x + a, y + a);
    }
    public Vector2 add(float a, float b){
        return new Vector2(x + a, y + b);
    }
    public Vector2 multiply(float m){
        return new Vector2(x * m, y * m);
    }
    public Vector2 multiply(Vector2 vec2){
        return new Vector2(x * vec2.getX(), y * vec2.getY());
    }
    public Vector2 translate(Vector2 direction, float speed){
        return this.multiply(direction.multiply(speed));
    }
    
    public static Vector2 zero(){
        return new Vector2(0f, 0f);
    }
    public static Vector2 up(){
        return new Vector2(0f, 1f);
    }
    public static Vector2 down(){
        return new Vector2(0f, -1f);
    }
    public static Vector2 left(){
        return new Vector2(-1f, 0f);
    }
    public static Vector2 right(){
        return new Vector2(1f, 0f);
    }
    public static Vector2 one(){
        return new Vector2(1f, 1f);
    }    
}
