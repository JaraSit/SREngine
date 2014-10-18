package testGame;

import Core.GameCore;

public class Main extends GameCore {

    public static void main(String[] args) {

        Main main = new Main();

        main.setDisplayMode(800, 600, false, true);
        main.setTitle("Test_of_SREngine_v_2_0");
        main.setFPS(35);

        main.start();

    }

    @Override
    public void initStates() {
        addState(new World01(1));
        addState(new World02(2));
    }

}
