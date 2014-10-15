package Core;

import org.lwjgl.opengl.GL11;

public class Graphics {

    public void setColor(Color color) {
        GL11.glColor3f(color.getDecimalRed(), color.getDecimalGreen(), color.getDecimalBlue());
    }

    public void fillRectangle(int x, int y, int width, int height) {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2i(x, y);
        GL11.glVertex2i(x + width, y);
        GL11.glVertex2i(x + width, y + height);
        GL11.glVertex2i(x, y + height);
        GL11.glEnd();
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex2i(x1, y1);
        GL11.glVertex2i(x2, y2);
        GL11.glEnd();
    }

    public void drawRectangle(int x, int y, int width, int height) {
        drawLine(x, y, x + width, y);
        drawLine(x + width, y, x + width, y + height);
        drawLine(x + width, y + height, x, y + height);
        drawLine(x, y + height, x, y);
    }
    
    public void drawImage(Image image) {
        image.draw();
    }
}
