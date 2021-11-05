package matrices;

public abstract class Vector extends Matrix {

	//
	//Class constants
	//
	public final static ArithmeticException UNDIFINED_OPERATION = 
			new ArithmeticException("VectorOperationUndefined");
	
	protected Vector(float[][] u) {
		super(u);
	}
	
	//
	//Dummy methods 
	//
	public int length() {
		return 0;
	}

	//
	//Class methods
	//
	public static float dotProduct(Vector u, Vector v) {
		
		float product = 0;
		
		if(u.length()==v.length())
			for(int i = 0; i < u.length(); i++) 
				product += u.getEntry(i, 0)*v.getEntry(i, 0);
		else
			throw UNDIFINED_OPERATION;
		
		return product;
	}
	

}
