package Core;

import java.util.ArrayList;

public class Animation {

    private ArrayList<Image> frames = new ArrayList<>();
    private int actualFrame = 0;
    private int period = 100;
    private int time = 0;
    private boolean isAnimate = false;
    private boolean singleShot = false;

    public Animation(Image[] frms) {
        for (int i = 0; i < frms.length; i++) {
            frames.add(frms[i]);
        }
    }

    public Animation(String[] paths) {
        for (int i = 0; i < paths.length; i++) {
            frames.add(Image.load(paths[i]));
        }
    }

    public void draw(int x, int y, boolean centered) {
        frames.get(actualFrame).draw(x, y, centered);
    }

    public void update(int delta) {
        if (isAnimate) {
            time += delta;
            if (time > period) {
                time -= period;
                actualFrame++;
                if (actualFrame > frames.size() - 1) {
                    actualFrame = 0;
                    if (singleShot) {
                        isAnimate = false;
                        singleShot = false;
                    }
                }
            }
        }
    }

    public void startAnimate(int period) {
        this.period = period;
        this.isAnimate = true;
    }

    public void singleShot(int period) {
        this.singleShot = true;
        this.startAnimate(period);
    }
    
    public boolean isAfterSingleShot() {
        return !singleShot;
    }
    
    public Image getCurrentFrame() {
        return this.frames.get(actualFrame);
    }

}
