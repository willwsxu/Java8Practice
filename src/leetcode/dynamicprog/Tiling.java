package leetcode.dynamicprog;

import java.util.Arrays;

public class Tiling {
    public int numTilings_formula(int N) {
    	if (N<3)
    		return N;
        int dp[]=new int[N+1];
        dp[0]=0;
        dp[1]=1; // n=1, one way
        dp[2]=2;
        dp[3]=dp[2]+dp[1]+2; // last 2 using tromino
        // fill backwards
        for (int n=4; n<=N; n++) {
        	dp[n]=((2*dp[n-1])%MOD+dp[n-3])%MOD;
        }
        return dp[N];
    }

    public int numTilings_bottomup(int N) {
    	if (N<3)
    		return N;
        int dp[]=new int[N+1];  // flat end
        dp[0]=0;
        dp[1]=1; // n=1, one way
        dp[2]=2;
        int dp2[]=new int[N+1];   // tromino end
        dp2[0]=0;
        dp2[1]=0;
        dp2[2]=2;
        // fill backwards
        for (int n=3; n<=N; n++) {
        	dp[n]=(dp[n-1]+dp[n-2])%MOD;   // domino to domino
        	dp[n] = (dp[n]+dp2[n-1])%MOD;  // tromino+tromino
        	dp2[n]=(2*dp[n-2])%MOD;        // domino + tromino
        	dp2[n]=(dp2[n]+dp2[n-1])%MOD; // tromino + domino extend tromino
        }
        return dp[N];
    }
    int dp[][];
	final int MOD=1000000007;
    int numTilings(int N, int cur, int profile) { // profile for two possible end pattern
    	if (N-cur<=1) {  // one column left
    		if (profile==0) // flat end
    			return 1;
    		return 0;		// tromino end
    	}
    	if (dp[profile][cur]>=0)
    		return dp[profile][cur];
    	int count = 0;
    	if (profile==0) {
    		count += numTilings(N, cur+1, 0);
    		count += numTilings(N, cur+2, 0);
    		count %= MOD;
    		count += (2 * numTilings(N, cur+1, 1))%MOD;
    	} else
    	{
    		count += numTilings(N, cur+2, 0);  // fill opposite tromino to become flat
    		count += numTilings(N, cur+1, 1);  // extend tromino with a domino
    	}
		count %= MOD;
    	dp[profile][cur]=count;
    	return count;
    }
    public int numTilings(int N) {
    	if (N==0)
    		return 0;
    	dp=new int[2][N];
    	for (int[]r : dp)
    		Arrays.fill(r, -1);
    	return numTilings(N, 0, 0);
    }

    // bottom up beats 62%, topdown beats 46%
    public static void main(String[] args)
    {
    	System.out.println(new Tiling().numTilings_bottomup(0)==0);
    	System.out.println(new Tiling().numTilings_bottomup(1)==1);
    	System.out.println(new Tiling().numTilings_bottomup(4)==11);
    	System.out.println(new Tiling().numTilings_bottomup(5)==24);
    	System.out.println(new Tiling().numTilings_bottomup(100)==190242381);
    }
}
