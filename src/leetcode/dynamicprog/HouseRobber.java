/*
 * Given a list of non-negative integers representing the amount of money of each house, 
 * determine the maximum amount of money you can rob tonight without alerting the police
 * i.e. cannot rob two houes in in a row
 */
package leetcode.dynamicprog;

import java.util.Arrays;

public class HouseRobber {
    
    public int rob(int[] nums) {
        dp=new int[nums.length];
        Arrays.fill(dp,-1);
        return rob(nums,0);
    }
    int dp[];
    int rob(int[] nums, int at) {
        if (at>=nums.length)
            return 0;
        if (dp[at]>=0)
            return dp[at];
        int a1=nums[at]+rob(nums, at+2);
        int a2=rob(nums,at+1);
        dp[at] = Integer.max(a1,a2);
        return dp[at];
    }
    public static void main(String[] args)
    {
        System.out.println(new HouseRobber().rob(new int[]{1,1000,2,3,3000})==4000);
        System.out.println(new HouseRobber().rob(new int[]{1})==1);
        System.out.println(new HouseRobber().rob(new int[]{1,2})==2);
        System.out.println(new HouseRobber().rob(new int[]{1,2,0})==2);
    }
}
