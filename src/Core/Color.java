package Core;

import org.lwjgl.opengl.GL11;

public class Color {

    public final static Color WHITE = new Color(255, 255, 255, 255);
    public final static Color BLACK = new Color(0, 0, 0, 255);
    public final static Color RED = new Color(255, 0, 0, 255);
    public final static Color YELLOW = new Color(255, 255, 0, 255);
    public final static Color BLUE = new Color(0, 0, 255, 255);
    public final static Color GREEN = new Color(0, 255, 0, 255);
    public final static Color GRAY = new Color(125, 125, 125, 255);
    public final static Color ORANGE = new Color(255, 125, 0, 255);
    public static final Color DARK_GRAY = new Color(85, 85, 85, 255);

    float r;
    float g;
    float b;
    float a = 255;

    public Color(int r, int g, int b, int a) {
        this.r = r / 255f;
        this.g = g / 255f;
        this.b = b / 255f;
        this.a = a / 255f;
    }

    public float getRed() {
        return r;
    }

    public float getGreen() {
        return g;
    }

    public float getBlue() {
        return b;
    }
    
    public float getAlpha() {
        return a;
    }

    public void bind() {
        GL11.glColor4f(r, g, b, a);
    }
}
