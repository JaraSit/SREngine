package Old;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

/**
 * Create and manage game window.
 * 
 * @see JFrame
 * 
 * @since 1.0
 * 
 * @author Vojtech 'Rain' Vladyka, Jaroslav 'Sit' Schmidt
 * 
 */
@SuppressWarnings("serial")
public class SREWindow extends JFrame {

	private int width;
	private int height;
	private Insets in;
	private boolean resized = false;

	/**
	 * Window c'tor
	 * 
	 * @param width
	 *            window width
	 * @param height
	 *            window height
	 */
	public SREWindow(int width, int height) {
		this.width = width;
		this.height = height;

		in = getInsets();

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// This is only called when the user releases the mouse button.
				resized = true;
				in = getInsets();
				setWindowSize(getSize().width - in.left - in.right,getSize().height - in.top - in.bottom);
			}
		});
	}

	/**
	 * Resize indicator
	 * 
	 * @return true if window was resized, false if not
	 */
	public boolean isResized() {
		boolean ret = resized;
		resized = false;
		return ret;
	}

	/**
	 * Width getter
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * Height getter
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * Window size setter
	 * 
	 * @param width
	 * @param height
	 */
	void setWindowSize(int width, int height) {
		this.width = width;
		this.height = height;
		// setSize(width, height);
	}

	/**
	 * Window size setter
	 * 
	 * @param d
	 *            Dimensions
	 */
	void setWindowSize(Dimension d) {
		setWindowSize(d.width, d.height);
	}

	/**
	 * When called, window appears
	 */
	public void showWindow() {
		setVisible(true);
		in = getInsets();
		setSize(this.width - in.left - in.right, this.height - in.top
				- in.bottom);
		// setSize(this.width, this.height);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
