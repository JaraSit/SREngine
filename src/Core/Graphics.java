package Core;

import java.awt.Font;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;

public class Graphics {

    public void setColor(Color color) {
        GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
    }
    
    public void translate(float x, float y) {
        GL11.glTranslatef(x, y, 0f);
    }
    
    public void resetTransform() {
        GL11.glPopMatrix();
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

    public void drawImage(Image image, int x, int y) {
        image.draw(x, y);
    }

    //????????????????????
    public void drawString(String text, int x, int y) {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPushMatrix();
//        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        TrueTypeFont font = new TrueTypeFont(awtFont, true);
        font.drawString(x, y, text);
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    public void drawCircle(int x, int y, int radius) {
        drawOval(x, y, radius, radius);
    }

    public void drawOval(int x, int y, int radius1, int radius2) {
        float fi = 0;
        
        int steps = (radius1 + radius2) / 4;
        float delta = (float) ((Math.PI / 2) / steps);

        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex2f(x + radius1, y + 0f);
        
        while (fi < Math.PI * 2) {
            fi += delta;
            GL11.glVertex2d(x + Math.cos(fi) * radius1, y + Math.sin(fi) * radius2);
        }
        GL11.glEnd();
    }
}
