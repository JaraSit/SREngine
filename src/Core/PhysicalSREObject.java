package Core;

//first try about some physics
import java.util.ArrayList;

public class PhysicalSREObject extends SREObject {

    private float velocityX = 0;
    private float velocityY = 0;
    private float velocityFi = 0;
    private float accelerationX = 0;
    private float accelerationY = 0;
    private float accelerationFi = 0;

    private float cogX = 16;
    private float cogY = 20;

//    private ArrayList<Force> forces = new ArrayList<>();
    public PhysicalSREObject() {
        super(0, 0);
    }

    public PhysicalSREObject(float x, float y) {
        super(x, y);
    }

    @Override
    public void draw(GameCore gc, Graphics g) {
    }

    @Override
    public void update(GameCore gc, InputManager input, int delta) {
        this.velocityX += accelerationX;
        this.velocityY += accelerationY;
        this.velocityFi += accelerationFi;

        this.x += velocityX;
        this.y += velocityY;
        this.angle += velocityFi;

    }

    public void setVelocity(float velX, float velY) {
        this.velocityX = velX;
        this.velocityY = velY;
    }

    public void setAcceleration(float accX, float accY) {
        this.accelerationX = accX;
        this.accelerationY = accY;
    }

    public void setRelativeCenterOfGravity(float xc, float cy) {
        this.cogX = xc;
        this.cogY = cy;
    }

//    public void calculateHorizontalCondition() {
//        float horizontalForce = 0;
//        for (int i = 0; i < forces.size(); i++) {
//            horizontalForce += forces.get(i).forceX;
//        }
//        this.accelerationX = horizontalForce;
//    }
//    public void addForce(Force f) {
//        this.forces.add(f);
//    }
    public void addImpuls(float x, float y, float impulsX, float impulsY) {
        this.accelerationX += impulsX;
        this.accelerationY += impulsY;
        this.accelerationFi += impulsX * (x - this.x - cogX) / 300;
        this.accelerationFi += impulsY * (y - this.y - cogY) / 10;
    }

}
