package Core;

import org.newdawn.slick.opengl.Texture;

public class Entity extends SREObject{
    
    Image image;
    
    public Entity(float x, float y, String path) {
        setX(x);
        setY(y);
        image = new Image(path);
    }

    @Override
    public void draw(GameCore gc, Graphics g) {
        image.draw((int)x, (int)y);
    }

    @Override
    public void update(GameCore gc, int delta) {
        
    }
    
}