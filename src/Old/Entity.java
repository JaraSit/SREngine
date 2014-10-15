package Old;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;


/**
 * All showable game objects should be Entities
 * 
 * @see SREObject
 * 
 * @author Vojtech 'Rain' Vladyka, Jaroslav 'Sit' Schmidt *
 */
public class Entity extends SREObject {

	private int period = 0;
	private List<Image> images = new ArrayList<>();
	private int frame;
	private Timer upd = new Timer();
	private IHitBox hitBox;
	private IHitBox clickBox;
	private boolean invertOverX = false;
	private boolean invertOverY = false;
	private AffineTransform transform;
	private int valign = IHitBox.VALIGN_TOP;
	private int align = IHitBox.ALIGN_LEFT;
	private boolean hidden = false;
	private boolean destroy = false;
        private boolean hitBoxIsVisible = true;
        private int drawAngle = 0;
        private boolean rotated = false;
	/**
	 * Animated entity c'tor from image paths
	 * 
	 * @param x
	 *            position on X axis
	 * @param y
	 *            position on Y axis
	 * @param images
	 *            ArrayList of animation pictures
	 */
	public Entity(float x, float y, ArrayList<String> images) {
		super(x, y);
		for (int i = 0; i < images.size(); i++) {
			try {
				this.images.add(ImageIO.read(new File(images.get(i))));
			} catch (Exception ex) {
				System.err.println("Cannot load animation resource.\n"+ex.getCause());
			}
		}
	}

	/**
	 * Animated entity c'tor from image names and serialiser
	 * 
	 * @param x
	 *            position on X axis
	 * @param y
	 *            position on Y axis
	 * @param images
	 *            array of animation picture names
	 * @param s
	 *            Serialiser with data
	 */
	public Entity(float x, float y, String[] images, Serialiser s) {
		super(x, y);
		for (int i = 0; i < images.length; i++) {
			try {
				this.images.add(ImageIO.read(s.getData(images[i])));
				ImageIO.setUseCache(false);
			} catch (Exception ex) {
				System.err.println("Cannot load animation resource.\n"+ex.getCause()+"\n"+ex.getMessage());
			}
		}
	}

	/**
	 * Static entity c'tor from image name and serialiser
	 * 
	 * @param x
	 *            position on X axis
	 * @param y
	 *            position on Y axis
	 * @param images
	 *            picture name
	 * @param s
	 *            Serialiser with data
	 */
	public Entity(float x, float y, String image, Serialiser s) {
		super(x, y);
		try {
			this.images.add(ImageIO.read(s.getData(image)));
		} catch (Exception ex) {
			System.err.println("Cannot load animation resource.\n"+ex.getCause());
		}

	}

	/**
	 * Static entity c'tor from stream
	 * 
	 * @param x
	 *            position on X axis
	 * @param y
	 *            position on Y axis
	 * @param stream
	 *            image stream
	 */
	public Entity(float x, float y, ByteArrayInputStream stream) {
		super(x, y);
		this.images = new ArrayList<>();
		try {
			this.images.add(ImageIO.read(stream));
		} catch (Exception ex) {
			System.err.println("Cannot load animation resource.\n"+ex.getCause());
		}
	}

	/**
	 * Static entity c'tor from image path
	 * 
	 * @param x
	 *            position on X axis
	 * @param y
	 *            position on Y axis
	 * @param imagePath
	 *            path to image
	 */
	public Entity(float x, float y, String imagePath) {
		super(x, y);
		this.images = new ArrayList<>();
		try {
			this.images.add(ImageIO.read(new File(imagePath)));
		} catch (Exception ex) {
			System.err.println("Cannot load animation resource.\n"+ex.getCause());
		}
	}

	/**
	 * Setter for animation period
	 * 
	 * @param period
	 *            period in milliseconds or 0 if animation will triggered
	 *            manually
	 */
	public void setPeriod(int period) {
		if (period >= 0) {
			this.period = period;
		} else {
			System.err.println("Animation speed is out of range.");
		}
	}
	
	/**
	 * Period getter
	 * 
	 * @return period in milliseconds
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * Call it for animation start
	 * 
	 * @param period
	 *            period in milliseconds
	 */
	public void run(int period) {
		setPeriod(period);
		if (period > 0) {
			upd.schedule(new TimerTask() {
				@Override
				public void run() {
					if (frame < images.size() - 1) {
						frame++;
					} else {
						frame = 0;
					}
				}
			}, 0, period);
		}
	}

	/**
	 * Call it for animation start with period set by setPeriod method
	 */
	public void run() {
		this.run(period);
	}

	/**
	 * Stop animation run
	 */
	public void stop() {
		upd.cancel();
	}

	public void hide() {
		hidden = true;
	}
	
	public void hideShow(){
		if(hidden){
			show();
		}else{
			hide();
		}
	}

