package testGame;

import Core.BaseState;
import Core.Color;
import Core.Entity;
import Core.GameCore;
import Core.Graphics;

public class World02 extends BaseState {
    
    Entity sprite;
    
    public World02(int id) {
        super(id);
        setBackground(Color.YELLOW);
        
        sprite = new Entity(100, 100, "../Graphics/people/Rain_01.png");
    }

    @Override
    protected void enter() {
        super.enter();
        
        add(9, sprite);
        
    }

    @Override
    protected  void draw(GameCore gc, Graphics g) {
        super.draw(gc, g);
        g.setColor(Color.RED);
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
