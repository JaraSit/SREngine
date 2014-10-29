package testGame;

import Core.GameCore;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import srengine.utils.Serialiser;

public class Main extends GameCore {

    public static void main(String[] args) {


        Main main = new Main();

        main.setDisplayMode(800, 600, false, true);
        main.setTitle("Test_of_SREngine_v_2_0");
        main.setFPS(30);
        main.displayFPS();

        main.start();

    }

    @Override
    protected void initStates() {
        addState(new Init(0));
        addState(new World01(1));
        addState(new World02(2));
    }

}
