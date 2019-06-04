package at.spengergasse.hbgm.IO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageBuilder {
    public static void main(String[] args) throws Exception{
        BufferedImage image = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);

        for(int x = 50;x <= 150;x++){
            for(int y = 50;y <= 150;y++) {
                image.setRGB(x,y,0xFFFFFFFF);
            }
        }

        for(int x = 70; x <= 90;x++){
            for(int y = 70;y <= 90;y++) {
                image.setRGB(x,y,0x4286f4);
            }
        }

        for(int x = 110; x <= 130;x++){
            for(int y = 70;y <= 90;y++) {
                image.setRGB(x,y,0x4286f4);
            }
        }

        for(int x = 70; x <= 130;x++){
            for(int y = 110;y <= 130;y++) {
                image.setRGB(x,y,0x4286f4);
            }
        }

        for(int x = 70; x <= 90;x++){
            for(int y = 100;y <= 110;y++) {
                image.setRGB(x,y,0x4286f4);
            }
        }

        for(int x = 110; x <= 130;x++){
            for(int y = 100;y <= 110;y++) {
                image.setRGB(x,y,0x4286f4);
            }
        }



        ImageIO.write(image, "JPEG", new File("image.jpg"));
    }
}
