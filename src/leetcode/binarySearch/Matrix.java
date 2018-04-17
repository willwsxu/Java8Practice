package leetcode.binarySearch;

import java.util.Arrays;

// Integers in each row are sorted in ascending from left to right.
// Integers in each column are sorted in ascending from top to bottom.
public class Matrix {
	boolean searchup(int[][] mat, int col, int low, int hi, int target)
	{
		int idx=searchupIndex(mat, col, low, hi, target);
		return mat[idx][col]==target;
	}
	int searchupIndex(int[][] mat, int col, int low, int hi, int target)
	{
		if (low==hi)
			return low;
		int mid=(low+hi)/2;
		if ( mat[mid][col]==target)
			return mid;
		else if ( mat[mid][col]>target)
			return searchupIndex(mat, col, low, mid, target);
		else
			return searchupIndex(mat, col, mid+1, hi, target);
	}
	private boolean binary(int[][] mat, int lowR, int lowC, int hiR, int hiC, int target)  // search diagonal
	{
		if (lowR>=hiR && lowC>=hiC) {  // target must be above or left of this point
			System.out.println("pivot "+lowR+" "+lowC);
			if (Arrays.binarySearch(mat[hiR], 0, hiC+1, target) >=0)
				return true;
			return searchup(mat, hiC, 0, hiR, target);
		}
		int midR=(lowR+hiR)/2;
		int midC=(lowC+hiC)/2;
		if (mat[midR][midC]==target)
			return true;
		else if (mat[midR][midC]>target)
			return binary(mat, lowR, lowC, midR, midC, target);
		else {
			//if (hiR-midR>hiC-midC)
				midR++;
			//else 
				midC++;
			return binary(mat, midR, midC, hiR, hiC, target); // upper bound
		}
	}
	private boolean binary(int[][] mat, int low, int hi, int target)  // search square diagonal
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
    	int m=matrix.length;
    	if (m==0)
    		return false;
    	int n=matrix[0].length;
    	if (n==0)
    		return false;
    	int top=0, left=0;
    	int bottom=m-1, right=n-1;
    	while (top<=bottom && left<=right) {
	    	top=searchupIndex(matrix, right, top, bottom, target);
	    	if (matrix[top][right]==target)
	    		return true;
	    	if (top<bottom)
	    		bottom=searchupIndex(matrix, left, top, bottom, target);
	    	if (matrix[bottom][left]==target)
	    		return true;
	    	//System.out.println("top "+top+" bot "+bottom+" left "+left+" right "+right);
	    	if (bottom>top)
	    		bottom--;
	    	int l=Arrays.binarySearch(matrix[bottom], left, right+1, target);
	    	//System.out.println(l);
	    	if (l>=0)
	    		return true;
	    	left=-l-1;
	    	int r=Arrays.binarySearch(matrix[top], left, right+1, target);
	    	if (r>=0)
	    		return true;
	    	right=-r-1;
	    	//System.out.println("top "+top+" bot "+bottom+" left "+left+" right "+right);
	    	if (right>left)
	    		right--;
	    	if (left==right&& top==bottom)
	    		return false;
    	}
        return false;
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
        System.out.println(new Matrix().searchMatrix(new int[][]{{1,3,5}}, 3));
        System.out.println(new Matrix().searchMatrix(new int[][]{{1},{3},{5}}, 3));
    	System.out.println(new Matrix().searchMatrix(matrix, 5));
    	System.out.println(new Matrix().searchMatrix(matrix, 20)==false);
    	System.out.println(new Matrix().searchMatrix(new int[][] {{}}, 20)==false);
    	System.out.println(new Matrix().searchMatrix(new int[][] {{20}}, 20));
    	System.out.println(new Matrix().searchMatrix(new int[][] {{20}}, 2)==false);
    	System.out.println(new Matrix().searchMatrix(new int[][] {{20}}, 40)==false);
    	System.out.println(new Matrix().searchMatrix(new int[][] {{20,30, 35, 36,37}}, 40)==false);
    	System.out.println(new Matrix().searchMatrix(new int[][] {{1,4},{2,5}}, 2));
    	System.out.println(new Matrix().searchMatrix(new int[][] {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}}, 15));
    	
    }
}
