package testGame;

import Core.BaseState;
import Core.Color;
import Core.Entity;
import Core.GameCore;
import Core.InputManager;
import srengine.utils.Timer;
import srengine.utils.Serialiser;

public class Init extends BaseState {

    Serialiser s = new Serialiser("pack.dat");
    Entity back = new Entity(200, 100, "sre.png", s);
    Timer t = new Timer();

    public Init(int id) {
        super(id);
    }

    @Override
    protected void enter() {
        super.enter();

        setBackground(Color.BLACK);

        add(back);
        
        t.setTime(3000);
        t.start();
    }

    @Override
    protected void update(GameCore gc, InputManager input, int delta) {
        super.update(gc, input, delta);

        t.tick();
        
        if(input.isKeyPressed(InputManager.KEY_N) || t.isElapsed()) {
            gc.enterState(1);
        }
    }
    
    
    
    

}
