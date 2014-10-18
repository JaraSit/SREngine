package testGame;

import Core.BaseState;
import Core.Color;
import Core.Entity;
import Core.GameCore;
import Core.Graphics;
import Core.Image;
import Core.InputManager;
import org.lwjgl.input.Keyboard;

public class World01 extends BaseState {

    Image image;
//    Serialiser s = new Serialiser("../pack.dat");
    Entity sprite;
    Entity sprite2;
    Entity entity;
    Entity brick11;
    Entity brick12;
    Entity brick13;
//    Entity brick21 = new Entity(200, 232,
//            "../Graphics/blocks/floor_metal_01.png");
//    Entity brick22 = new Entity(232, 232,
//            "../Graphics/blocks/floor_metal_01.png");
//    Rectangle rect = new Rectangle(32, 32);
//    Rectangle rect2 = new Rectangle(32, 40);
//    Rectangle rect3 = new Rectangle(200, 300, 32, 32);
//    Entity ser;
    Entity animSer;
    Entity expl1;
//    Entity r2d2 = new Entity(50, 350, new String[]{"r2d2_01_a.png",
//        "r2d2_01_b.png"}, s);
//    Image a;
//    Image b;
//    Image c;
//    Image d;
//    int upd = 0;
    int upd2 = 0;
    double angle = 0;
//   
    public World01(int id) {
        super(id);
        setBackground(Color.GRAY);
        
//        ArrayList<String> l = new ArrayList<>();
//        l.add("../Graphics/people/Sit_01_a.png");
//        l.add("../Graphics/people/Sit_01_b.png");
//        entity = new Entity(100, 232, l);
//        entity.run(300);
//"Graphics/people/Rain_01_b.png"
//        try {
            
//            ser = new Entity(400, 300, s.getData("female_01.png"));
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        animSer.run(360);
//        r2d2.run(350);
//        expl1.hide();
//        // Sound.playSound("../Music/01.wav");
    }
//

    @Override
    protected void enter() {
        super.enter();
        
        sprite = new Entity(100, 100, "Graphics/people/Rain_01.png");
        sprite2 = new Entity(100, 200, "../Graphics/people/Sit_01.png");
        brick11 = new Entity(200, 200, "../Graphics/blocks/floor_metal_01.png");
        brick12 = new Entity(232, 200, "../Graphics/blocks/floor_metal_01.png");
        brick13 = new Entity(264, 200, "../Graphics/blocks/floor_metal_01.png");
        animSer = new Entity(10, 300, new String[]{"../Graphics/people/Rain_01_a.png", "../Graphics/people/Rain_01_b.png"});
        expl1 = new Entity(200, 232, new String[]{"../Graphics/fx/expl_01_a.png",
        "../Graphics/fx/expl_01_b.png", "../Graphics/fx/expl_01_c.png", "../Graphics/fx/expl_01_d.png", "../Graphics/fx/expl_01_e.png",
        "../Graphics/fx/expl_01_f.png", "../Graphics/fx/expl_01_g.png", "../Graphics/fx/expl_01_h.png"});
        
        animSer.startAnimate(350);
                
        add(9, sprite);
//        // sprite.invertOverY(true);
////		sprite.setCentered(true);
        add(5, sprite2);
//        sprite2.setHitboxValign(IHitBox.VALIGN_BOTTOM);
//        sprite2.setHitboxAlign(IHitBox.ALIGN_RIGHT);
//        sprite2.setAngle(180);
////        sprite2.invertOverX(true);
//        add(1, entity);
//        entity.invertOverY();
        add(-1, brick11);
        add(-1, brick12);
        add(-1, brick13);
//        add(-1, brick21);
//        add(-1, brick22);
//        add(6, ser);
        add(9, animSer);
//        sprite.setHitBox(rect);
//        sprite2.setHitBox(rect2);
//        add(5, new TestObject());
        add(10, expl1);
//        add(9, r2d2);
    }
//
//    @Override
//    protected void draw(Graphics2D g, GameContainer gc) {

//
//    }
//

    @Override
    protected void update(GameCore gc, InputManager input, int delta) {
        super.update(gc, input, delta);
        
//        sprite.setX(sprite.getX()+0.02f*delta);
//        gc.enterState(2);
//        super.update(input, gc);
        angle += 0.005*delta;
//        upd++;
        upd2++;
////		sprite.setAngle(sprite.getAngle() + 0.01f);
//        sprite.setAngle(1);
//        
//
        if (input.isKeyPressed(Keyboard.KEY_LEFT)) {
            sprite.setX(sprite.getX() - 0.05f*delta);
            // gc.enterState(2);

        }

        if (input.isKeyPressed(Keyboard.KEY_RIGHT)) {
            sprite.setX(sprite.getX() + 0.05f*delta);
        }

        if (input.isKeyPressed(Keyboard.KEY_UP)) {
            sprite.setY(sprite.getY() - 0.05f*delta);
        }

        if (input.isKeyPressed(Keyboard.KEY_DOWN)) {
            sprite.setY(sprite.getY() + 0.05f*delta);
        }
//
        if (input.isKeyTyped(Keyboard.KEY_Q)) {
            expl1.singleShot(70);
//            sprite2.setAngle(sprite2.getAngle() - 0.1f);
        }
//        sprite2.setX(input.getPosX());
//        sprite2.setY(input.getPosY());
//        entity.setX(upd2 / 4);
        animSer.setX(upd2 / 20);
//        r2d2.setX((float) (upd2 / 4.5));
//
//        if (sprite.collisionWith(sprite2)) {
//            System.out.println("kolize");
//        } else {
//            System.out.println("neni kolize");
//        }
//        
        if (input.isKeyTyped(Keyboard.KEY_K)) {
            System.out.println("release");
            remove(brick13);
        }
//
    }

    @Override
    protected void draw(GameCore gc, Graphics g) {
        super.draw(gc, g);

        g.setColor(Color.RED);
        g.drawLine(200, 200, 400, 400);
//        g.setColor(Color.RED);
//        g.drawRectangle(100, 100, 200, 200);
        
//        g.drawImage(image);
        
        //        super.draw(g, gc);
//
//        g.setColor(Color.black);
        g.drawLine(500, 200, (int) Math.round(Math.sin(angle) * 50 + 500),
                (int) Math.round(Math.cos(angle) * 50 + 200));
//        g.drawLine(0, 0, gc.getWindow().getWidth(), gc.getWindow().getHeight());
//        g.drawLine(0, 0, 600, 600);
//        g.drawChars("It's race!".toCharArray(), 0, 10, 10, 290);
//        rect3.draw(g, gc);
    }
}
