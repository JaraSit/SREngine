package Core;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

public class Graphics {

    public Graphics() {

    }

    public void setColor(Color color) {
//        GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
        GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public void translate(float x, float y) {
        GL11.glTranslatef(x, y, 0f);
    }

    public void rotate(float angle) {
        GL11.glRotatef((float) Math.toDegrees(angle), 0f, 0f, 1f);
    }

    public void resetTransform() {
        GL11.glPopMatrix();
    }

    public void saveTransform() {
        GL11.glPushMatrix();
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

    public void drawRectangle(int x, int y, int width, int height, float angle) {
        saveTransform();
        translate(x, y);
        rotate(angle);
        drawRectangle(0, 0, width, height);
        resetTransform();
    }

    public void drawImage(Image image, int x, int y) {
        image.draw(x, y);
    }

    //????????????????????
    public void drawString(String text, int x, int y) {
        Texture t = null;
        try {
            //        GL11.glEnable(GL11.GL_TEXTURE_2D);
//        GL11.glPushMatrix();
//        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

//        font.drawString(x, y, text);
//        GL11.glPopMatrix();
//        GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            BufferedImage b = new BufferedImage(300, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = b.createGraphics();
            g.drawString(text, 10, 10);
            t = BufferedImageUtil.getTexture("aa", b);
        } catch (IOException ex) {
            Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, null, ex);
        }
        GL11.glDisable(GL11.GL_TEXTURE_2D);
//        GL11.glEnable(GL11.GL_BLEND);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPushMatrix();

        GL11.glTranslatef(x, y, 0);
        Color.YELLOW.bind();
        t.bind();
//        fillRectangle(x, y, x + 100, y + 100);
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f(0, 0);

            GL11.glTexCoord2f(0, t.getHeight());
            GL11.glVertex2f(0, t.getImageHeight());

            GL11.glTexCoord2f(t.getWidth(), t.getHeight());
            GL11.glVertex2f(t.getImageWidth(), t.getImageHeight());

            GL11.glTexCoord2f(t.getWidth(), 0);
            GL11.glVertex2f(t.getImageWidth(), 0);
        }
        GL11.glEnd();
//
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    public void drawCircle(int x, int y, int radius) {
        drawOval(x, y, radius, radius);
    }

    public void drawOval(int x, int y, int radius1, int radius2) {
        float fi = 0;

        int steps = (radius1 + radius2) / 4;
        float delta = (float) ((Math.PI * 2) / steps);

        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex2f(x + radius1, y + 0f);

        while (fi < Math.PI * 2) {
            fi += delta;
            GL11.glVertex2d(x + Math.cos(fi) * radius1, y + Math.sin(fi) * radius2);
        }
        GL11.glEnd();
    }
}
