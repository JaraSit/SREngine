package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    public Image(String path) {
        try {
//            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
            texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(path)));
            width = texture.getImageWidth();
            height = texture.getImageHeight();
            textureWidth = texture.getWidth();
            textureHeight = texture.getHeight();
        } catch (IOException ex) {
            System.out.println("nenalezen soubor");
        }
    }

    public void draw(int x, int y) {
//        GL11.glMatrixMode(GL11.GL_PROJECTION);
////        GL11.glLoadIdentity();
//        GL11.glColor3d(1.0, 1.0, 1.0);
//        GL11.glClearColor(0.5f, 0.5f, 0.5f, 1);
//        GL11.glEnable(GL11.GL_TEXTURE_2D);
//        GL11.glClearColor(0, 0, 0, 0);
//        GL11.glEnable(GL11.GL_BLEND);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GL11.glPushMatrix();
//        texture.bind();
//        GL11.glTranslatef(100, 100, 0);
//        GL11.glBegin(GL11.GL_QUADS);
//        GL11.glTexCoord2f(0, 0);
//        GL11.glVertex2f(0, 0);
//        GL11.glTexCoord2f(textureWidth, 0);
//        GL11.glVertex2f(0 + 32, 0);
//        GL11.glTexCoord2f(textureWidth, textureHeight);
//        GL11.glVertex2f(0 + 32, 0 + 40);
//        GL11.glTexCoord2f(0, textureHeight);
//        GL11.glVertex2f(0, 0 + 40);
//        GL11.glEnd();
//        GL11.glPopMatrix();
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPushMatrix();

        GL11.glTranslatef(x, y, 0);
        
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
                
//                GL11.glColor4f(1f, 1f, 1f, 1f);
//                Color.WHITE.bind();
    }
}
