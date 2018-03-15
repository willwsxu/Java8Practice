/*
 * Given array of strings, find the maximum number of strings that you can form with given m 0s and n 1s
 */
package leetcode.dynamicprog;

import java.util.Arrays;

public class KnapsackDp {
    int dp[][][];
    int ones[];
    int zeroes[];
    private int findMaxForm(String[] strs, int idx, int zeroes, int ones) {
        if (zeroes<0 ||ones<0)
            return Integer.MIN_VALUE;
        if (idx==strs.length)
            return 0;
        if (dp[idx][zeroes][ones]>=0)
            return dp[idx][zeroes][ones];
        int notake=findMaxForm(strs, idx+1, zeroes, ones);
        int take=1+findMaxForm(strs, idx+1, zeroes-this.zeroes[idx], ones-this.ones[idx]);
        dp[idx][zeroes][ones] = Integer.max(take, notake);
        return dp[idx][zeroes][ones];
    }
    public int findMaxForm_topdown(String[] strs, int m, int n) { // topdown dp
        dp=new int[strs.length][m+1][n+1];
        for (int[][] rc: dp)
            for (int[] r: rc)
                Arrays.fill(r, -1);
        ones=new int[strs.length];
        zeroes=new int[strs.length];
        for (int i=0; i<strs.length; i++) {
            for (int j=0; j<strs[i].length(); j++) {
                if (strs[i].charAt(j)=='1')
                    ones[i]++;
                else
                    zeroes[i]++;
            }            
        }
        return findMaxForm(strs, 0, m, n);
    }

    public int findMaxForm(String[] strs, int m, int n) { // bottom up
        dp=new int[strs.length][m+1][n+1];
        for (int[][] rc: dp)
            for (int[] r: rc)
                Arrays.fill(r, -1);
        ones=new int[strs.length];
        zeroes=new int[strs.length];
        for (int i=0; i<strs.length; i++) {
            for (int j=0; j<strs[i].length(); j++) {
                if (strs[i].charAt(j)=='1')
                    ones[i]++;
                else
                    zeroes[i]++;
            }            
        }
        return findMaxForm(strs, 0, m, n);
    }
    public static void main(String[] args)
    {
        System.out.println(new KnapsackDp().findMaxForm(new String[]{"10", "0", "1"}, 1,1)==2);
        System.out.println(new KnapsackDp().findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5,3)==4);
    }
}
