import matrices.Column;
import matrices.Matrix;
import matrices.Row;
import matrices.Vector;

public class Tests {

	public static void main(String[] args) { 

		//Matrix addition
		Matrix u = new Matrix(3,3);
		u.randomizeInt(0, 5);
		Matrix v = new Matrix(3,3);
		v.randomizeInt(0, 5);
		Matrix w = null;
		
		try {
			w = Matrix.add(u, v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Matrices:");
		System.out.println("u:");
		System.out.println(u);
		System.out.println("v:");
		System.out.println(v);
		System.out.println("Addition (u+v):");
		System.out.println(w);
		
		//Dot Product
		Column u2 = new Column(3);
		u2.randomizeInt(-5, 5);
		Row v2 = new Row(3);
		v2.randomizeInt(-5, 5);
		
		System.out.println("Vectors:");
		System.out.println("u2:");
		System.out.println(u2);
		System.out.println("v2:");
		System.out.println(v2);
		System.out.println("Dot Product (u2*v2): "+Vector.dotProduct(u2, v2));
		System.out.println("\n");

		//multiply
		Matrix u4 = new Matrix(3,3);
		u4.randomizeInt(0, 3);
		Matrix v4 = new Matrix(3,2);
		v4.randomizeInt(0, 3);
		Matrix w4 = Matrix.multiply(u4, v4);
		
		System.out.println("Multiply Matrices:");
		System.out.println("u4:");
		System.out.println(u4);
		System.out.println("v4:");
		System.out.println(v4);
		System.out.println("u4*v4:");
		System.out.println(w4);
		
		
		//Set new row 
		Row newRow = new Row(3);
		newRow.randomizeInt(0, 3);
		
		Matrix u5 = new Matrix(2,3);
		u5.randomizeInt(0, 3);
		
		System.out.println("Set new row:");
		System.out.println("u5:\n"+u5);
		System.out.println("New row:\n"+newRow);
		u5.setRow(0, newRow);
		System.out.println("Put new row in first position:\nu5:\n"+u5);
		
		//Swap rows
		u5.swapRows(0, 1);
		System.out.println("Swap rows 1 and 2:");
		System.out.println("u5:\n"+u5);
		
		//Multiply row by a scalar
		u5.multiplyRow(0, 2);
		System.out.println("Multiply row 1 by 2:");
		System.out.println("u5:\n"+u5);
		
		u5.addRows(1, 0, 2);
		System.out.println("Add 2x row 1 to row 2:");
		System.out.println("u5:\n"+u5);		
		
	}//main
}
