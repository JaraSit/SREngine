package Core;

import java.io.IOException;
import srengine.utils.Serialiser;

public class Entity extends SREObject {

//    private Image image;
    private Animation anim;
    private boolean centered = false;
    private boolean hidden = false;
    private IHitBox hitBox;
    private IHitBox clickBox;
    private boolean isDestroyed = false;
    private boolean hitBoxIsVisible = true;
    private int hitBoxAlign = IHitBox.ALIGN_CENTER;
    private int hitBoxValign = IHitBox.VALIGN_CENTER;

    public Entity(float x, float y, String path) {
        super(x, y);
//        image = Image.load(path);
        this.anim = new Animation(new String[]{path});
    }

    public Entity(float x, float y, String image, Serialiser s) {
        super(x, y);
//            this.image = Image.read(s.getData(image));
        this.anim = new Animation(new String[]{image}, s);
    }

    public Entity(float x, float y, String[] paths) {
        super(x, y);
        anim = new Animation(paths);
    }

    public Entity(float x, float y, String[] paths, Serialiser s) {
        super(x, y);
        anim = new Animation(paths, s);
    }

    @Override
    public void draw(GameCore gc, Graphics g) {
//        if (image != null && !hiden) {
//            image.draw((int) x, (int) y, centered);
        if (anim != null && !hidden) {
            anim.draw((int) x, (int) y, centered);
        }
        if (hitBox != null && hitBoxIsVisible) {
            g.setColor(Color.YELLOW);
            hitBox.draw(gc, g);
        }
    }

    @Override
    public void update(GameCore gc, InputManager input, int delta) {
        if (anim != null) {
            anim.update(delta);
            anim.getCurrentFrame().setAngleDeg(this.getAngleDeg());
        }
//        } else if (image != null) {
//            this.image.setAngleDeg(this.getAngleDeg());
//        }

        if (hitBoxAlign == IHitBox.ALIGN_CENTER) {

        } else if (hitBoxAlign == IHitBox.ALIGN_LEFT) {

        } else if (hitBoxAlign == IHitBox.ALIGN_RIGHT) {

        }

        if (hitBoxValign == IHitBox.VALIGN_CENTER) {

        } else if (hitBoxValign == IHitBox.VALIGN_TOP) {

        } else if (hitBoxValign == IHitBox.VALIGN_BOTTOM) {

        }

        if (hitBox != null) {
            if (centered) {
                hitBox.setX(x - anim.getCurrentFrame().getWidth() / 2);
                hitBox.setY(y - anim.getCurrentFrame().getHeight() / 2);
            } else {
                hitBox.setX(x);
                hitBox.setY(y);
            }
            hitBox.setRotate(this.getAngle());
        }
        if (clickBox != null) {
            if (centered) {
                clickBox.setX(x - anim.getCurrentFrame().getWidth() / 2);
                clickBox.setY(y - anim.getCurrentFrame().getHeight() / 2);
            } else {
                clickBox.setX(x);
                clickBox.setY(y);
            }
            clickBox.setRotate(this.getAngle());
        }
    }

    public void startAnimate(int period) {
        if (anim != null) {
            this.anim.startAnimate(period);
        }
    }

    public void startAnimate() {
        if (anim != null) {
            this.anim.startAnimate();
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
        this.hidden = true;
    }

    public void show() {
        this.hidden = false;
    }

    public void setHitBox(IHitBox hitBox) {
        this.hitBox = hitBox;
    }

    public void setClickBox(IHitBox clickBox) {
        this.clickBox = clickBox;
    }

    public IHitBox getHitBox() {
        return this.hitBox;
    }

    public void setPeriod(int period) {
        if (anim != null) {
            this.anim.setPeriod(period);
        }
    }

    public void hideShow() {
        this.hidden = !hidden;
    }

    public void setFrame(int frame) {
        this.anim.setFrame(frame);
    }

    public void destroy() {
        this.isDestroyed = true;
    }

    public void redraw() {
        this.anim.nextFrame();
    }

    public boolean hidden() {
        return this.hidden;
    }

    public Image getImage() {
        return this.anim.getCurrentFrame();
    }

    public boolean collisionWith(Entity entity) {
        if (this.hitBox == null || entity.getHitBox() == null) {
            return false;
        }

        return (this.hitBox.intersects(entity.getHitBox()) || entity.getHitBox().intersects(this.hitBox));
    }

    public boolean isPointInside(int x, int y) {
        if (this.clickBox == null) {
            return false;
        }

        return (((Rectangle) this.clickBox).intersects(x, y));
    }

    public void setHitBoxVisible(boolean visible) {
        this.hitBoxIsVisible = visible;
    }

    public void setHitBoxAlign(int align) {
        this.hitBoxAlign = align;
    }

    public void setHitBoxVAlign(int valign) {
        this.hitBoxValign = valign;
    }
}
