package testGame;

import Core.GameCore;

public class Main extends GameCore {
    
    public static void main(String[] args) {

		Main main = new Main(); // singleton ??



                main.setDisplayMode(800, 600, false, true);
                main.setTitle("Test_of_SREngine_v_2_0");
                main.setFPS(1);
                
                //TODO init states via function
		main.addState(new World01(1));
		main.addState(new World02(2));
                
		main.start();

	}
    
}