	public void show() {
		hidden = false;
	}
	
	public void singleshot(final int period, boolean blankAfter) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				show();
				for (int i = 0; i < images.size() - 1; i++) {
					try {
						redraw();
						Thread.sleep(period);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				hidden = true;
				frame = 0;
			}
		}).start();
	}

	/**
	 * Manual redraw of animation
	 */
	public int redraw() {
		if (frame < images.size() - 1)
			return frame++;
		else
			return frame = 0;
	}
        
        /**
         * Frame setter
         * 
         * @param frame 
         */
        public void setFrame(int frame){
            if (frame < images.size())
			this.frame = frame;
		else
			frame = 0;
        }

	/**
	 * Draw method
	 */
	// @Override
	// public void draw(Graphics2D g, GameContainer gc) {
	// //is on a screen?
	// if ((getX() + getImage().getWidth(null) > 0)
	// && (getX() < gc.getWindow().getWidth())
	// && (getY() + getImage().getHeight(null) > 0)
	// && (getY() < gc.getWindow().getHeight())) {
	// if (angle != 0) {
	// gc.transformGraphics(x, y, angle);
	// if (isCentered) {
	// g.drawImage(images.get(frame),
	// -this.getImage().getWidth(null) / 2, -this
	// .getImage().getHeight(null) / 2, null);
	// } else {
	// g.drawImage(images.get(frame),
	// -(1-invertXCoef)*this.getImage().getWidth(null)/2, 0, null);
	// }
	// gc.resetTransform();
	// } else {
	// if (isCentered) {
	// g.drawImage(
	// images.get(frame),
	// (int) (this.x - this.getImage().getWidth(null) / 2),
	// (int) (this.y - this.getImage().getHeight(null) / 2),
	// null);
	// } else {
	// g.drawImage(images.get(frame), (int) this.x, (int) this.y,
	// null);
	// }
	// }
	// if (hitBox != null) {
	// g.setColor(Color.red);
	// hitBox.draw(g, gc);
	// }
	// }
	// }
	/**
	 * Draw method
	 */
	@Override
	public void draw(Graphics2D g, GameContainer gc) {
		if (hidden == false) {
       
                    int iHeight = this.getImage().getHeight(null);
                    int iWidth = this.getImage().getWidth(null);
                    
                    float moveX = 0;
                    float moveY = 0;
                    
                    if(camera != null){
                        moveX = camera.getXMove();
                        moveY = camera.getYMove();
                    }
			// is on a screen?
			if ((getX() + moveX + iWidth*2 > 0)
					&& (getX() + moveX - iWidth*2 < gc.getWindow().getWidth())
					&& (getY() + moveY + iHeight*2 > 0)
					&& (getY() + moveY - iHeight*2 < gc.getWindow().getHeight())) {
                            
                            
                            
				transform = new AffineTransform();
				transform.translate(x+moveX, y+moveY);

				if (angle != 0) {
					transform.rotate(angle);
				}
                                
				if (isCentered) {
					transform.translate(-iWidth / 2,
							-iHeight / 2);
				}                              
                                
                                g.drawImage(this.getImage(), transform, null);
                                
                                if(hitBoxIsVisible){
				if (hitBox != null) {
					g.setColor(Color.RED);
					hitBox.draw(g, gc);
				}
				if (clickBox != null) {
					g.setColor(Color.GREEN);
					clickBox.draw(g, gc);
				}
                                }
                 
			}
			
		}
	}

	/**
	 * Actual image getter
	 * 
	 * @return returned image
	 */
	public Image getImage() {
		return images.get(frame);
	}

	/**
	 * HitBox setter
	 * 
	 * @param hitBox
	 */
	public void setHitBox(IHitBox hitBox) {
		this.hitBox = hitBox;
	}

	public void setClickBox(IHitBox clickBox) {
		this.clickBox = clickBox;
	}
	
	/**
	 * Update method
	 */
	@Override
	public void update(InputManager input, GameContainer gc) {
		if (this.hitBox != null) {
			float newX = 0;
			float newY = 0;
                        int iHeight = this.getImage().getHeight(null);
                        int iWidth = this.getImage().getWidth(null);
                        int hHeight = this.hitBox.getHeight();
                        int hWidth = this.hitBox.getWidth();
                        float cos = (float) Math.cos(angle);
                        float sin = (float) Math.sin(angle);
                        
			if (valign == IHitBox.VALIGN_TOP) {
				newY = this.y;
			} else if (valign == IHitBox.VALIGN_BOTTOM) {
				newY = this.y + (iHeight - hHeight);
			} else if (valign == IHitBox.VALIGN_CENTER) {
                            newY = this.y + (iHeight/2 - hHeight/2);
			}
                        
                        if(align == IHitBox.ALIGN_LEFT){
                            newX = this.x;
                        } else if(align == IHitBox.ALIGN_RIGHT){
                            newX = this.x + (iWidth - hWidth);
                        } else if(align == IHitBox.ALIGN_CENTER){
                            newX = this.x + (iWidth/2 - hWidth/2);
                        }
                        
                        float tempX = newX - x;
                        float tempY = newY - y;
                        if(this.angle != 0){
                            newX = (tempX*cos - tempY*sin) + x;
                            newY = (tempX*sin + tempY*cos) + y;
                        }
                        
			this.hitBox.setX(newX);
			this.hitBox.setY(newY);
			this.hitBox.setRotate(this.angle);
		}
		if(this.clickBox != null){
			this.clickBox.setX(x);
			this.clickBox.setY(y);
			this.clickBox.setRotate(this.angle);
		}
	}

