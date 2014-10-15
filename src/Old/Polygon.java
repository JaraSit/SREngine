package Old;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/**
 * 
 * @warning Unsupported
 *
 */
public class Polygon implements IHitBox {
    
    ArrayList vertices = new ArrayList();

    public Polygon() {
        this(new Point(), new Point(), new Point(), new Point());
    }

    public Polygon(Point p1, Point p2, Point p3, Point p4) {
        vertices.add(p1);
        vertices.add(p2);
        vertices.add(p3);
        vertices.add(p4);
    }

    @Override
    public void setX(float x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setY(float y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRotate(float angle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics2D g, GameContainer gc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getWidth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean intersects(IHitBox hitbox) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public boolean intersects(int px, int py) {
		// TODO Auto-generated method stub
		return false;
	}
}
