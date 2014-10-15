package Core;

import java.awt.Graphics2D;

/**
 * Represents all object with angle and position.
 * 
 * @author Vojtech 'Rain' Vladyka, Jaroslav 'Sit' Schmidt
 */

public abstract class SREObject {
    
    protected float x;
    protected float y;
    protected float angle = 0;
    protected boolean isCentered = false;
    
    /**
     * Imlicit c'tor
     */
    public SREObject(){
        this(0,0);
    }
    
    /**
     * c'tor with specific position
     * 
     * @param x
     *          X position
     * @param y 
     *          Y position
     */
    public SREObject(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * X position setter
     * 
     * @param x 
     *          X position
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Y position setter
     * 
     * @param y 
     *          Y position
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * X position getter
     * 
     * @return X position
     */
    public float getX() {
        return this.x;
    }

    /**
     * Y position getter
     * 
     * @return Y position
     */
    public float getY() {
        return this.y;
    }
    
    /**
     * Angle setter
     * 
     * @param angle
     *          angle of rotation
     */
    public void setAngle(float angle){
        this.angle = (float) (angle%(Math.PI*2));
    }
    
    /**
     * Angle in degrees setter
     * 
     * @param angle 
     *          angle of rotation in degrees
     */
    public void setAngleDeg(float angle){
        this.setAngle((float) Math.toRadians(angle));
    }
    
    /**
     * Angle getter
     * 
     * @return angle of rotation
     */
    public float getAngle(){
        return this.angle;
    }
    
    /**
     * Angle in degrees getter
     * 
     * @return angle of rotation in degrees
     */
    public float getAngleDeg(){
        return (float) Math.toDegrees(this.angle);
    }
    
    /**
     * Add rotate
     * 
     * @param angleDif
     */
    public void rotate(float angleDif){
        this.setAngle(angle+angleDif);
    }
    
    /**
     * Add rotate in degrees
     * 
     * @param angleDif 
     */
    public void rotateDeg(float angleDif){
        this.rotate((float) Math.toRadians(angleDif));
    }
    
    /**
     * Centered setter
     * 
     * @param isCentered 
     */
    public void setCentered(boolean isCentered){
        this.isCentered = isCentered;
    }
    
    /**
     * Abstract method draw
     * 
     * @param g
     *          Graphics context
     * @param gc 
     *          GameContainer
     */
    public abstract void draw(GameCore gc, Graphics g);
    
    /**
     * Abstract method update
     */
    public abstract void update(GameCore gc, int delta);
}