//	/**
//	 * Invert over Y method
//	 */
//	public void invertOverY(boolean b) {
//		this.invertOverY = b;
//	}
//
//	/**
//	 * Invert over X method
//	 */
//	public void invertOverX(boolean b) {
//		this.invertOverX = b;
//	}
        
        /**
         * Valign setter
         * 
         * @param valign 
         */
	public void setHitboxValign(int valign) {
		this.valign = valign;
	}

        /**
         * Align setter
         * 
         * @param align 
         */
        public void setHitboxAlign(int align){
            this.align = align;
        }
        
        /**
         * Hitbox getter
         * 
         * @return IHitBox 
         */
        public IHitBox getHitBox(){
            return this.hitBox;
        }
        
        public IHitBox getClickBox(){
            return this.clickBox;
        }
        
        /**
         * Calculate collision between two hitboxes
         * 
         * @param entity
         * 
         * @return boolean 
         */
        public boolean collisionWith(Entity entity){
            if(this.hitBox == null || entity.getHitBox() == null){
                return false;
            }
            
            return (this.hitBox.intersects(entity.getHitBox()) || entity.getHitBox().intersects(this.hitBox));
        }
        
        public boolean isPointInside(int x, int y){
            if(this.clickBox == null){
                return false;
            }
            
            return (((Rectangle)this.clickBox).intersects(x,y));
        }
        
        public boolean isDestroyed(){
        	return destroy;
        }
        
        public void destroy(){
        	destroy = true;
        }
        
        /**
         * Hitbox visible setter
         * 
         * @param visible 
         *          is hitbox visible?
         */
        public void setHitBoxVisible(boolean visible){
            this.hitBoxIsVisible = visible;
        }
        

    	public boolean hidden() {
    		return hidden;
    	}
        
        public void staticRotate(int angle){
            if(this.drawAngle != angle){
                if(angle == 0 || angle == 90 || angle == 180 || angle == 270){
                    while(angle != drawAngle){
                        rotate90();
                    }
                }
            }
        }
        
        public void rotate90(){
            rotated = true;
            drawAngle += 90;
            if(drawAngle >270) drawAngle = 0;
            for(int k = 0; k < images.size(); k++){
            BufferedImage image = (BufferedImage)images.get(k);
            int pWidth = image.getWidth();
            int pHeight = image.getHeight();
            BufferedImage newImage = new BufferedImage(pHeight, pWidth, BufferedImage.TYPE_INT_ARGB);
            
            for(int i = 0; i < pWidth; i++){
                for(int j = 0; j < pHeight; j++){
                    newImage.setRGB(pHeight-1-j, i, image.getRGB(i, j));
                }
            }
            this.images.set(k, newImage);      
            }
        }
        
               
        public void invertOverX(){
            for(int k = 0; k < images.size(); k++){
            BufferedImage image = (BufferedImage)images.get(k);
            int pWidth = image.getWidth();
            int pHeight = image.getHeight();
            BufferedImage newImage = new BufferedImage(pWidth, pHeight, BufferedImage.TYPE_INT_ARGB);
            
            for(int i = 0; i < pWidth; i++){
                for(int j = 0; j < pHeight; j++){
                    newImage.setRGB(i, pHeight-1-j, image.getRGB(i, j));
                }
            }
            this.images.set(k, newImage);      
            }
        }
        
        public void invertOverY(){
            for(int k = 0; k < images.size(); k++){
            BufferedImage image = (BufferedImage)images.get(k);
            int pWidth = image.getWidth();
            int pHeight = image.getHeight();
            BufferedImage newImage = new BufferedImage(pWidth, pHeight, BufferedImage.TYPE_INT_ARGB);
            
            for(int i = 0; i < pWidth; i++){
                for(int j = 0; j < pHeight; j++){
                    newImage.setRGB(pWidth-1-i, j, image.getRGB(i, j));
                }
            }
            this.images.set(k, newImage);      
            }
        }
}