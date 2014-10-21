package Core;

import java.util.ArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * The main class of engine. The class stored states and switches between them.
 * Class call main update and draw method.
 *
 * @author Vojtech 'Rain' Vladyka, Jaroslav 'Sit' Schmidt
 */
public abstract class GameCore {

    //info about project
    private static final String NAME = "SREngine";
    private static final String VERSION = "2.0";
    private static final String SIT = "Jaroslav 'Sit' Schmidt";
    private static final String RAIN = "Vojtech 'Rain' Vladyka";
    private static final int YEAR = 2014;

    //timing
    private long previousTime;
    private int delta;
    private int FPS;
    private int realFPS = 0;
    private long fpsTime;
    private boolean displayFPS = false;

    //states managing
    private int actualState = 0;
    private ArrayList<BaseState> states = new ArrayList<>();

    //Graphics and input classes
    private final Graphics graphics = new Graphics();
    private final InputManager input = new InputManager();

    /**
     * Start method. This start whole mechanism
     */
    public void start() {

        //print info
        System.out.println(NAME + " v" + VERSION + " created by " + SIT
                + " & " + RAIN + " at " + YEAR);

        //create window
        try {
            Display.create();
        } catch (LWJGLException e) {
            System.out.println("Error during creating window");
            System.exit(0);
        }

        //init OpenGl
        initOpenGL();
        //load states
        initStates();
        
        //init every state
        for (int i = 0; i < states.size(); i++) {
            states.get(i).init();
        }

        //initialize time variables
        previousTime = System.currentTimeMillis();
        fpsTime = System.currentTimeMillis();

        //call of first enter method
        states.get(0).enter();

        //game loop
        while (!Display.isCloseRequested()) {

            //calculate delta
            delta = (int) (System.currentTimeMillis() - previousTime);
            previousTime = System.currentTimeMillis();

            //update inputManager and calculating of FPS
            input.update();
            updateFPS();

            //call update and draw method od actual State
            states.get(actualState).update(this, input, delta);
            states.get(actualState).draw(this, graphics);

            //Redraw the window
            Display.update();
            Display.sync(FPS);
        }

        Display.destroy();
    }

    /**
     * Display mode setter
     *
     * @param width Width of window
     * @param height Heigh of window
     * @param fullscreen
     * @param Vsync
     */
    public void setDisplayMode(int width, int height, boolean fullScreen, boolean VSync) {
        try {
            DisplayMode displayMode = null;
            int freq = 0;

            if (fullScreen) {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                for (int i = 0; i < modes.length; i++) {
                    DisplayMode mode = modes[i];

                    if ((mode.getWidth() == width) && (mode.getHeight() == height)) {
                        if (displayMode == null) {
                            displayMode = mode;
                        }
                    }

                }
            } else {
                displayMode = new DisplayMode(width, height);
            }

            if (displayMode == null) {
                System.out.println("Failed to find value mode: " + width + "x" + height + " fs=" + fullScreen);
                return;
            }

            Display.setDisplayMode(displayMode);
            Display.setFullscreen(fullScreen);
            Display.setVSyncEnabled(VSync);
        } catch (LWJGLException e) {
            System.out.println("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullScreen + e);
        }
    }

    /**
     * Title setter
     *
     * @param title Title of window
     */
    public void setTitle(String title) {
        Display.setTitle(title);
    }

    /**
     * FPS setter
     *
     * @param FPS Required FPS value
     */
    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

    /**
     * Add state to game
     *
     * @param state New state
     */
    public void addState(BaseState state) {
        this.states.add(state);
    }

    /**
     * Enter to state with this id
     *
     * @param id Id of state
     */
    public void enterState(int id) {
        for (int i = 0; i < states.size(); i++) {
            if (states.get(i).getId() == id) {
                states.get(i).enter();
                actualState = i;
            }
        }
    }

    /**
     * Window width getter
     *
     * @return width of window
     */
    public int getWidth() {
        return Display.getWidth();
    }

    /**
     * Window height getter
     *
     * @return height of window
     */
    public int getHeight() {
        return Display.getHeight();
    }

    /**
     * Init every state, must be inherit
     */
    protected abstract void initStates();

    /**
     * Display FPS value to title
     */
    public void displayFPS() {
        this.displayFPS = true;
    }

    //PRIVATE FUNCTIONS
    
    /**
     * 
     */
    private void initOpenGL() {

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    /**
     * 
     */
    private void updateFPS() {
        if (System.currentTimeMillis() - fpsTime > 1000) {
            if (displayFPS) {
                Display.setTitle("FPS: " + realFPS);
            }
            realFPS = 0;
            fpsTime += 1000;
        }
        realFPS++;
    }

}
