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
        if ( nums[pos]==0 ) {
            dp[pos]=-1;
            return false;
        }
        boolean ans=jump(nums, pos+1);
        if (ans) {
            dp[pos]=1;
            return true;            
        }
        int end=1;
        if (pos<nums.length-1 )  //prune postion that pos+1 can reach
            end = nums[pos+1]+1;
        for (int i=nums[pos]; i>=end; i--) {
            if (jump(nums, i+pos)) {
                ans=true;  // succeed
                break;
            }
        }
        if (ans)
            dp[pos]=1;
        else 
            dp[pos]=-11;
        return ans;        
    }
    boolean ans;
    int[]nums;
    public boolean canJump_dp(int[] nums) {
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
    
    
    public boolean canJump(int[] nums) {  //O(n)
        int maxReach=0; // initial reachable index
        int n=nums.length-1;
        if (n<=0)
            return true;
        for (int i=0; i<=maxReach; i++) {
            int next=i+nums[i];
            if (next>maxReach) { // faster than using max, beats 89%
                maxReach=next; // furtherest you can jump to up to position i
                if (maxReach>=n)
                    return true;
            }
        }
        return false;
    }
    public static void main(String[] args)
    {
        System.out.println(new JumpGame().canJump(new int[]{2,3,1,1,4}));
        System.out.println(new JumpGame().canJump(new int[]{3,2,1,0,4}));
        System.out.println(new JumpGame().canJump(new int[]{2,0}));
    }    
}
