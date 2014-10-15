package Old;

import java.awt.Graphics2D;

import Old.GameContainer;
import Old.InputManager;
import Old.SREObject;

public class TestObject extends SREObject{

	@Override
	public void draw(Graphics2D g, GameContainer gc) {
		//System.out.println("redraw");
		
	}

	@Override
	public void update(InputManager input, GameContainer gc) {
		//System.out.println("update");
	}


}
