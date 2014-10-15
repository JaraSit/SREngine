package Old;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The main class of engine. The class stored states and switches between them.
 * Class call main update and draw method.
 * 
 * @author Vojtech 'Rain' Vladyka, Jaroslav 'Sit' Schmidt
 */

public class GameContainer {

	private static final float VERSION = 1.0f;
	private static final String SIT = "Jaroslav 'Sit' Schmidt";
	private static final String RAIN = "Vojtech 'Rain' Vladyka";
	private static final int YEAR = 2013;
	
	private SREWindow window = new SREWindow(600, 600);
	private ArrayList<BaseState> states = new ArrayList<>();
	private Graphics2D g, bbg;
	// Time control variables
	private int FPS = 30;
	private int UPS = 100;
	private Timer updateTimer;
	private Timer drawTimer;
	private InputManager input;
	// private BufferStrategy strategy;
	private GameContainer gc = this;
	private AffineTransform transform;
	//private AffineTransform tempTransform;
	private BufferedImage buffer;
	
        /**
         * Start method. This start whole mechanism
         */
	protected void start() {
		System.out.println("SREngine v" + VERSION + " created by " + SIT
				+ " & " + RAIN + " at " + YEAR);
		window.showWindow();
		buffer = new BufferedImage(window.getWidth(), window.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) window.getGraphics();
		bbg = (Graphics2D) buffer.getGraphics();
		transform = bbg.getTransform();
		// window.createBufferStrategy(2);
		// strategy = window.getBufferStrategy();

		// g = (Graphics2D) strategy.getDrawGraphics().create(0, 0,
		// window.getWidth(), window.getHeight());
		// transform = g.getTransform();
		
		updateAndDrawLoop(states.get(0));
	}

        /**
         * Window size setter
         * 
         * @param width
         *          Width of window
         * @param height 
         *          Heigh of window
         */
	protected void setWindowSize(int width, int height) {
		window.setWindowSize(width, height);
	}

        /**
         * Title setter
         * 
         * @param title
         *          Title of window
         */
	protected void setTitle(String title) {
		window.setTitle(title);
	}

        /**
         * Update and draw loop
         * 
         * @param state 
         *          This state will be update and draw
         */
	private void updateAndDrawLoop(final BaseState state) {
		state.enter();
		updateTimer = new Timer();
		input = new InputManager(window);
		updateTimer.scheduleAtFixedRate(new TimerTask() { // timer with updates,
					// kill it by
					// updateTimer.cancel();
					@Override
					public void run() {
						state.update(input, gc);
					}
				}, 0, 1000 / UPS);

		drawTimer = new Timer();
		drawTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (window.isResized()) {
					buffer = new BufferedImage(window.getWidth(), window
							.getHeight(), BufferedImage.TYPE_INT_RGB);
					g = (Graphics2D) window.getGraphics();
					bbg = (Graphics2D) buffer.getGraphics();
					transform = bbg.getTransform();
				}
				bbg.setColor(state.getBackgroundColor());
				bbg.fillRect(0, 0, window.getWidth(), window.getHeight());
				state.draw(bbg, gc);
				g.drawImage(buffer, window.getInsets().left,
						window.getInsets().top, null);
				// strategy.show();
			}
		}, 0, 1000 / FPS);
	}

        /**
         * Add state to list of states
         * 
         * @param state 
         *          This state will be added
         */
	protected void addState(BaseState state) {
		states.add(state);
                state.init();
	}

        /**
         * Enter to state with specific id
         * 
         * @param id 
         *          Id of state
         */
	public void enterState(int id) {
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i).getId() == id) {
				updateTimer.cancel();
				drawTimer.cancel();
				updateAndDrawLoop(states.get(i));
			}
		}
	}

        /**
         * State getter
         * 
         * @param id
         *          Id of state
         * @return <code>BaseState</code> with this id
         */
	public BaseState getState(int id) {
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i).getId() == id) {
				return states.get(i);
			}
		}
		return null;
	}

        /**
         * FPS setter
         * 
         * @param fps 
         */
	protected void setFPS(int fps) {
		this.FPS = fps;
	}

        /**
         * Window getter
         * 
         * @return SREWindow
         */
	public SREWindow getWindow() {
		return window;
	}

        /**
         * This method reset graphics transformations
         */
	public void resetTransform() {
		bbg.setTransform(transform);
	}
}
