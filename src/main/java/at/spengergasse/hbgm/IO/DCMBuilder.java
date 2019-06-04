package at.spengergasse.hbgm.IO;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DCMBuilder {
   /* public static final File filepath = new File("images/0819A27F.dcm");
    public static void main(String[] args) throws Exception{
        DicomInputStream inputStream = new DicomInputStream(filepath);
        DicomObject dcm = inputStream.readDicomObject();

        short[] pixelData = dcm.getShorts(Tag.PixelData);
        int rows = dcm.getInt(Tag.Rows);
        int columns = dcm.getInt(Tag.Columns);

        short max = max(pixelData);
        short min = min(pixelData);
        int avg = avg(pixelData);

        System.out.println(avg);

        BufferedImage dcmImage1 = new BufferedImage(columns,rows,BufferedImage.TYPE_INT_RGB);
        BufferedImage dcmImage2 = new BufferedImage(columns,rows,BufferedImage.TYPE_INT_RGB);
        BufferedImage dcmImage3 = new BufferedImage(columns,rows,BufferedImage.TYPE_INT_RGB);

        LookupTable lookupTable1 = new LookupTable();
        lookupTable1.setAlpha(0xFF);
        lookupTable1.setCenter(avg);
        lookupTable1.setWidth(avg*2);

        LookupTable lookupTable2 = new LookupTable();
        lookupTable2.setAlpha(0xFF);
        lookupTable2.setCenter(min);
        lookupTable2.setWidth(min*2);

        LookupTable lookupTable3 = new LookupTable();
        lookupTable3.setAlpha(0xFF);
        lookupTable3.setCenter(max);
        lookupTable3.setWidth(max*2);

        for (int c = 0;c < columns;c++) {
            for (int r = 0;r < rows;r++) {
                dcmImage1.setRGB(c,r,lookupTable1.argb(pixelData[r*columns+c]));
                dcmImage2.setRGB(c,r,lookupTable2.argb(pixelData[r*columns+c]));
                dcmImage3.setRGB(c,r,lookupTable3.argb(pixelData[r*columns+c]));
            }
        }

        ImageIO.write(dcmImage1, "JPEG", new File("dcmimage1.jpg"));
        ImageIO.write(dcmImage2, "JPEG", new File("dcmimage2.jpg"));
        ImageIO.write(dcmImage3, "JPEG", new File("dcmimage3.jpg"));
    }*/

    public static BufferedImage getImage(File filepath) throws IOException {
        DicomInputStream inputStream = new DicomInputStream(filepath);
        DicomObject dcm = inputStream.readDicomObject();

        short[] pixelData = dcm.getShorts(Tag.PixelData);
        int rows = dcm.getInt(Tag.Rows);
        int columns = dcm.getInt(Tag.Columns);

        short max = max(pixelData);
        short min = min(pixelData);
        int avg = avg(pixelData);

        System.out.println(avg);

        BufferedImage dcmImage = new BufferedImage(columns,rows,BufferedImage.TYPE_INT_RGB);

        LookupTable lookupTable = new LookupTable();
        lookupTable.setAlpha(0xFF);
        lookupTable.setCenter(avg);
        lookupTable.setWidth(avg*2);

        for (int c = 0;c < columns;c++) {
            for (int r = 0;r < rows;r++) {
                dcmImage.setRGB(c,r,lookupTable.argb(pixelData[r*columns+c]));
            }
        }

        return dcmImage;
    }

    private static short max(short[] pixelData) {
        short max = pixelData[0];

        for(short s : pixelData) {
            if (s > max) {
                max = s;
            }
        }

        return max;
    }

    private static short min(short[] pixelData) {
        short min = pixelData[0];

        for(short s : pixelData) {
            if (s < min) {
                min = s;
            }
        }

        return min;
    }

    private static int avg(short[] pixelData) {
        int avg = 0;

        for(short s : pixelData) {
            avg += s;
        }

        avg = avg/pixelData.length;

        return avg;
    }
}
