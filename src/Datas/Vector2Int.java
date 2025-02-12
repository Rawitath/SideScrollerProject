/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datas;

/**
 *
 * @author GA_IA
 */
public class Vector2Int {
    private int x, y;
    public Vector2Int(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Vector2Int(){
        this(0, 0);
    }

    public static Vector2Int copy(Vector2Int origin){
        Vector2Int output = new Vector2Int(origin.getX(), origin.getY());
        return output;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Vector2Int negative(){
        return new Vector2Int(-x, -y);
    }
    public Vector2Int add(Vector2Int vec2){
        return new Vector2Int(x + vec2.getX(), y + vec2.getY());
    }
    public Vector2Int add(Vector2 vec2){
        return new Vector2Int(x + Math.round(vec2.getX()), y + Math.round(vec2.getY()));
    }
    public Vector2Int add(int a){
        return new Vector2Int(x + a, y + a);
    }
    public Vector2Int add(int a, int b){
        return new Vector2Int(x + a, y + b);
    }
    public Vector2Int multiply(int m){
        return new Vector2Int(x * m, y * m);
    }
    public Vector2Int multiply(float m){
        return new Vector2Int(Math.round(x * m), Math.round(y * m));
    }
    public Vector2Int multiply(Vector2Int vec2){
        return new Vector2Int(x * vec2.getX(), y * vec2.getY());
    }
    public Vector2Int multiply(Vector2 vec2){
        return new Vector2Int(x * Math.round(vec2.getX()), y * Math.round(vec2.getY()));
    }
    public Vector2Int translate(Vector2Int direction, int speed){
        return this.add(direction.multiply(speed));
    }
    public String getPrint(){
        return "("+this.x + ", "+this.getY()+")";
    }
    
    public static Vector2Int zero(){
        return new Vector2Int(0, 0);
    }
    public static Vector2Int up(){
        return new Vector2Int(0, 1);
    }
    public static Vector2Int down(){
        return new Vector2Int(0, -1);
    }
    public static Vector2Int left(){
        return new Vector2Int(-1, 0);
    }
    public static Vector2Int right(){
        return new Vector2Int(1, 0);
    }
    public static Vector2Int one(){
        return new Vector2Int(1, 1);
    }    
    public static Vector2Int negativeX(){
        return new Vector2Int(-1, 1);
    } 
    public static Vector2Int negativeY(){
        return new Vector2Int(1, -1);
    }
}

