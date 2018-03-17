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
    public int findMaxForm_topdown(String[] strs, int m, int n) { // topdown dp, beat 40%
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

    public int findMaxForm(String[] strs, int m, int n) { // bottom up beat 80%
        int memo[][]=new int[m+1][n+1];
        for (int[] r: memo)
            Arrays.fill(r, 0);
        for (int i=0; i<strs.length; i++) {
        	int ones=0;
        	int zero=0;
            for (int j=0; j<strs[i].length(); j++) {
                if (strs[i].charAt(j)=='1')
                    ones++;
                else
                	zero++;
            }
            for (int j=m; j>=zero; j--) {
            	for (int k=n; k>=ones; k--) {
            		memo[j][k]=Integer.max(memo[j][k], 1+memo[j-zero][k-ones]);
            	}
            }
        }
        return memo[m][n];
    }
    
    // target sum, 
    // You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. 
    // Now you have 2 symbols + and -. For each integer, you should choose one from + and - 
    // as its new symbol. 
    // Find out how many ways to assign symbols to make sum of integers equal to target S.
    int memo[][];
    int sum=0;
    int target;
    int findTargetSumWays(int[] nums, int idx,  int S) {
        if (idx==nums.length) {
        	return S==target?1:0;
        }
        if (memo[idx][S+sum]>=0)
        	return memo[idx][S+sum];
        int count=0;
        count += findTargetSumWays(nums, idx+1, S-nums[idx]);
        count += findTargetSumWays(nums, idx+1, S+nums[idx]);
        memo[idx][S+sum]=count;
        return count;
    }
    public int findTargetSumWays(int[] nums, int S) {
    	for (int v: nums)
    		sum +=v;
    	if (S<-sum || S>sum)
    		return 0;
    	//if (S==sum || S==-sum)
    	//	return 1;
    	target=S;
    	memo=new int[nums.length][2*sum+1]; // [-sum,sum]
    	for (int[]r: memo)
    		Arrays.fill(r, -1);
        int ans= findTargetSumWays(nums, 0, 0);
        //for (int []r : memo)
        //	System.out.println(Arrays.toString(r));
        return ans;
    }
    
    public static void main(String[] args)
    {
        System.out.println(new KnapsackDp().findMaxForm(new String[]{"10", "0", "1"}, 1,1)==2);
        System.out.println(new KnapsackDp().findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5,3)==4);
        
        System.out.println(new KnapsackDp().findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3)==5);
        System.out.println(new KnapsackDp().findTargetSumWays(new int[]{1, 0}, 1)==2);
    }
}
