package Old;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

/**
 * The InputManager class handle user input via keyboard and mouse.
 * 
 * @see KeyListener
 * @see MouseListener
 * @see MouseMotionListener
 * @see MouseWheelListener
 * 
 * @author Vojtech 'Rain' Vladyka
 *
 * @since 1.0
 */
public class InputManager implements KeyListener, MouseListener,
		MouseMotionListener, MouseWheelListener {

	private boolean keys[] = new boolean[256];
	private int posX, posY;
	public boolean LMB, RMB, MMB;
	private int rotation;
	private int clicks;
	private Component c;
	private int rotationDirection;
	public boolean event = false;
	public boolean listenKeys = false;
	public String typed = "";
	/**
	 * <code>InputManager</code> c'tor
	 * @param c Component to which listeners will be attached. Usually <code>SREWindow</code> 
	 * 			which extends <code>JFrame</code>
	 */
	public InputManager(Component c) {
		c.addKeyListener(this);
		c.addMouseListener(this);
		c.addMouseMotionListener(this);
		c.addMouseWheelListener(this);
		this.c = c;
		posX = 0;
		posY = 0;
		LMB = RMB = MMB = false;
		rotation = 0;
		clicks = 0;
	}
	
	public int getRotDirection(){
		int rot = rotationDirection;
		rotationDirection = 0;
		return rot;
	}
	

	/**
	 * Mouse clicks getter
	 * @return count of clicks
	 */
	public int getClicks() {
		return clicks;
	}

	/**
	 * Mouse position getter, <b>X axis</b>
	 * @return return relative position
	 */
	public int getPosX() {
		return posX;
	}
	
	public Point getPos(){
		return new Point(posX, posY);
	}

	/**
	 * Mouse position getter, <b>Y axis</b>
	 * @return return relative position
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Wheel rotation getter
	 * @return count of 'clicks' of mouse wheel
	 */
	public int getRotation() {
		return rotation;
	}

	/**
	 * LMB getter
	 * @return true if LMB is pressed, else false
	 */
	public boolean isLMB() {
		boolean ret = LMB;
		LMB = false;
		return ret;
	}

	/**
	 * MMB getter
	 * @return true if MMB is pressed, else false
	 */
	public boolean isMMB() {
		boolean ret = MMB;
		MMB = false;
		return ret;
	}

	/**
	 * RMB getter
	 * @return true if RMB is pressed, else false
	 */
	public boolean isRMB() {
		boolean ret = RMB;
		RMB = false;
		return ret;
	}
	
	/**
	 * Getter for mouse scancode
	 * @return scancode from MouseEvent
	 */
	public int MBPressed(){
		if(isLMB())
			return MouseEvent.BUTTON1;
		if(isMMB())
			return MouseEvent.BUTTON3;
		if(isRMB())
			return MouseEvent.BUTTON2;
		return 0;
	}
	
	public String flush(){
		String buf = typed;
		typed = "";
		return buf;
	}

	/**
	 * Unimplemented yet.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		if(listenKeys){
			typed+=e.getKeyChar();
		}
	}

	/**
	 * Key handler
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() > 0 && e.getKeyCode() < 256) {
			keys[e.getKeyCode()] = true;
		}
		event = true;
	}

	/**
	 * Key handler
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() > 0 && e.getKeyCode() < 256) {
			keys[e.getKeyCode()] = false;
		}
		event = true;
	}

	/**
	 * Key getter
	 * @param key use values from <code>KeyEvent</code>
	 * @return true if key is pressed, false if not
	 */
	public boolean isKeyPressed(int key) {
		if (key > 0 && key < 256) {
			return keys[key];
		}
		
		return false;
	}

	/**
	 * Mouse handler
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		mouseDragged(e);
		
	}

	/**
	 * Mouse handler
	 */
	@Override
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Mouse handler
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Unimplemented yet.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	/**
	 * Unimplemented yet.
	 */
	@Override
	public void mouseExited(MouseEvent e) {

	}

	/**
	 * Mouse handler
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		rotation += e.getWheelRotation();
		rotationDirection = e.getWheelRotation();
		event = true;
	}

	/**
	 * Mouse handler
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		clicks = e.getClickCount();
		LMB = e.getButton() == MouseEvent.BUTTON1;
		MMB = e.getButton() == MouseEvent.BUTTON2;
		RMB = e.getButton() == MouseEvent.BUTTON3;
		event = true;
	}

	/**
	 * Mouse handler
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		posX = e.getPoint().x - ((JFrame) c).getInsets().left;
		posY = e.getPoint().y - ((JFrame) c).getInsets().top;
	}

	/**
	 * Key typped getter
	 * @param key use values from <code>KeyEvent</code>
	 * @return true, if key was typed, false if not
	 */
	public boolean isKeyTyped(int key) {
		if (key > 0 && key < 256 && keys[key]) {
			keys[key] = false;
			return true;
		}
		return false;
	}
	
	public void resetEvent(){
		event = false;
	}

}
