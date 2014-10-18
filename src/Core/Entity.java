package Core;

import org.newdawn.slick.opengl.Texture;

public class Entity extends SREObject {

    Image image;
    Animation anim;

    public Entity(float x, float y, String path) {
        setX(x);
        setY(y);
        image = Image.load(path);
    }

    public Entity(float x, float y, String[] paths) {
        setX(x);
        setY(y);
        anim = new Animation(paths);
    }

    @Override
    public void draw(GameCore gc, Graphics g) {
        if (image != null) {
            image.draw((int) x, (int) y);
        } else if (anim != null) {
            anim.draw((int)x, (int)y);
        }
    }

    @Override
    public void update(GameCore gc, int delta) {
        if(anim != null) {
            anim.update(delta);
        }
    }
    
    public void startAnimate(int period) {
        if(anim != null) {
            this.anim.startAnimate(period);
        }
    }
    
    public void singleShot() {
        singleShot(100);
    }
    
    public void singleShot(int period) {
        if(anim != null) {
            anim.singleShot(period);
        }
    }

}
