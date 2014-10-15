package Core;

public class Color {

    public final static Color WHITE = new Color(255, 255, 255);
    public final static Color BLACK = new Color(0, 0, 0);
    public final static Color RED = new Color(255, 0, 0);
    public final static Color YELLOW = new Color(255, 255, 0);
    public final static Color BLUE = new Color(0, 0, 255);
    public final static Color GREEN = new Color(0, 255, 0);

    int r;
    int g;
    int b;

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public float getDecimalRed() {
        return (r / 255f);
    }

    public float getDecimalGreen() {
        return (g / 255f);
    }

    public float getDecimalBlue() {
        return (b / 255f);
    }
}
