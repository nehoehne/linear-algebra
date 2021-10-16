package matrices;

import java.util.ArrayList;
/*
 * Some issues to fix:
 * - use of break and continue in loops 
 * - return statements in the middle of a method/multiple return statements 
 * - Unnecessary creation of objects (row operations + dot product/matrix multiplication)
 * 		- idea for dot product make a parent method in matrix that has direct access to the matrix? 
 */
public class Matrix {
	
	//
	//Instance variables 
	//
	private float[][] u;
	private int numRows;
	private int numCols;
	
	//
	//Class constants
	//
	public static final ArithmeticException UNDIFINED_MATRIX =
			new ArithmeticException("MatrixIsUndefined");
	public static final ArithmeticException UNDIFINED_OPERATION = 
			new ArithmeticException("MatrixOperationUndefined");
	public static final ArithmeticException INCONSISTENT_SYSTEM = 
			new ArithmeticException("SystemIsInconsistent");
	
	
	public final float ZERO = 0.0000001f;//Constant determines the level of 
										//accuracy for gaussian elimination 
	
	//
	//Constructors 
	//
	public Matrix(float[][] u) {
		
		this.u = u;
		numRows = u.length;
		
		//Insure that all rows are the same length
		for(int i = 0; i < u.length; i++) 
			if(u[i].length != u[0].length) 
				throw UNDIFINED_MATRIX;
		
		numCols = u[0].length; 
		
	}
	
	public Matrix(int numRows, int numCols) {
		
		//Insure matrix is at least 1x1
		if(numRows < 1||numCols < 1)
			throw UNDIFINED_MATRIX;
			
		u = new float[numRows][numCols];
		
		this.numRows = numRows;
		this.numCols = numCols;
	}
	
	//
	//Instance methods
	//
	/*
	 * Sets each entry in a martix to a random integer within the given range
	 */
	public void randomizeInt(int minValue, int maxValue) {
		
		int range = maxValue - minValue +1; 
		
		for(int i = 0; i < numRows; i++)
			for(int j = 0; j < numCols; j++)
				u[i][j] = (int)(Math.random()*range) + minValue; 
				
	}//randomizeInt
	
	/*
	 * Randomizes each entry in Matrix as specified by given max and min.
	 */
	public void randomize(float minValue, float maxValue) {
		
		float range = maxValue - minValue; 
		
		for(int i = 0; i < numRows; i++)
			for(int j = 0; j < numCols; j++)
				u[i][j] = (float)(Math.random()*range) + minValue; 
				
	}//randomize
	

	/*
	 * Multiply matrix by a scalar.
	 */
	public void multiply(float scalar) {
		for(int i = 0; i < numRows; i++) 
			for(int j = 0; j < numCols; j++)
				u[i][j] *= scalar;
	}//multiply
	
	/*
	 * Returns a deep copy of matrix. 
	 */
	public Matrix clone() {
		
		float[][] clone = new float[numRows][numCols];
		
		for(int i = 0; i < numRows; i++)
			for(int j = 0; j < numCols; j++)
				clone[i][j] = u[i][j];
		
		return new Matrix(clone);
				
	}//clone
	
