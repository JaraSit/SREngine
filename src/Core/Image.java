package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.glBindTexture;
import org.lwjgl.opengl.GL14;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.opengl.renderer.SGL;

public class Image {

    private Texture texture;
    private int width;
    private int height;
    private float textureWidth;
    private float textureHeight;
    private float angle = 0f;
    private float centerX;
    private float centerY;

    public Image() {

    }

    public Image(String path) {
        try {
//            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
            texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(path)));
            width = texture.getImageWidth();
            height = texture.getImageHeight();
            textureWidth = texture.getWidth();
            textureHeight = texture.getHeight();
            centerX = width / 2;
            centerY = height / 2;
        } catch (IOException ex) {
            System.out.println("nenalezen soubor");
        }
    }

    static public Image load(String path) {
        Image i = null;
        try {
            i = new Image();
            System.out.println(path);
            i.setTexture(TextureLoader.getTexture("PNG", new FileInputStream(new File(path))));
        } catch (IOException ex) {
            System.out.println("load texture exception");
        }
        return i;
    }
    
    static public Image read(InputStream stream) {
        Image i = null;
        try {
            i = new Image();
            i.setTexture(TextureLoader.getTexture("PNG", stream));
        } catch (IOException ex) {
            System.out.println("load texture exception");
        }
        return i;
    }

    public void setTexture(Texture tex) {
        this.texture = tex;
        this.width = tex.getImageWidth();
        this.height = tex.getImageHeight();
        this.textureWidth = tex.getWidth();
        this.textureHeight = tex.getHeight();
        centerX = width / 2;
        centerY = height / 2;
    }

    public void draw(int x, int y) {
        draw(x, y, false);
    }

    public void draw(int x, int y, boolean centered) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPushMatrix();

        GL11.glTranslatef(x, y, 0);
        if(centered) {
            GL11.glTranslatef(-centerX, -centerY, 0f);
        }

        if (angle != 0) {
            GL11.glTranslatef(centerX, centerY, 0f);
            GL11.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(-centerX, -centerY, 0f);
        }

        Color.WHITE.bind();
        texture.bind();

        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f(0, 0);

            GL11.glTexCoord2f(0, texture.getHeight());
            GL11.glVertex2f(0, height);

            GL11.glTexCoord2f(texture.getWidth(), texture.getHeight());
            GL11.glVertex2f(width, height);

            GL11.glTexCoord2f(texture.getWidth(), 0);
            GL11.glVertex2f(width, 0);
        }
        GL11.glEnd();

        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    public void testDraw(int x, int y) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPushMatrix();

        GL11.glTranslatef(x, y, 0);

        Color.WHITE.bind();
        texture.bind();

        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glTexCoord2f(textureWidth, 0);
            GL11.glVertex2f(0, 0);

            GL11.glTexCoord2f(textureWidth, textureHeight);
            GL11.glVertex2f(0, height);

            GL11.glTexCoord2f(0, textureHeight);
            GL11.glVertex2f(width, height);

            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f(width, 0);
        }
        GL11.glEnd();

        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    public void setAngleDeg(float angle) {
        this.angle = angle;
    }
}
