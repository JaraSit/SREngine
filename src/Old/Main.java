package Old;

import java.io.IOException;
import Old.GameContainer;
import Old.Serialiser;

public class Main extends GameContainer {

	public static void main(String[] args) {
		Serialiser s = new Serialiser("../pack.dat");
		try {
			s.pack("../Graphics/", "png", 'd');
		} catch (IOException e) {
			System.err.println("Data cannot be packed. Aborting.");
			return;
		}

		Main main = new Main(); // singleton ??

		main.setWindowSize(600, 600);
		main.setTitle("Cubed");
		main.setFPS(30);

		main.addState(new World01(1));
		main.addState(new World02(2));

		main.start();

	}
}
