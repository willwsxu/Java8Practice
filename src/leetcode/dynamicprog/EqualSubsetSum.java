/*
 * Given a non-empty array containing only positive integers, find if the array 
 * can be partitioned into two subsets such that the sum of elements in both subsets is equal
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 */
package leetcode.dynamicprog;

import java.util.Arrays;

public class EqualSubsetSum {
    int memo[][];
    boolean targetSum(int[] nums, int pos, int sum, int target) {
        if (sum==target)
            return true;
        if (pos==nums.length || sum>target)
            return false;
        if (memo[pos][sum]>=0)
            return memo[pos][sum]>0;
        if (targetSum(nums, pos+1, sum, target)) {  // no take
            memo[pos][sum]=1;
            return true;
        }
        
        if (targetSum(nums, pos+1, sum+nums[pos], target)) { // take
            memo[pos][sum]=1;
            return true;
        }
        memo[pos][sum]=0;
        return false;
    }
    public boolean canPartition_topdown(int[] nums) {  // similar to target sum in Knapsack
        int sum=0;
        for (int n: nums)
            sum += n;
        if (sum%2>0)
            return false;
        memo = new int[nums.length][sum/2];
        for (int r[]: memo)
            Arrays.fill(r, -1);
        return targetSum(nums, 0, 0, sum/2);
    }
    public boolean canPartition_v1(int[] nums) {  // bottom up
        int sum=0;
        for (int n: nums)
            sum += n;
        if (sum%2>0)
            return false;
        sum /= 2;
        boolean dp[][] = new boolean[nums.length+1][sum+1];
        Arrays.fill(dp[0], false);  // any sum for  no number
        dp[0][0]=true;
        for (int i=1; i<=nums.length; i++) {
            dp[i][0]=true; // true if sum=0
            for (int j=1; j<=sum; j++) {
                dp[i][j] = dp[i-1][j]; // no take ith nums
                if (j>=nums[i-1]) // take ith nums
                    dp[i][j] = dp[i][j] || dp[i][j-nums[i-1]];
            }
        }
        return dp[nums.length][sum];
    }
    public boolean canPartition(int[] nums) {  // bottom up, optimization, beat 89%, twice better than top down
        int sum=0;
        for (int n: nums)
            sum += n;
        if (sum%2>0)
            return false;
        sum /= 2;
        boolean dp[] = new boolean[sum+1];
        Arrays.fill(dp, false);  // any sum for  no number
        dp[0]=true;
        for (int num : nums) {
            for (int j=sum; j>0; j--) {  // backwards so new dp is not affect previous values
                if (j>=num) // take ith nums, or not take
                    dp[j] = dp[j] || dp[j-num];
            }
            if (dp[sum])  // skip rest of numbers
                return true;
        }
        return dp[sum];
    }
    public static void main(String[] args)
    {
        System.out.println(new EqualSubsetSum().canPartition(new int[]{1, 5, 11, 5})==true);
        System.out.println(new EqualSubsetSum().canPartition(new int[]{1,2,3,5})==false);
    }
}