	public String toString() {
		String s = "";
		
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++)
				s += String.format("%f",u[i][j]) + "\t";
			s += "\n\n";
		}
		return s;
	}//toString
	
	//
	//Getters and setters
	//
	public int getNumRows() {
		return numRows;
	}
	public int getNumCols() {
		return numCols;
	}
	public float getEntry(int row, int col) {
		return u[row][col];
	}
	public void setEntry(int row, int col, float value) {
		u[row][col] = value;
	}
	
	/* 
	 * Returns the column of the desired position as a column object.
	 */
	public Column getCol(int position) {
		float[] u = new float[numRows];
		
		for(int i = 0; i < numRows; i++) 
			u[i] = this.u[i][position];
		
		return new Column(u);
	}//getCol
	
	/* 
	 * Returns the row of the desired position as a row object.
	 */
	public Row getRow(int position) {
		float[] u = new float[numCols];
		
		for(int i = 0; i < numCols; i++)
			u[i] = this.u[position][i];
		
		return new Row(u);
	}//getRow
	
	/*
	 * Replaces the row in given position with given row object. 
	 */
	public void setRow(int position, Row newRow) {
		
		for(int i = 0; i < numCols; i++)
			this.setEntry(position, i, newRow.getEntry(i, -1));
	}//setRow
	
	//
	//Elementary row operations 
	//
	/*
	 * Swaps the rows in given the positions.
	 */
	public void swapRows(int position1, int position2) {
		
		//Store one of the rows to be swapped 
		float[] row = u[position1];
		
		u[position1] = u[position2];
		u[position2] = row;
	}//swapRows
	
	/*
	 * Multiplies row in given the position by a scalar.
	 */
	public void multiplyRow(int position, float scalar) {
		
		//Stores the row to be scaled 
		//*INEFICIENT*
		Row row = this.getRow(position);
		row.multiply(scalar);
		this.setRow(position, row);
	}//multiplyRow
	
	/*
	 * Adds a multiple of one row to another. 
	 */
	public void addRows(int destination, int source, float multiple) {
		for(int i = 0; i < numCols; i++)
			u[destination][i] += u[source][i]*multiple;
	}//addRows
	
	//
	//Solving systems of equations 
	//
	/*
	 * Solves the linear system that has this augmented matrix and returns the 
	 * solution as a string
	 */
	public String solveSystem() {
		
		//Put matrix into REF
		ArrayList<Integer> pivotCols = gaussianElim();
		
		
		//Check if system is consistent
		
		int row = numRows-1;//Initialize as last row
		int lastCol = numCols-1;//Index of the last col 
		boolean zeroRow = true;//All entries left of partition are zero
		
		do {
			
			for(int col = lastCol-1; col>=0; col--) 
				if(Math.abs(u[row][col]) > ZERO) {
					zeroRow = false;
					break;//*FIX THIS*
				}
			
			if(zeroRow&&Math.abs(u[row][lastCol]) > ZERO)
				return "System is inconsistent.";//*FIX THIS*
			
		}while(zeroRow&&--row>=0);

		
		//Put matrix into RREF
		backSub();
		
		
		//Now that the system is in RREF we know that there is a unique sol if 
		//the # of equations = # of unknowns => # non-zero rows = # cols -1
				
		if(numCols-1 > row+1)
			return parametericSol(pivotCols);//*FIX THIS*
		
		return uniqueSol();//*FIX THIS*
		
	}//solveSystem
	
	/*
	 * Formats and returns a string for systems that have infinitely many 
	 * solutions. 
	 */
	private String uniqueSol() {
		String s = ""; 
		for(int i = 0; i < numCols-1; i++)
			s+=String.format("X%d = %f\n", i+1,u[i][numCols-1]);
		return s;
	}

	/*
	 * Formulates and returns a string solution for systems that have 
	 * infinitely many solutions. 
	 * Accepts an ArrayList of pivot col indices for this system.      
	 */
	private String parametericSol(ArrayList<Integer> pivotCols) {
		
		String s = "";
		int pivotCount = 1;//Keeps track of pivot unknowns   
		int freeCount = 1;//Keeps track of free unknowns  
		
		for(int i = 0; i < numCols-1; i++) {
			if(pivotCols.contains(i)) {
				s += pivotFormat(pivotCount-1, i, pivotCols);
				pivotCount++;
			}
			else {
				s += String.format("X%d = p%d\n", i+1, freeCount++);
			}
		}
		
		return s;
		
	}//parametricSol
	
	/*
	 * Formats and returns a string for unknowns that have both parameters and 
	 * scalars. 
	 * 
	 * (This method is very messy because I wanted the output to look nice 
	 * and I couldn't think of a better way of doing this than using a bunch 
	 * of conditions.)
	 */
	private String pivotFormat(int row, int col, ArrayList<Integer> pivotCols) {
		
		int freeCount = 1;//Keeps track of free unknowns for naming
		boolean zero = true;
		
		String s ="X"+(col+1)+" = "; 
		
		//Check if RHS of partition is greater than zero
		if(Math.abs(u[row][numCols-1])>ZERO) {
			s += String.format("%f", u[row][numCols-1]);
			zero = false;
		}
		
		//Iterate through all cols to the right of the given col
		for(int i = col+1; i < numCols; i++) {
			
			//Check if pivot col
			if(!pivotCols.contains(i)) {
				
				//Check if non-zero
				if(Math.abs(u[row][i])>ZERO) {
					
					//Check for positive or negative 
					if(u[row][i]<0&&!zero) s+= "+";
					else if (u[row][i]>=0) s+= "-";
					
					zero = false;
					
					s += String.format("%fp%d", Math.abs(u[row][i]),freeCount);
				}
				
				s+=" ";
				freeCount++;
			}
		}//for
		
		//Check if row of zeros to fill blank 
		if(zero) s+= String.format("%f", u[row][numCols-1]);
		
		return s+"\n";
	}//pivotFormat

	/*
	 * Backwards phase: puts matrix into RREF.
	 */
	public void backSub() {
		
		int row = numRows;
		
		while(--row >= 0) {//Iterate through rows bottom up 
			
			
			int pivotCol = -1;
			
			//Find pivot col
			for(int j = 0; j < numCols-1; j++)//We don't care about the last col 
				if(Math.abs(u[row][j]) > ZERO) {
					pivotCol = j;
					break;//*FIX THIS*
				}
			
			if(pivotCol == -1) 
				continue;//*FIX THIS*
			
			//Add to rows above
			for(int j = row-1;j >=0; j--) 
				if(Math.abs(u[j][pivotCol])>ZERO)
					addRows(j, row, -1*u[j][pivotCol]);
			
		}
	}//BackSub
	
	/*
	 * Forward phase: puts matrix into REF.
	 * Returns a list of all pivot col indices for future reference.
	 */
	public ArrayList<Integer> gaussianElim() {
		
		//Stores the positions of the pivot cols
		ArrayList<Integer> pivotCols = new ArrayList<Integer>();
				
		int pivotRow = 0;
		int pivotCol = 0;
		
		while(pivotRow < numRows && pivotCol < numCols) {
			
			//Find the row below the pivot with the highest absolute value
			int maxPosition = getMaxPosition(pivotCol, pivotRow);
			
			if(Math.abs(u[maxPosition][pivotCol]) < ZERO) {//skip if zero
				pivotCol++;
				continue;//*FIX THIS*
			}else
				pivotCols.add(pivotCol);

			//Swap rows
			if(maxPosition != pivotRow) 
				swapRows(maxPosition, pivotRow);
			
			
			//Make leading 1
			multiplyRow(pivotRow, 1/u[pivotRow][pivotCol]);
			
			//Add to rows below
			for(int i = pivotRow+1; i < numRows; i++) 
				addRows(i, pivotRow, -1*u[i][pivotCol]);
			
			pivotRow++; pivotCol++;
			
		}//while
		
		return pivotCols;
	}//gaussianElim
	
	/*
	 * Finds and returns the position of the largest entry of the pivot col 
	 * below starting from the pivot row. 
	 */
	public int getMaxPosition(int pivotCol, int pivotRow) {
		
		int maxPosition = pivotRow;
		
		for(int i = pivotRow+1; i < numRows; i++)
			if(Math.abs(u[i][pivotCol]) > Math.abs(u[maxPosition][pivotCol]))
				maxPosition = i;
		
		return maxPosition;
	}//getMaxPosition
	
	//
	//Class Methods 
	//
	/*
	 * Gets a randomly sized matrix with random entries 
	 * as specified by max and min. 
	 */
	public static Matrix randomMatrix(int maxSize, 
			int maxValue, int minValue) {
		
		
		int numRows = (int)(Math.random()*maxSize)+1;
		int numCols = (int)(Math.random()*maxSize)+1;
		
		Matrix u = new Matrix(numRows, numCols);
		
		//Randomize entries
		u.randomizeInt(maxValue, minValue);
		
		return u;
	}//randomMatrix
	
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