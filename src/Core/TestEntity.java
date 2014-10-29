package Core;

import java.io.IOException;
import srengine.utils.Serialiser;

public class TestEntity extends PhysicalSREObject {

    private Image image;
    private Animation anim;
    private boolean centered = false;
    private boolean hiden = false;
    private IHitBox hitBox;

    public TestEntity(float x, float y, String path) {
        super(x, y);
        image = Image.load(path);
    }

    public TestEntity(float x, float y, String image, Serialiser s) {
        super(x, y);
        try {
            this.image = Image.read(s.getData(image));
        } catch (IOException ex) {
        }

    }

    public TestEntity(float x, float y, String[] paths) {
        super(x, y);
        anim = new Animation(paths);
    }

    public TestEntity(float x, float y, String[] paths, Serialiser s) {
        super(x, y);
        anim = new Animation(paths, s);
    }

    @Override
    public void draw(GameCore gc, Graphics g) {
        if (image != null && !hiden) {
            image.draw((int) x, (int) y, centered);
        } else if (anim != null && !hiden) {
            anim.draw((int) x, (int) y, centered);
        }
        if (hitBox != null) {
            g.setColor(Color.RED);
            hitBox.draw(gc, g);
        }
    }

    @Override
    public void update(GameCore gc, int delta) {
        super.update(gc, delta);
        if (anim != null) {
            anim.update(delta);
            anim.getCurrentFrame().setAngleDeg(this.getAngleDeg());

        } else if (image != null) {
            this.image.setAngleDeg(this.getAngleDeg());
        }

        if (hitBox != null) {
            if (centered) {
                hitBox.setX(x - anim.getCurrentFrame().getWidth()/2);
                hitBox.setY(y - anim.getCurrentFrame().getHeight()/2);
            } else {
                hitBox.setX(x);
                hitBox.setY(y);
            }
        }
    }

    public void startAnimate(int period) {
        if (anim != null) {
            this.anim.startAnimate(period);
        }
    }

//    public void singleShot() {
//        singleShot(100);
//    }
    public void singleShot(int period) {
        if (anim != null) {
            show();
            anim.singleShot(period);
        }
    }

    public void setCentered(boolean centered) {
        this.centered = centered;
    }

    public void hide() {
        this.hiden = true;
    }

    public void show() {
        this.hiden = false;
    }

    public void setHitBox(IHitBox hitBox) {
        this.hitBox = hitBox;
    }

    public IHitBox getHitBox() {
        return this.hitBox;
    }

}
