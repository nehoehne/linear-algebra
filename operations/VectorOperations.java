package operations;

import matrices.Vector;

public class VectorOperations {

	//
	//Class constants
	//
	public final static ArithmeticException UNDIFINED_OPERATION = new 
			ArithmeticException("VectorOperationUndefined");
	
	//
	//Class methods
	//
	
	/*
	 * Duplicate method. Current in Vector. 
	 */
	public static int dotProduct(Vector u, Vector v) {
		
		int product = 0;
		
		if(u.length()==v.length())
			for(int i = 0; i < u.length(); i++) 
				product += u.getEntry(i, 0)*v.getEntry(i, 0);
		else
			throw UNDIFINED_OPERATION;
		
		return product;
	}//dotProduct
	
}
