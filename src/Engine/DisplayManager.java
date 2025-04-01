/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine;


/**
 *
 * @author user
 */
import java.awt.*;
import javax.swing.*;

public class DisplayManager {
    private static DisplayManager instance;
    private EngineFrame engineFrame;
    private boolean isFullScreen = false;
    private Dimension windowedSize;
    private GraphicsDevice device;

    private DisplayManager(EngineFrame frame) {
        this.engineFrame = frame;
        this.device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        this.windowedSize = new Dimension(frame.getWidth(), frame.getHeight());
    }

    public static DisplayManager getInstance(EngineFrame frame) {
        if (instance == null) {
            instance = new DisplayManager(frame);
        }
        return instance;
    }

    public void setFullScreen(boolean isFull) {
        if (isFull == isFullScreen) return; // No change needed

        isFullScreen = isFull;
        if (isFull) {
            windowedSize = engineFrame.getSize(); // Save size
            engineFrame.dispose();
            engineFrame.setUndecorated(true);
            device.setFullScreenWindow(engineFrame);
        } else {
            engineFrame.dispose();
            engineFrame.setUndecorated(false);
            device.setFullScreenWindow(null);
            engineFrame.setSize(windowedSize);
            engineFrame.setLocationRelativeTo(null); // Center window
        }
        engineFrame.setVisible(true);
    }
    
    public boolean isFullScreen() {
    return isFullScreen;
}

    public void setResolution(int width, int height) {
        if (isFullScreen) {
            System.out.println("Cannot change resolution in fullscreen mode.");
            return;
        }
        engineFrame.setSize(width, height);
        engineFrame.setLocationRelativeTo(null);
    }
}