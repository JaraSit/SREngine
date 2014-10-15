package testGame;

import Core.BaseState;
import Core.GameCore;
import Core.Graphics;

public class World02 extends BaseState {
    
    public World02(int id) {
        super(id);
    }

//    @Override
//    protected void init() {
//        super.init();
//        setBackground(Color.yellow);
//    }
//
    protected  void draw(GameCore gc, Graphics g) {
        g.fillRectangle(0,0,100,100);
    }
//    @Override
//    protected void draw(Graphics2D g, GameContainer gc) {
//        super.draw(g, gc);
//    }
//
//    @Override
//    protected void update(InputManager input, GameContainer gc) {
//        super.update(input, gc);
//    }
}
