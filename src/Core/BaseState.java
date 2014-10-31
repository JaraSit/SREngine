package Core;

import srengine.utils.SortedList;

/**
 * This class represents all game states
 *
 * @author Vojtech 'Rain' Vladyka, Jaroslav 'Sit' Schmidt
 */
public abstract class BaseState {

    private int id;
    private Color bgColor;
    private SortedList<SREObject> objects = new SortedList<>();
    private Camera camera;

    /**
     * c'tor for <code>BaseState</code> which have specific id
     *
     * @param id
     */
    public BaseState(int id) {
        this.id = id;
    }

    /**
     * This method will be called if state is created
     */
    protected void init() {

    }

    /**
     * This method will be called when it enter to state
     */
    protected void enter() {

    }

    /**
     * Update method of state. This method is called if state is active
     *
     * @param gc GameContainer class
     * @param input key and mouse input manager
     * @param delta time correction of inaccuracies
     */
    protected void update(GameCore gc, InputManager input, int delta) {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update(gc, input, delta);
        }

        if (camera != null) {
            camera.update(gc);
        }
    }

    /**
     * Draw method of state. This method is called if state is active
     *
     * @param gc GameContainer class
     * @param g Graphic context of Display
     */
    protected void draw(GameCore gc, Graphics g) {
        g.setColor(bgColor);
        g.fillRectangle(0, 0, gc.getWidth(), gc.getHeight());

        if (camera != null) {
            g.translate(camera.getXMove(), camera.getYMove());
        }

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).draw(gc, g);
        }
        
        if (camera != null) {
            g.translate(-camera.getXMove(), -camera.getYMove());
        }
    }

    /**
     * Add object <code>Entity</code> to list
     *
     * @param entity
     */
    protected void add(SREObject entity) {
        add(0, entity);
    }

    /**
     * Add object <code>Entity</code> to list with specific Z-index
     *
     * @param zIndex
     * @param entity
     */
    protected void add(int zIndex, SREObject entity) {
        objects.add(zIndex, entity);
    }

    /**
     * Remove object <code>Entity</code> from list
     *
     * @param entity object which will be removed
     * @return
     */
    protected SREObject remove(SREObject entity) {
        return objects.remove(entity);
    }

    /**
     * Turn on camera. Focus on entity
     *
     * @param camEntity
     * @param width
     * @param height
     */
    protected void cameraOn(Entity camEntity, int width, int height) {
        camera = new Camera(camEntity, width, height);
    }
    
    /**
     * 
     * @param moveX
     * @param moveY 
     */
    protected void cameraMove(float moveX, float moveY) {
        this.camera.move(moveX, moveY);
    }
    
    /**
     * 
     * @return 
     */
    protected Camera getCamera() {
        return this.camera;
    }

    /**
     * State object getter
     *
     * @param entity
     * @return
     */
    protected SREObject get(SREObject entity) {
        return objects.get(entity);
    }

    /**
     * State object getter
     *
     * @param index
     * @return
     */
    protected SREObject get(int index) {
        return objects.get(index);
    }

    /**
     * Id getter
     *
     * @return id of state
     */
    public int getId() {
        return id;
    }

    /**
     * Background color setter
     *
     * @param color
     */
    protected void setBackground(Color color) {
        this.bgColor = color;
    }
    
    protected int objectArraySize() {
        return this.objects.size();
    }

}
