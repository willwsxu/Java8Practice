/*
 * Given array of strings, find the maximum number of strings that you can form with given m 0s and n 1s
 */
package leetcode;

import java.util.Arrays;

public class OnesZeros {
    int dp[][][];
    int findMaxForm(String[] strs, int idx, int zeroes, int ones) {
        if (zeroes<0 ||ones<0)
            return Integer.MIN_VALUE;
        if (idx==strs.length)
            return 0;
        if (dp[idx][zeroes][ones]>=0)
            return dp[idx][zeroes][ones];
        int notake=findMaxForm(strs, idx+1, zeroes, ones);
        int new1=ones;
        int new0=zeroes;
        for (int i=0; i<strs[idx].length(); i++) {
            if (strs[idx].charAt(i)=='1')
                new1--;
            else
                new0--;
        }
        int take=1+findMaxForm(strs, idx+1, new0, new1);
        dp[idx][zeroes][ones] = Integer.max(take, notake);
        return dp[idx][zeroes][ones];
    }
    public int findMaxForm(String[] strs, int m, int n) {
        dp=new int[strs.length][m+1][n+1];
        for (int[][] rc: dp)
            for (int[] r: rc)
                Arrays.fill(r, -1);
        return findMaxForm(strs, 0, m, n);
    }
    
    public static void main(String[] args)
    {
        System.out.println(new OnesZeros().findMaxForm(new String[]{"10", "0", "1"}, 1,1)==2);
        System.out.println(new OnesZeros().findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5,3)==4);
    }
}
