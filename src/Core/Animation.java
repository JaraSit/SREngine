package Core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import srengine.utils.Serialiser;

public class Animation {

    private ArrayList<Image> frames = new ArrayList<>();
    private int actualFrame = 0;
    private int period = 100;
    private int time = 0;
    private boolean isAnimate = false;
    private boolean singleShot = false;
    private boolean afterSingleShot = false;

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

    public Animation(String[] paths, Serialiser s) {
        for (int i = 0; i < paths.length; i++) {
            try {
                frames.add(Image.read(s.getData(paths[i])));
            } catch (IOException ex) {
            }
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
                        afterSingleShot = true;
                    }
                }
            }
        }
    }

    public void startAnimate(int period) {
        this.period = period;
        this.isAnimate = true;
    }

    public void startAnimate() {
        this.isAnimate = true;
    }

    public void singleShot(int period) {
        this.singleShot = true;
        this.startAnimate(period);
    }

    public boolean isAfterSingleShot() {
        if (afterSingleShot) {
            afterSingleShot = false;
            return true;
        }
        return false;
    }

    public Image getCurrentFrame() {
        return this.frames.get(actualFrame);
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void setFrame(int frame) {
        if (frame < frames.size()) {
            this.actualFrame = frame;
        } else {
            frame = 0;
        }
    }

    public void nextFrame() {
        if (actualFrame + 1 < frames.size()) {
            actualFrame++;
        } else {
            actualFrame = 0;
        }
    }
}
