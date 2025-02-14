/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.UI;

import Datas.Vector2;
import Datas.Vector2Int;
import Scenes.Scene;
import Utilities.FileReader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;

/**
 *
 * @author GA_IA
 */
public abstract class UIText extends UIEntity{
    
    private String text;
    private Font font;
    private int style;
    private int size;
    private Color color;
    
    public UIText(Scene s) {
        super(s);
        text = "Lorem Ipsum";
        style = Font.PLAIN;
        size = 14;
        font = new Font("Arial", style, size);
        color = Color.BLACK;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
    public void setFont(String path, int fontType) throws FontFormatException, IOException {
        font = Font.createFont(fontType, FileReader.readFile(path));
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics g, Vector2 posOffset, Vector2 scaleOffset, float zoom) {
        Dimension screen = getScene().getUIView().getScreenSize();
        Vector2Int reference = getScene().getUIView().getReferenceResolution();
        super.draw(g, posOffset, scaleOffset, zoom);
        
        
        
        Vector2 pos = getPosition().add(getScreenAnchor()).multiply(Vector2.negativeY())
                .multiply(new Vector2((float)screen.width / (float)reference.getX(), (float)screen.height / (float)reference.getY()))
                .add(posOffset);
        g.setFont(font.deriveFont(0, size));
        g.setColor(color);
        g.drawString(text, Math.round(pos.getX()),Math.round(pos.getY()) );
    }
    
}
