package Core;

public class Matrix<I> {
    
    private float[][] data;
    
    public Matrix(float[][] array) {
        
    }
    
    private void coppyArray(float[][] array) {
        this.data = new float[array.length][array[0].length];
    }
}
