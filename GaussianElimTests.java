import matrices.Matrix;

public class GaussianElimTests {

	public static void main(String[] args) {
		
		//Odd exercises from text book 

		//Q5
		float[][] a5 = {{1,1,2,8},
						{-1,-2,3,1},
						{3,-7,4,10}};
		
		Matrix u5 = new Matrix(a5);
		Matrix u5Clone = u5.clone();
		System.out.println("u5:\n"+u5);
		System.out.println();
		
		u5Clone.gaussianElim();
		System.out.println("Gaussian Elimination!");
		System.out.println();
		
		System.out.println("u5Clone:\n"+u5Clone);
		System.out.println();
		
		u5Clone.backSub();
		System.out.println("Back Sub!");
		System.out.println();
		
		System.out.println("u5Clone:\n"+u5Clone);
		System.out.println();
		
		System.out.println("u5:\n"+u5);
		System.out.println();

		System.out.println(u5.solveSystem());
		
		
		//7
		float[][] a7 = {{1,-1,2,-1,-1},
				  		{2,1,-2,-2,-2},
				  		{-1,2,-4,1,1},
				  		{3,0,0,-3,-3}};

		Matrix u7 = new Matrix(a7);
		System.out.println("u7:\n"+u7);
		System.out.println();
		
		u7.gaussianElim();
		System.out.println("Gaussian Elimination!");
		System.out.println();
		
		System.out.println("u7:\n"+u7);
		System.out.println();
		
		u7.backSub();
		System.out.println("Back Sub!");
		System.out.println();
		
		System.out.println("u7:\n"+u7);
		System.out.println();
		
		System.out.println(u7.solveSystem());
		
		//15
		float[][] a15 = {{2,1,3,0}, 
						{1,2,0,0}, 
						{0,1,1,0}}; 
		
		Matrix u15 = new Matrix(a15);
		System.out.println("u15:\n"+u15);
		System.out.println();
		
		u15.gaussianElim();
		System.out.println("Gaussian Elimination!");
		System.out.println();
		
		System.out.println("u15:\n"+u15);
		System.out.println();
		
		u15.backSub();
		System.out.println("Back Sub!");
		System.out.println();
		
		System.out.println("u15:\n"+u15);
		System.out.println();
		
		System.out.println(u15.solveSystem());
		
		
		//17
		float[][] a17 = {{3,1,1,1,0}, 
						{5,-1,1,-1,0}};
				
		Matrix u17 = new Matrix(a17);
		System.out.println("u17:\n"+u17);
		System.out.println();
		
		u17.gaussianElim();
		System.out.println("Gaussian Elimination!");
		System.out.println();
		
		System.out.println("u17:\n"+u17);
		System.out.println();

		u17.backSub();
		System.out.println("Back Sub!");
		System.out.println();
		
		System.out.println("u17:\n"+u17);
		System.out.println();
		
		System.out.println(u17.solveSystem());
		
		//19
		float[][] a19 = {{0,2,2,4,0},
						{1,0,-1,-3,0},
						{2,3,1,1,0},
						{-2,1,3,-2,0}};
		
		Matrix u19 = new Matrix(a19);
		System.out.println("u19:\n"+u19);
		System.out.println();
		
		u19.gaussianElim();
		System.out.println("Gaussian Elimination!");
		System.out.println();
		
		System.out.println("u19:\n"+u19);
		System.out.println();

		u19.backSub();
		System.out.println("Back Sub!");
		System.out.println();
		
		System.out.println("u19:\n"+u19);
		System.out.println();
		
		System.out.println(u19.solveSystem());
		
		
		//21
		float[][] a21 = {{2,-1,3,4,9},
						{1,0,-2,7,11},
						{3,-3,1,5,8},
						{2,1,4,4,10}};
		
		Matrix u21 = new Matrix(a21);
		System.out.println("u21:\n"+u21);
		System.out.println();
		
		u21.gaussianElim();
		System.out.println("Gaussian Elimination!");
		System.out.println();
		
		System.out.println("u21:\n"+u21);
		System.out.println();

		u21.backSub();
		System.out.println("Back Sub!");
		System.out.println();
		
		System.out.println("u21:\n"+u21);
		System.out.println();
		
		System.out.println(u21.solveSystem());
		
		//Example from geeks for geeks 
		float[][] a = { { 3, 2, -4, 3},
                { 2, 3, 3, 15},
                { 5,-3,1,14}};
		
		Matrix u = new Matrix(a);
		System.out.println("u:\n"+u);
		System.out.println();
		
		u.gaussianElim();
		System.out.println("Gaussian Elimination!");
		System.out.println();
		
		System.out.println("u:\n"+u);
		System.out.println();
		
		u.backSub();
		System.out.println("Back Sub!");
		System.out.println();
		
		System.out.println("u:\n"+u);
		System.out.println();
		
		System.out.println(u.solveSystem());
		
		System.out.println("u:\n"+u);
		System.out.println();

		
		//Inconsistent example 
		float[][] inconsistent ={{1,0,0,0},
                				{0,0,1,15},
                				{0,0,0,14}};
		
		Matrix i = new Matrix(inconsistent);
		System.out.println("i:\n"+i);
		System.out.println();
		System.out.println(i.solveSystem());
		System.out.println("i:\n"+i);
		System.out.println();
				
	}
}
