package leetcode.binarySearch;

import java.util.Arrays;

// Integers in each row are sorted in ascending from left to right.
// Integers in each column are sorted in ascending from top to bottom.
public class Matrix {
	boolean searchup(int[][] mat, int col, int low, int hi, int target)
	{
		if (low==hi)
			return mat[low][col]==target;
		int mid=(low+hi)/2;
		if ( mat[mid][col]==target)
			return true;
		else if ( mat[mid][col]>target)
			return searchup(mat, col, low, mid, target);
		else
			return searchup(mat, col, mid+1, hi, target);
	}
	private boolean binary(int[][] mat, int low, int hi, int target)  // search diagonal
	{
		if (low==hi) {  // target must be above or left of this point
			if (Arrays.binarySearch(mat[low], 0, low+1, target) >=0)
				return true;
			return searchup(mat, low, 0, low, target);
		}
		int mid=(low+hi)/2;
		if (mat[mid][mid]==target)
			return true;
		else if (mat[mid][mid]>target)
			return binary(mat, low, mid, target);
		else
			return binary(mat, mid+1, hi, target); // upper bound
	}
    public boolean searchMatrix(int[][] matrix, int target) {
        return binary(matrix, 0, matrix.length-1, target);
    }

    public static void main(String[] args)
    {
    	int[][] matrix =new int[][]{
    		  {1,   4,  7, 11, 15},
    		  {2,   5,  8, 12, 19},
    		  {3,   6,  9, 16, 22},
    		  {10, 13, 14, 17, 24},
    		  {18, 21, 23, 26, 30}
    		};
    	System.out.println(new Matrix().searchMatrix(matrix, 5));
    	System.out.println(new Matrix().searchMatrix(matrix, 20)==false);
    }
}
