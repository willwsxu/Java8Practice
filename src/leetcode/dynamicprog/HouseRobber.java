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
        return rob(nums,0,nums.length-1);
    }
    int dp[];
    int rob(int[] nums, int at, int end) {
        if (at>end)
            return 0;
        if (dp[at]>=0)
            return dp[at];
        int a1=nums[at]+rob(nums, at+2, end);
        int a2=rob(nums,at+1, end);
        dp[at] = Integer.max(a1,a2);
        return dp[at];
    }
    
    // nums in a circle
    public int rob2(int[] nums) {
        if (nums.length==0)
            return 0;
        if (nums.length==1)
            return nums[0];
        dp=new int[nums.length];
        Arrays.fill(dp,-1);
        
        int a1=rob(nums, 0, nums.length-2);// step 1, take first, not last
        Arrays.fill(dp,-1); // reset
        int a2=rob(nums,1, nums.length-1);// step 2, not take first
        return Integer.max(a1, a2);
    }
    public static void main(String[] args)
    {
        System.out.println(new HouseRobber().rob(new int[]{1,1000,2,3,3000})==4000);
        System.out.println(new HouseRobber().rob(new int[]{1000,2,3,3000})==4000);
        System.out.println(new HouseRobber().rob(new int[]{1})==1);
        System.out.println(new HouseRobber().rob(new int[]{1,2})==2);
        System.out.println(new HouseRobber().rob(new int[]{1,2,0})==2);
        
        System.out.println(new HouseRobber().rob2(new int[]{1,1000,2,3,3000})==4000);
        System.out.println(new HouseRobber().rob2(new int[]{1000,1,2,3,3000})==3002);
        System.out.println(new HouseRobber().rob2(new int[]{})==0);
        System.out.println(new HouseRobber().rob2(new int[]{1})==1);
    }
}
