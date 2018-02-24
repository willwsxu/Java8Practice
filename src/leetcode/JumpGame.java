/*
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 */
package leetcode;

public class JumpGame implements Runnable {   
    int[]dp;
    boolean jump(int[] nums, int pos) {
        if (pos==nums.length-1)
            return true;
        if (pos>=nums.length)
            return false;
        if ( dp[pos]!=0 )    // check if this position is aready computed
            return dp[pos]==1;
        int ans=-1;
        for (int i=nums[pos]; i>=1; i--) {
            if (jump(nums, i+pos)) {
                ans=1;  // succeed
                break;
            }
        }
        dp[pos]=ans;
        return ans==1;        
    }
    boolean ans;
    int[]nums;
    public boolean canJump(int[] nums) {
        this.nums=nums;
        dp=new int[nums.length]; // 0 initial state, -1 failed, 1 succeed
        Thread t =new Thread(null, this, "big callstack", 1<<28);
        t.start();
        try {
            t.join();
        } catch (Exception e)
        {
            System.out.println(e);
        }        
        return ans;
    }
    
    public void run()
    {
        ans=jump(nums, 0);
    }
    public static void main(String[] args)
    {
        System.out.println(new JumpGame().canJump(new int[]{2,3,1,1,4}));
        System.out.println(new JumpGame().canJump(new int[]{3,2,1,0,4}));
        System.out.println(new JumpGame().canJump(new int[]{2,0}));
    }    
}
