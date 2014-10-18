package Core;

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
import org.lwjgl.input.Keyboard;

public class InputManager {

	private boolean keys[] = new boolean[256];
//	private int posX, posY;
//	public boolean LMB, RMB, MMB;
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
            while(Keyboard.next()) {
                if(Keyboard.getEventKeyState()) {
                    keys[Keyboard.getEventKey()] = true;
                } else {
                    keys[Keyboard.getEventKey()] = false;
                }
            }
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
//	public int getPosX() {
//		return posX;
//	}
//	
//	public Point getPos(){
//		return new Point(posX, posY);
//	}
//
//	public int getPosY() {
//		return posY;
//	}
//
//	public int getRotation() {
//		return rotation;
//	}
//
//	public boolean isLMB() {
//		boolean ret = LMB;
//		LMB = false;
//		return ret;
//	}
//
//	public boolean isMMB() {
//		boolean ret = MMB;
//		MMB = false;
//		return ret;
//	}
//
//	public boolean isRMB() {
//		boolean ret = RMB;
//		RMB = false;
//		return ret;
//	}
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
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		mouseDragged(e);
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//
//	}
//
//	@Override
//	public void mouseWheelMoved(MouseWheelEvent e) {
//		rotation += e.getWheelRotation();
//		rotationDirection = e.getWheelRotation();
//		event = true;
//	}
//
//	@Override
//	public void mouseDragged(MouseEvent e) {
//		clicks = e.getClickCount();
//		LMB = e.getButton() == MouseEvent.BUTTON1;
//		MMB = e.getButton() == MouseEvent.BUTTON2;
//		RMB = e.getButton() == MouseEvent.BUTTON3;
//		event = true;
//	}
//
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		posX = e.getPoint().x - ((JFrame) c).getInsets().left;
//		posY = e.getPoint().y - ((JFrame) c).getInsets().top;
//	}
//
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
