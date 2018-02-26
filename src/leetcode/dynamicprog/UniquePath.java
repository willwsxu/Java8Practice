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
    public static void main(String[] args)
    {
        System.out.println(new UniquePath().uniquePaths(1,2));
        System.out.println(new UniquePath().uniquePaths(2,1));
        System.out.println(new UniquePath().uniquePaths(2,2));
        System.out.println(new UniquePath().uniquePaths(10,10));
    }
}
