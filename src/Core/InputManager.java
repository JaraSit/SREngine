package Core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class InputManager {

    private boolean keys[] = new boolean[256];
    private int posX, posY;
    public boolean mouseButtons[] = new boolean[3];
//	private int rotation;
//	private int clicks;
//	private Component c;
//	private int rotationDirection;
//	public boolean event = false;
//	public boolean listenKeys = false;
//	public String typed = "";
//

//	public InputManager(Component c) {
//		c.addKeyListener(this);
//		c.addMouseListener(this);
//		c.addMouseMotionListener(this);
//		c.addMouseWheelListener(this);
//		this.c = c;
//		posX = 0;
//		posY = 0;
//		LMB = RMB = MMB = false;
//		rotation = 0;
//		clicks = 0;
//	}
    public void update() {
        while (Keyboard.next()) {
            keys[Keyboard.getEventKey()] = Keyboard.getEventKeyState();
        }
        while (Mouse.next()) {
            if (Mouse.getEventButton() > -1) {
                mouseButtons[Mouse.getEventButton()] = Mouse.getEventButtonState();
            }

        }

        posX = Mouse.getX();
        posY = Mouse.getY();
    }
//	
//	public int getRotDirection(){
//		int rot = rotationDirection;
//		rotationDirection = 0;
//		return rot;
//	}
//
//	public int getClicks() {
//		return clicks;
//	}
//

    public int getMouseX() {
        return posX;
    }
//	
//	public Point getPos(){
//		return new Point(posX, posY);
//	}
//

    public int getMouseY() {
        return Display.getHeight() - posY;
    }
//
//	public int getRotation() {
//		return rotation;
//	}
//

    public boolean isLMBClicked() {
        boolean lmb = mouseButtons[0];
        mouseButtons[0] = false;
        return lmb;
    }

    public boolean isLMBDown() {
        return mouseButtons[0];
    }

    public boolean isRMBClicked() {
        boolean lmb = mouseButtons[1];
        mouseButtons[1] = false;
        return lmb;
    }

    public boolean isRMBDown() {
        return mouseButtons[1];
    }

    public boolean isMMBClicked() {
        boolean lmb = mouseButtons[2];
        mouseButtons[2] = false;
        return lmb;
    }

    public boolean isMMBDown() {
        return mouseButtons[2];
    }

//
//	public int MBPressed(){
//		if(isLMB())
//			return MouseEvent.BUTTON1;
//		if(isMMB())
//			return MouseEvent.BUTTON3;
//		if(isRMB())
//			return MouseEvent.BUTTON2;
//		return 0;
//	}
//	
//	public String flush(){
//		String buf = typed;
//		typed = "";
//		return buf;
//	}
    public boolean isKeyPressed(int key) {
        if (key > 0 && key < 256) {
            return keys[key];
        }

        return false;
    }

    public boolean isKeyTyped(int key) {
        if (key > 0 && key < 256 && keys[key]) {
            keys[key] = false;
            return true;
        }
        return false;
    }
//	
//	public void resetEvent(){
//		event = false;
//	}
//
}
