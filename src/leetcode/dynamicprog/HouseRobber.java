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
    
    // nums in a circle
    public int rob2(int[] nums) {
        if (nums.length==0)
            return 0;
        if (nums.length==1)
            return nums[0];
        dp=new int[nums.length];
        Arrays.fill(dp,-1);
        
        int last=nums[nums.length-1];
        nums[nums.length-1]=0;
        int a1=nums[0]+rob(nums, 2);
        nums[nums.length-1]=last;
        Arrays.fill(dp,-1); // reset
        int a2=rob(nums,1);
        //System.out.println(a1+" "+a2);
        //System.out.println(Arrays.toString(nums));
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
