
package leetcode.dynamicprog;

import java.util.Arrays;

public class MatrixDP {
    // Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
    static public int maximalSquare_v1(char[][] matrix) {
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
    static public int maximalSquare(char[][] matrix) { // beats 96%
        if (matrix.length==0)
            return 0;
        int maxDim=0;
        // dp state is for previous row
        int dp[]=new int[matrix[0].length]; // O(n) memory for dp state
        
        for (int j=0; j<matrix[0].length; j++) { // fill first row
            dp[j]=matrix[0][j]-'0';
            if (dp[j]>maxDim)
                maxDim = dp[j];
        }
        for (int i=1; i<matrix.length; i++) {
            int left=matrix[i][0]-'0';
            if (left>maxDim)
                maxDim = left;
            for (int j=1; j<matrix[i].length; j++) {
                int current=0;
                if ( matrix[i][j]!='0') {
                    current = Integer.min(left, dp[j-1]);
                    current = Integer.min(current, dp[j])+1;
                    if (current>maxDim)
                        maxDim=current;
                }
                dp[j-1]=left;
                left=current;
            }
            dp[matrix[0].length-1]=left;
        }
        return maxDim*maxDim;
    }    
    

    // Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
    static public int findLength(int[] A, int[] B) {
    	int m=A.length;
    	int n=B.length;
    	//int dp[][]=new int[m+1][n+1];  // dp[i][j] is the matching subarray ends at A[i+1,B[j+1]
    	int dp[]=new int[n+1];  // only need to value of last row, beat 99%
    	Arrays.fill(dp, 0);
    	int maxLen=0;
    	for (int i=1; i<=m; i++) {
    		int prev=0;
        	int current=0;  // to store current
    		for (int j=1; j<=n ; j++) {
    			if (A[i-1]==B[j-1]) {
    				current =1+dp[j-1];
    				if ( current>maxLen)
    					maxLen=current;
    			}
    			else
    				current=0;
    			dp[j-1]=prev; // now prev dp can be updated
    			prev=current; // save current as prev
    		}
    		dp[n]=current;    		
    	}
    	return maxLen;
    }
    
    static void maxSquare()
    {
        char[][]m=new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(maximalSquare(m)==4);
        m=new char[][]{{'0','0','0','0','0'},{'1','0','0','0','0'},{'0','0','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(maximalSquare(m)==1);    	
    }
    
    public static void main(String[] args)
    {
    	int A[]=new int[] {1,2,3,2,1};
    	int B[]=new int[] {3,2,1,4,7};
    	System.out.println(findLength(A, B)==3);     	

    	int A2[]=new int[] {0,1,1,1,1};
    	int B2[]=new int[] {1,0,1,0,1};
    	System.out.println(findLength(A2, B2)==2); 
    }
}
