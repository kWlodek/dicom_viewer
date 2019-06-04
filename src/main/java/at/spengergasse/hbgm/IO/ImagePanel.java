package at.spengergasse.hbgm.IO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private BufferedImage img;

    private int zoomWidth = 0;
    private int zoomHeight = 0;

    private int zoomX = 0;
    private int zoomY = 0;

    MouseWheelListener listener = e -> {
        int notches = e.getWheelRotation();
        if (zoomX > -140) {
            if (notches < 0) {
                zoomHeight += 20;
                zoomWidth += 20;

                zoomX += 10;
                zoomY += 10;
            } else if (zoomX == 140 || zoomX >= -130){
                zoomHeight -= 20;
                zoomWidth -=  20;

                zoomX -= 10;
                zoomY -= 10;
            }
        }
        System.out.println("X: " + zoomX);
        System.out.println("Y: " + zoomY);
        repaint();
    };

    public ImagePanel(File filepath) throws IOException {
        try {
            img = DCMBuilder.getImage(filepath);
            addMouseWheelListener(listener);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null)
            g.drawImage(img, (super.getWidth() / 4) - zoomX, (super.getHeight() / 4) - zoomY, img.getWidth() + zoomWidth, img.getHeight() + zoomHeight, this);
    }


}
