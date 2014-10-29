package Core;

/**
 * HitBox interface
 * 
 * @author Vojtech 'Rain' Vladyka, Jaroslav 'Sit' Schmidt
 */

public interface IHitBox {
    
    /**
     * Valign constants
     */
    public final int VALIGN_TOP = 1;
    public final int VALIGN_CENTER = 2;
    public final int VALIGN_BOTTOM = 3;
    
    /**
     * Align constants
     */
    public final int ALIGN_LEFT = 4;
    public final int ALIGN_CENTER = 5;
    public final int ALIGN_RIGHT = 6;
    
    /**
     * X position setter
     * 
     * @param x 
     *          X position
     */
    public void setX(float x);
    
    /**
     * Y position setter
     * 
     * @param y
     *          Y position
     */
    public void setY(float y);
    
    /**
     * Width getter
     * 
     * @return width 
     */
    public int getWidth();
    
    /**
     * Height getter
     * 
     * @return height
     */
    public int getHeight();
    
    /**
     * Angle setter
     * 
     * @param angle
     *          Angle of rotation
     */
    public void setRotate(float angle);
    
    /**
     * Draw method
     * 
     * @param g
     *          Graphics context
     * @param gc 
     *          GameContainer
     */
    public void draw(GameCore gc, Graphics g);
    
    /**
     * Intersect method
     * 
     * @param hitbox
     * 
     * @return boolean
     */
    public boolean intersects(IHitBox hitbox);
    
    /**
     * Intersect method
     * 
     * @param px x coordinate
     * @param py y coordinate
     * 
     * @return boolean
     */
    public boolean intersects(int px, int py);
}
