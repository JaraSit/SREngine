package Core;

import java.awt.Dimension;
import java.awt.Point;
//import java.awt.geom.AffineTransform;

/**
 * This class represents rectangle with specific width, height and angle of
 * rotation
 *
 * @author Vojtech 'Rain' Vladyka, Jaroslav 'Sit' Schmidt
 */
public class Rectangle implements IHitBox {

    float x;
    float y;
    int width;
    int height;
    float angle = 0;

    /**
     * Implicit c'tor
     */
    public Rectangle() {
        this(new Point(), new Dimension());
    }

    /**
     * c'tor with specific point and dimensions of rectangle
     *
     * @param p Location of left-top corner
     * @param d Dimensions of rectangle
     */
    public Rectangle(Point p, Dimension d) {
        this(p.x, p.y, d.width, d.height);
    }

    /**
     * c'tor with specific dimensions
     *
     * @param width Width of rectangle
     * @param height Height of rectangle
     */
    public Rectangle(int width, int height) {
        this(0, 0, width, height);
    }

    /**
     * c'tor with specific location and dimensions
     *
     * @param x X position of left-top corner
     * @param y Y position of left-top corner
     * @param width Width of rectangle
     * @param height Height of rectangle
     */
    public Rectangle(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Angle setter
     *
     * @param angle Angle of rotation
     */
    @Override
    public void setRotate(float angle) {
        this.angle = angle;
    }

    /**
     * Angle getter
     *
     * @return angle of rectangle in rad
     */
    public float getRotate() {
        return this.angle;
    }

    /**
     * X position setter
     *
     * @param x X position
     */
    @Override
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Y position setter
     *
     * @param y Y position
     */
    @Override
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
     * Draw method. Rotate graphic context, draw and reset graphic
     * transformation
     *
     * @param g Graphic context
     * @param gc GameContainer
     */
    @Override
    public void draw(GameCore gc, Graphics g) {
//        g.translate(x, y);
//        g.rotate(angle);
//        g.drawRect(0, 0, width, height);
//        gc.resetTransform();
        g.drawRectangle((int)x, (int)y, width, height, angle);
    }

    /**
     * Width getter
     *
     * @return width of rectangle
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * Height getter
     *
     * @return height of rectangle
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * Calculate if vertices of other IHitBox are inside in this IHitBox
     *
     * @param hitbox The other hit box
     * @return boolean
     */
    @Override
    public boolean intersects(IHitBox hitbox) {
        Rectangle rect = (Rectangle) hitbox;

        if (angle == 0 && rect.getRotate() == 0) {
            if (x + width > rect.getX() && x < rect.getX() + rect.getWidth() && y + height > rect.getY() && y < rect.getY() + rect.getHeight()) {
                return true;
            }
            return false;
        } else {

            Point[] points = new Point[4];
            points[0] = new Point((int) rect.getX(), (int) rect.getY());
            points[1] = new Point((int) (rect.getX() + Math.cos(rect.getRotate()) * rect.getWidth()), (int) (rect.getY() + Math.sin(rect.getRotate()) * rect.getWidth()));
            points[2] = new Point((int) (rect.getX() - Math.sin(rect.getRotate()) * rect.getHeight()), (int) (rect.getY() + Math.cos(rect.getRotate()) * rect.getHeight()));
            //            points[3] = new Point((int) (rect.getX() + Math.cos(rect.getRotate()) * rect.getWidth() - Math.sin(rect.getRotate()) * rect.getHeight()), (int) (rect.getY() + Math.sin(rect.getRotate()) * rect.getWidth() + Math.cos(rect.getRotate()) * rect.getHeight()));
            points[3] = new Point(points[1].x + points[2].x - points[0].x, points[1].y + points[2].y - points[0].y);
            boolean pointIntersect = false;
            for (int i = 0; i < points.length; i++) {
                pointIntersect = (pointIntersect || intersects(points[i]));
            }
            return (pointIntersect);
//            return false;
        }
    }

    /**
     * Calculate if point with coordinates px and py is inside in this IHitBox
     *
     * @param px X coordinate
     * @param py Y coordinate
     * @return boolean
     */
    @Override
    public boolean intersects(int px, int py) {
        if (angle == 0) {
            if (x + width > px && x < px && y + height > py && y < py) {
                return true;
            }
            return false;
        } else {

            px -= this.x;
            py -= this.y;

            int newX = (int) (px * Math.cos(-this.angle) - py * Math.sin(-this.angle));
            int newY = (int) (px * Math.sin(-this.angle) + py * Math.cos(-this.angle));

            if ((newX > 0) && (newX < this.width) && (newY > 0) && (newY < this.height)) {
                return true;
            }
            return false;
        }
    }

    /**
     * Calculate if Point is inside in this IHitBox
     *
     * @param point
     *
     * @return boolean
     */
    private boolean intersects(Point point) {
        return intersects(point.x, point.y);
    }
}
