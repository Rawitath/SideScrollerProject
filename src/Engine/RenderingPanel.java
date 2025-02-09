/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author GA_IA
 */
public class RenderingPanel extends JPanel implements EngineLoopable{

    public RenderingPanel(JFrame frame, Color bg) {
        setPreferredSize(frame.getSize());
        setBackground(bg);
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(100, 100, 100, 100);
    }

    @Override
    public void update() {
    }

    @Override
    public void fixedUpdate() {
        
    }

    @Override
    public void render() {
        repaint();
    }
}
