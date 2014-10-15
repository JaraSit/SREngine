package Core;

import srengine.utils.SortedList;

public class BaseState {

    private int id;
    private Color bgColor;
    private SortedList<SREObject> objects = new SortedList<>();

    public BaseState(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    protected void enter() {

    }

    protected void update(GameCore gc, int delta) {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update(gc, delta);
        }
    }

    protected void draw(GameCore gc, Graphics g) {
        g.setColor(bgColor);
        g.fillRectangle(0, 0, gc.getWidth(), gc.getHeight());

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).draw(gc, g);
        }
    }

    protected void add(SREObject entity) {
        objects.add(0, entity);
    }

    protected void add(int zIndex, SREObject entity) {
        objects.add(zIndex, entity);
    }

    protected SREObject remove(SREObject entity) {
        return objects.remove(entity);
    }

    protected SREObject get(SREObject entity) {
        return objects.get(entity);
    }

    protected SREObject get(int index) {
        return objects.get(index);
    }

    protected void setBackground(Color color) {
        this.bgColor = color;
    }

}
