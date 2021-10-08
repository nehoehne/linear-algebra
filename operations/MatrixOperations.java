package operations;

import matrices.Column;
import matrices.Matrix;
import matrices.Row;
import matrices.Vector;

public class MatrixOperations {

	//
	//Class constants 
	//
	public final static ArithmeticException UNDIFINED_OPERATION = new 
			ArithmeticException("MatrixOperationUndefined");
	
	public static Matrix add(Matrix u, Matrix v) {
		
		Matrix w;	
		
		//Check for same size 
		if(u.getNumRows() == v.getNumRows()
				&&(u.getNumCols() == v.getNumCols())) {
			
			w = new Matrix(u.getNumRows(), u.getNumCols());
			
			for(int i = 0; i < u.getNumRows();i++) 
				for(int j = 0; j < u.getNumCols(); j++) 
					w.setEntry(i,j, u.getEntry(i,j) + v.getEntry(i,j));	
		}
		else throw UNDIFINED_OPERATION;
		
		return w;
	}

	public static Matrix multiply(Matrix u, Matrix v) {
		
		Matrix w = new Matrix(u.getNumRows(), v.getNumCols());
		
		//Check if operation is defined 
		if(u.getNumCols() == v.getNumRows()) {
			
			//*INEFFICIENT*
			Column col; Row row; float dotProduct;
			
			for(int i = 0; i < w.getNumRows(); i++) {
				for(int j = 0; j < w.getNumCols(); j++) {
					//ith row of u
					row = u.getRow(i);
					//jth col of v
					col = v.getCol(j);
					//dot product 
					dotProduct = Vector.dotProduct(row, col);
					//Set position 
					w.setEntry(i, j, dotProduct);
				}
			}
		}
		else throw UNDIFINED_OPERATION;
		
		return w;
	}
	
	

}
