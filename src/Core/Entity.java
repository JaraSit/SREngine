package Core;

public class Entity extends SREObject {

    private Image image;
    private Animation anim;
    private boolean centered = false;
    private boolean hiden = false;

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
        if (image != null && !hiden) {
            image.draw((int) x, (int) y, centered);
        } else if (anim != null && !hiden) {
            anim.draw((int)x, (int)y, centered);
        }
    }

    @Override
    public void update(GameCore gc, int delta) {
        if(anim != null) {
            anim.update(delta);
            if(anim.isAfterSingleShot()) hide();
            anim.getCurrentFrame().setAngleDeg(this.getAngleDeg());
        } else if(image != null){
            this.image.setAngleDeg(this.getAngleDeg());
            
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

}
