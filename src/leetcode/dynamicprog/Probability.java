package leetcode.dynamicprog;

import java.util.Arrays;

// On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves
// Return the probability that the knight remains on the board after it has stopped moving.
// N will be between 1 and 25.
// K will be between 0 and 100.
public class Probability {
	int moves[][]=new int[][]{{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1},{-1,2}};
	double dp[][][]=new double[101][25][25];
	{
		for (double[][]rc: dp)
			for (double[]r: rc)
				Arrays.fill(r,  -1);
	}
    public double knightProbability(int N, int K, int r, int c) {
        if (r<0 || c<0 || r>=N || c>=N)
        	return 0;
        if (K==0)
        	return 1;
        if (dp[K][r][c]>=0)
        	return dp[K][r][c];
        double ans=0;
        for (int m=0; m<8;m++)
        	ans += knightProbability(N, K-1, r+moves[m][0], c+moves[m][1]);
        dp[K][r][c] = ans/8;
        return dp[K][r][c];
    }

    public static void main(String[] args)
    {
    	System.out.println(new Probability().knightProbability(3,2,0,0));
    	System.out.println(new Probability().knightProbability(8,30,6,4));
    }
}
