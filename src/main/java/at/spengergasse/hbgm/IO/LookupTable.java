package at.spengergasse.hbgm.IO;

public class LookupTable {

    private int width;
    private int center;
    private int alpha;

    public int argb(int x) {

        int farbe;
        int y;

        y = (x-center+(width/2))* 255 / width;

        if (y > 255) { y = 255; }
        if (y < 0) { y = 0; }

        farbe = alpha << 24 | y << 16 | y << 8 | y;

        return farbe;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width <= 0) {
            this.width = 1;
        } else {
            this.width = width;
        }
    }

    public int getCenter() {
        return center;
    }

    public void setCenter(int center) {
        this.center = center;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
}
