package Old;

import java.awt.Color;
import java.awt.Graphics2D;


/**
 * This class represents all game states
 * 
 * @author Vojtech 'Rain' Vladyka, Jaroslav 'Sit' Schmidt
 */

public class BaseState {

	private int id;
	private Color background = Color.BLACK;
	private SortedList<SREObject> objects = new SortedList<>();
	private boolean debug = false;
        public Camera camera;
        
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
         * @param input
         *              key and mouse input manager
         * @param gc 
         *              GameContainer class
         */
	protected void update(InputManager input, GameContainer gc) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).update(input, gc);
		}
                if(camera != null)
                camera.update(gc);
	}

        /**
         * Draw method of state. This method is called if state is active
         * 
         * @param g
         *          Graphic context of double buffer
         * @param gc 
         *          GameContainer class
         */
	protected void draw(Graphics2D g, GameContainer gc) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).draw(g, gc);
		}
	}

        /**
         * Background color setter
         * 
         * @param color 
         *          Color of background
         */
	protected void setBackground(Color color) {
		this.background = color;
	}

        /**
         * Id getter
         * 
         * @return id of instance
         */
	public int getId() {
		return id;
	}

        /**
         * Background color getter
         * 
         * @return color of background
         */
	public Color getBackgroundColor() {
		return background;
	}

        /**
         * Add object <code>Entity</code> to list
         * 
         * @param entity
         */
	protected void add(SREObject entity) {
             setCamera(entity);
	     objects.add(0, entity);
	}

        /**
         * Add object <code>Entity</code> to list with specific Z-index
         * 
         * @param zIndex
         * 
         * @param entity 
         */
	protected void add(int zIndex, SREObject entity) {
            setCamera(entity);
	    objects.add(zIndex, entity);
	}
	
	protected SREObject remove(SREObject entity){
		return objects.remove(entity);
	}
	
	protected SREObject get(SREObject entity){
		return objects.get(entity);
	}
	
	protected SREObject get(int index){
		return objects.get(index);
	}
	
	protected int size(){
		return objects.size();
	}
        
        protected void cameraOn(Entity cameraEntity){
            camera = new Camera(cameraEntity);
        }
        
        protected void cameraOn(Entity cameraEntity, int width, int height){
            camera = new Camera(cameraEntity, width, height);
        }
        
        public float getCameraXMove() {
            if(camera != null) return camera.getXMove();
            return 0;
        }
        
        public float getCameraYMove() {
            if(camera != null)return camera.getYMove();
            return 0;
        }
        
        private void setCamera(SREObject obj){
            if(camera != null)
            obj.setCamera(camera);
        }
}
