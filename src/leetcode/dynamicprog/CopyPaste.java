package leetcode.dynamicprog;

import java.util.Arrays;

// Initially on a notepad only one character 'A' is present. You can perform two operations for each step: 
//   Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
//   Paste: You can paste the characters which are copied last time.
// Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted
public class CopyPaste {
	int dp[][];
	int minSteps(int n, int copy, int total) {
		if (n==total)
			return 0;
		if (n<total || n<copy+total)  // copied too many
			return Integer.MAX_VALUE/2;
		if (dp[copy-1][total]>=0)
			return dp[copy-1][total];
		dp[copy-1][total]=1+minSteps(n, copy, total+copy); // paste
        if (total>copy) {
        	int s2=1+minSteps(n, total, total);    // copy all
        	dp[copy-1][total] = Integer.min(dp[copy-1][total], s2);
        }
        return dp[copy-1][total];
    }
    public int minSteps(int n) {
    	if (n<=1)
    		return 0;
    	dp=new int[n][n];
    	for (int []r: dp)
    		Arrays.fill(r, -1);
        return 1+minSteps(n, 1, 1); // must copy first time
    }
    public static void main(String[] args)
    {
    	int ans = new CopyPaste().minSteps(3);
    	System.out.println(ans==3);
    	
    	ans = new CopyPaste().minSteps(8);
    	System.out.println(ans);

    	ans = new CopyPaste().minSteps(1);
    	System.out.println(ans==0);
    }
}
