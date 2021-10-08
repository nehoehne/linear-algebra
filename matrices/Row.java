package matrices;

public class Row extends Vector {

	public Row(float[] u) {
		
		super(new float[1][u.length]);
		
		for(int i = 0; i < u.length; i++)
			this.setEntry(0, i, u[i]);
	}
	
	public Row(int numCols) {
		super(new float[1][numCols]);
	}
	
	/*
	 * Receives the desired position and a dummy value to allow for polymorphism.
	 */
	public float getEntry(int index, int dummy) {
		return super.getEntry(0, index);
	}//getEntry
	
	public int length() {
		return this.getNumCols();
	}
	
}
