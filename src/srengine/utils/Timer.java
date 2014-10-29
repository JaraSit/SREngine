package srengine.utils;

public class Timer {
    
    private long startTime = 0;
    private long actualTime = 0;
    private long loopTime = 0;
    private boolean loop = false;
    private boolean isPaused = true;
    
    public Timer() {
        
    }
    
    public void setTime(long timeMillis) {
        this.loopTime = timeMillis;
        this.loop = false;
    }
    
    public void start() {
        this.startTime = System.currentTimeMillis();
        this.isPaused = false;
    }
    
    public boolean isElapsed() {
        if((actualTime - startTime) > loopTime) {
            this.isPaused = true;
//            reset();
            return true;
        }
        return false;
    }
    
    public void tick() {
        if(!isPaused)
        this.actualTime = System.currentTimeMillis();
    }
}
