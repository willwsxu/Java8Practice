package leetcode.dynamicprog;

import java.util.Arrays;

// In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those cells in the 
// given list mines which are 0. What is the largest axis-aligned plus sign of 1s contained in the grid?
// N will be an integer in the range [1, 500].
// mines will have length at most 5000.
// mines[i] will be length 2 and consist of integers in the range [0, N-1].
public class LargestPlusSign {
	static void print(int[][]g)
	{
		int N=g.length;
        for (int r=0; r<N; r++) {
        	for (int c=0; c<N; c++)
        		System.out.print(g[r][c]+" ");
        	System.out.println();
        }
        System.out.println();
	}
    static public int orderOfLargestPlusSign(int N, int[][] mines) {
    	int[][]grid=new int[N][N];
        int[][]sumFromLeft=new int[N][N];
        int[][]sumFromRight=new int[N][N];
        int[][]sumFromTop=new int[N][N];
        int[][]sumFromBottom=new int[N][N];
        for (int[]r: grid)
        	Arrays.fill(r,  1);
        for (int[] r: mines)
        	grid[r[0]][r[1]]=0;
        for (int r=0; r<N; r++) {
    		sumFromRight[r][N-1]=grid[r][N-1];
        	for (int c=N-2; c>=0; c--) {
        		if (grid[r][c]==0)
        			sumFromRight[r][c]=0;
        		else
        			sumFromRight[r][c] = sumFromRight[r][c+1]+grid[r][c];  // prefix sum from right
        	}      
        	sumFromLeft[r][0]=grid[r][0];  	
        	for (int c=1; c<N; c++) {
        		if (grid[r][c]==0)
        			sumFromLeft[r][c]=0;
        		else
        			sumFromLeft[r][c] = sumFromLeft[r][c-1]+grid[r][c];  // prefix sum from left        		
        	}
        }
        for (int c=0; c<N; c++) {
        	sumFromBottom[N-1][c] = grid[N-1][c];
        	for (int r=N-2; r>=0; r--) {
        		if (grid[r][c]==0)
        			sumFromBottom[r][c]=0;
        		else
        			sumFromBottom[r][c] = sumFromBottom[r+1][c]+grid[r][c];  // prefix sum from bottom up
        	}
        	sumFromTop[0][c] = grid[0][c];
        	for (int r=1; r<N; r++) {
        		if (grid[r][c]==0)
        			sumFromTop[r][c]=0;
        		else
        			sumFromTop[r][c] = sumFromTop[r-1][c]+grid[r][c];  // prefix sum from top down    		
        	}
        }
        //print(grid);
        //print(sumFromLeft);
        //print(sumFromRight);
        //print(sumFromTop);
        //print(sumFromBottom);
        int maxOrder=0;
        if (mines.length<N*N)  // at least 1 cell not 0
        	maxOrder=1;
        // update grid in place to compute order, no need to compute 2 outside rows and columns
        for (int r=1; r<N-1; r++) {
        	for (int c=1; c<N-1; c++) {
        		if (grid[r][c]==0)  // cannot form any sign
        			continue;
        		int order=Integer.min(sumFromTop[r][c], sumFromLeft[r][c]);
        		order = Integer.min(order, sumFromRight[r][c]);  // check max 1 to right
        		order = Integer.min(order, sumFromBottom[r][c]); // check max 1 to bottom
        		if (order>maxOrder)
        			maxOrder=order;
        		grid[r][c]=order;
        	}
        }	
        //print(grid);
        return maxOrder;
    }

    public static void main(String[] args)
    {
    	System.out.println(orderOfLargestPlusSign(5, new int[][] {{4,2}})==2);
    	System.out.println(orderOfLargestPlusSign(2, new int[][] {})==1);
    	System.out.println(orderOfLargestPlusSign(1, new int[][] {{0,0}})==0);

    	int[][]mines=new int[][]{{0,0},{0,1},{0,2},{0,7},{1,2},{1,3},{1,9},{2,3},{2,5},{2,7},{2,8},{3,2},{3,5},{3,7},{4,2},{4,3},{4,5},{4,7},{5,1},{5,4},{5,8},{5,9},{7,2},{7,5},{7,7},{7,8},{8,5},{8,8},{9,0},{9,1},{9,2},{9,8}};
    	System.out.println(orderOfLargestPlusSign(10, mines)==4);
    }
}
