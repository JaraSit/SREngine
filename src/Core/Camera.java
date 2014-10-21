package Core;

public class Camera {
    private Entity cameraEntity;
    private int cameraBoundWidth = 800;
    private int cameraBoundHeight = 800;
    private float xMove;
    private float yMove;

    public Camera(Entity entity) {
        this.cameraEntity = entity;
    }
    
    public Camera(Entity entity, int width, int height){
        this.cameraEntity = entity;
        this.cameraBoundWidth = width;
        this.cameraBoundHeight = height;
    }

    public void update(GameCore gameCore) {
        if(cameraEntity != null) {
            
            xMove = cameraEntity.getX() - (gameCore.getWidth() / 2);
            yMove = cameraEntity.getY() - (gameCore.getHeight() / 2);
        }
        
        if(cameraBoundWidth < gameCore.getWidth()){
            xMove = 0;
        } else {
        xMove = Math.max(0, xMove);
        xMove = Math.min(xMove, cameraBoundWidth - gameCore.getWidth());
        }

        if(cameraBoundHeight < gameCore.getHeight()){
            yMove = 0;
        } else {
        yMove = Math.max(0, yMove);
        yMove = Math.min(yMove, cameraBoundHeight - gameCore.getHeight()); 
        }

    }

    public float getXMove() {
        return -xMove;
    }

    public float getYMove() {
        return -yMove;
    }
    
    public void setXMove(float xMove){
        this.xMove = xMove;
    }
    
    public void setYMove(float yMove){
        this.yMove = yMove;
    }
    
    public void move(float xMove, float yMove){
        this.xMove += xMove;
        this.yMove += yMove;
    }
    
    protected void setCameraBoundary(int width, int height){
            this.cameraBoundWidth = width;
            this.cameraBoundHeight = height;
    }
    
    public void setFocusOn(Entity entity){
        this.cameraEntity = entity;
    }
    
}
