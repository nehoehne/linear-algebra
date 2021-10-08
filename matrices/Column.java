package matrices;

public class Column extends Vector {

	public Column(float[] u) {
		
		super(new float[u.length][1]);
		
		for(int i = 0; i < u.length; i++) 
			this.setEntry(i, 0, u[i]);
		
	}
	
	public Column(int numRows) {
		super(new float[numRows][1]);
	}
	
	/*
	 * Receives the desired position and a dummy value to allow for polymorphism 
	 */
	public float getEntry(int index, int dummy) {
		return super.getEntry(index, 0);
	}
	public int length() {
		return this.getNumRows();
	}
	
}
