/*
 * A robot is located at the top-left corner of a m x n grid
 * The robot can only move either down or right at any point in time. 
 * The robot is trying to reach the bottom-right corner of the grid
 **How many possible unique paths are there?
 */
package leetcode.dynamicprog;

import java.util.Arrays;

public class UniquePath {
    
    public int uniquePaths(int m, int n) {
        count =new int[m][n];
        for (int[] r: count)
            Arrays.fill(r, -1);
        return visit(m,n,1,1);
    }
    int count[][];
    private int visit(int m, int n, int i, int j) {
        if (i==m && j==n) {
            return 1;
        }
        if (count[i-1][j-1]>=0)
            return count[i-1][j-1];
        int path=0;
        if (i<m)
            path +=visit(m,n, i+1,j);
        if (j<n)
            path +=visit(m,n,i,j+1);
        count[i-1][j-1]=path;
        return path;
    }
    
    private int visit(int[][] obstacleGrid, int i, int j) {
        int m=obstacleGrid.length-1;
        int n=obstacleGrid[0].length-1;
        if (i==m && j==n) {
            return obstacleGrid[i][j]>0?0:1;
        }
        if (obstacleGrid[i][j]>0)
            return 0;
        if (count[i][j]>=0)
            return count[i][j];
        int path=0;
        if (i<m)
            path +=visit(obstacleGrid, i+1,j);
        if (j<n)
            path +=visit(obstacleGrid,i,j+1);
        count[i][j]=path;
        return path;
    }
    // An obstacle and empty space is marked as 1 and 0 respectively in the grid
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        count =new int[m][n];
        for (int[] r: count)
            Arrays.fill(r, -1);
        return visit(obstacleGrid,0,0);        
    }
    public static void main(String[] args)
    {
        System.out.println(new UniquePath().uniquePaths(1,2)==1);
        System.out.println(new UniquePath().uniquePaths(2,1)==1);
        System.out.println(new UniquePath().uniquePaths(2,2)==2);
        System.out.println(new UniquePath().uniquePaths(10,10)==48620);
        
        int grid[][]=new int[3][3];
        for (int[] r: grid)
            Arrays.fill(r, 0);
        grid[1][1]=1;
        System.out.println(new UniquePath().uniquePathsWithObstacles(grid)==2);
        
        grid[0][0]=1;
        System.out.println(new UniquePath().uniquePathsWithObstacles(grid)==0);
        
        grid[0][0]=0;
        grid[2][2]=1;
        System.out.println(new UniquePath().uniquePathsWithObstacles(grid)==0);
    }
}
