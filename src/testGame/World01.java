package testGame;

import Core.BaseState;
import Core.Color;
import Core.Entity;
import Core.Force;
import Core.GameCore;
import Core.Graphics;
import Core.Image;
import Core.InputManager;
import Core.Rectangle;
import Core.TestEntity;
import srengine.utils.Serialiser;

public class World01 extends BaseState {

    Image image;
    Serialiser s = new Serialiser("../pack.dat");
    Entity sprite;
    Entity sprite2;
    Entity entity;
    Entity entity2;
    Entity brick11;
    Entity brick12;
    Entity brick13;
    Entity brick21 = new Entity(200, 232, "floor_metal_01.png", s);
    Rectangle rect = new Rectangle(32, 32);
    TestEntity animSer;
    Entity expl1;
    int upd2 = 0;
    double angle = 0;
//   

    public World01(int id) {
        super(id);
        setBackground(Color.GRAY);
    }


    @Override
    protected void enter() {
        super.enter();
        
        image = Image.load("../Graphics/people/r2d2_01_a.png");

        sprite = new Entity(10, 350, new String[]{"Sit_01_a.png", "Sit_01_b.png"}, s);

        sprite2 = new Entity(100, 200, "Sit_01.png", s);
        brick11 = new Entity(200, 200, "floor_metal_01.png", s);
        brick12 = new Entity(232, 200, "floor_metal_01.png", s);
        brick13 = new Entity(264, 200, "floor_metal_01.png", s);
        entity = new Entity(20, 20, "Rain_01_a.png", s);
        animSer = new TestEntity(10, 300, new String[]{"Rain_01_a.png", "Rain_01_b.png"}, s);
        expl1 = new Entity(200, 232, new String[]{"expl_01_a.png",
            "expl_01_b.png", "expl_01_c.png", "expl_01_d.png", "expl_01_e.png",
            "expl_01_f.png", "expl_01_g.png", "expl_01_h.png"}, s);
        cameraOn(entity, 1000, 1000);
        animSer.startAnimate(350);
        animSer.addImpuls(animSer.getX(), animSer.getY(), 0.01f, 0);

        sprite.startAnimate(350);

        add(9, sprite);
        add(5, sprite2);
        add(1, entity);
        add(-1, brick11);
        add(-1, brick12);
        add(-1, brick13);
        add(-1, brick21);

        add(9, animSer);
        sprite2.setHitBox(rect);
        add(10, expl1);
    }

    @Override
    protected void update(GameCore gc, InputManager input, int delta) {
        super.update(gc, input, delta);

//        sprite.setX(sprite.getX()+0.02f*delta);
//        gc.enterState(2);
        angle += 0.005 * delta;
//        upd++;
        upd2 += delta;
////		sprite.setAngle(sprite.getAngle() + 0.01f);
//        sprite.setAngle(1);
//        
//
        if (input.isKeyPressed(InputManager.KEY_LEFT)) {
            entity.setX(entity.getX() - 0.1f * delta);
            // gc.enterState(2);

        }

        if (input.isKeyPressed(InputManager.KEY_RIGHT)) {
            entity.setX(entity.getX() + 0.1f * delta);
        }

        if (input.isKeyPressed(InputManager.KEY_UP)) {
            entity.setY(entity.getY() - 0.1f * delta);
        }

        if (input.isKeyPressed(InputManager.KEY_DOWN)) {
            entity.setY(entity.getY() + 0.1f * delta);
        }
//
        if (input.isKeyTyped(InputManager.KEY_Q)) {
            expl1.singleShot(70);
//            sprite2.setAngle(sprite2.getAngle() - 0.1f);
        }

        sprite2.setAngle(sprite2.getAngle() - 0.001f * delta);
        sprite2.setX(input.getMouseX());
        sprite2.setY(input.getMouseY());
        sprite.setX(upd2 / 45);

   
        if (input.isKeyTyped(InputManager.KEY_K)) {
            remove(brick13);
        }

        if (input.isLMBClicked()) {
            System.out.println("LMB");
        }
        if (input.isRMBClicked()) {
            System.out.println("RMB");
        }
        if (input.isMMBClicked()) {
            System.out.println("MMB");
        }

    }

    @Override
    protected void draw(GameCore gc, Graphics g) {
        super.draw(gc, g);

        g.setColor(Color.RED);
        g.drawLine(200, 200, 400, 400);
//        g.setColor(Color.RED);
//        g.drawRectangle(100, 100, 200, 200);

        g.drawImage(image, 200, 100);
        image.testDraw(300, 100);

        //        super.draw(g, gc);
//
        g.setColor(Color.WHITE);
        g.drawLine(500, 200, (int) Math.round(Math.sin(angle) * 50 + 500),
                (int) Math.round(Math.cos(angle) * 50 + 200));

        g.drawCircle(100, 100, 49);
        g.drawOval(300, 300, 300, 30);
        g.drawCircle(200, 200, 30);
//        g.drawLine(0, 0, gc.getWindow().getWidth(), gc.getWindow().getHeight());
//        g.drawLine(0, 0, 600, 600);

        //TODO slow!! :/
//        g.drawString("It's race!", 10, 10);
//        rect3.draw(g, gc);
    }
}
