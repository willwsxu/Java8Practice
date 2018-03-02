
package leetcode.dynamicprog;

public class MatrixDP {
    // Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
    static public int maximalSquare(char[][] matrix) {
        if (matrix.length==0)
            return 0;
        int maxDim=0;
        int dp[][]=new int[matrix.length][matrix[0].length]; // store max size square with right bottom coner at (i,j)
        for (int i=0; i<matrix.length; i++) { // fill first column
            dp[i][0]=matrix[i][0]-'0';
            if (dp[i][0]>maxDim)
                maxDim = dp[i][0];
        }
        
        for (int j=0; j<matrix[0].length; j++) { // fill first row
            dp[0][j]=matrix[0][j]-'0';
            if (dp[0][j]>maxDim)
                maxDim = dp[0][j];
        }
        for (int i=1; i<matrix.length; i++) {
            for (int j=1; j<matrix[0].length; j++) {
                if ( matrix[i][j]=='0')
                    dp[i][j]=0;
                else {
                    dp[i][j] = Integer.min(dp[i-1][j], dp[i][j-1]);
                    dp[i][j] = Integer.min(dp[i][j], dp[i-1][j-1])+1;
                    if (dp[i][j]>maxDim)
                        maxDim=dp[i][j];
                }
            }
        }
        return maxDim*maxDim;
    }
    
    public static void main(String[] args)
    {
        char[][]m=new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(maximalSquare(m));
    }
}
