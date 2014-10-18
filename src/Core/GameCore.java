package Core;

import java.util.ArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class GameCore {

    //info about project
    private static final String VERSION = "2.0";
    private static final String SIT = "Jaroslav 'Sit' Schmidt";
    private static final String RAIN = "Vojtech 'Rain' Vladyka";
    private static final int YEAR = 2014;

    //timing
    private long previousTime;
    private int delta;

    //states managing
    private int actualState = 0;
    private boolean inState = true;

    private ArrayList<BaseState> states = new ArrayList<>();
    private Graphics graphics = new Graphics();
    private InputManager input = new InputManager();

    public void start() {

        //print info
        System.out.println("SREngine v" + VERSION + " created by " + SIT
                + " & " + RAIN + " at " + YEAR);

        //create window
        try {
            Display.create();
        } catch (LWJGLException e) {
            System.out.println("Error during creating display mode");
            System.exit(0);
        }

        //init OpenGl
        initOpenGL();
        initStates();

        //game loop
        previousTime = System.currentTimeMillis();
//        while (true) {
//            inState = true;
//            states.get(0).enter();
//            while (inState) {
//                delta = (int) (System.currentTimeMillis() - previousTime);
//                previousTime = System.currentTimeMillis();
//
//                states.get(actualState).update(this, delta);
//                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
//                states.get(actualState).draw(this, graphics);
//                Display.update();
//
//                //TODO!!!!
//                if (Display.isCloseRequested()) {
//                    Display.destroy();
//                    break;
//                }
//            }
//        }

//        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        states.get(0).enter();
        while (!Display.isCloseRequested()) {

            delta = (int) (System.currentTimeMillis() - previousTime);
            previousTime = System.currentTimeMillis();

            input.update();
            
            states.get(actualState).update(this, input, delta);
//            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            states.get(actualState).draw(this, graphics);
            Display.update();
        }
        Display.destroy();

    }

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

    public void setTitle(String title) {
        Display.setTitle(title);
    }

    public void setFPS(int FPS) {
        Display.sync(FPS);
    }

    public void addState(BaseState state) {
        this.states.add(state);
    }

    public void enterState(int id) {
        for (int i = 0; i < states.size(); i++) {
            if (states.get(i).getId() == id) {
                actualState = i;
                inState = false;
            }
        }
    }

    public int getWidth() {
        return Display.getWidth();
    }

    public int getHeight() {
        return Display.getHeight();
    }

    //PRIVATE FUNCTIONS
    private void initOpenGL() {
//        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
//        GL11.glClearColor(0, 0, 0, 0);
//        GL11.glEnable(GL11.GL_BLEND);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
//        GL11.glColor3f(0.5f, 0.5f, 1.0f);
        GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
    
    public void initStates() {
    }

}
