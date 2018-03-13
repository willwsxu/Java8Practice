/*
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product
 */
package leetcode.dpspecial;

public class MaxProduct {
    static public int maxProduct(int[] nums) {
        int local_min=nums[0];
        int local_max=nums[0];
        int ans=nums[0];
        for (int i=1; i<nums.length; i++) {
            int newmin=local_min*nums[i];
            int newmax=local_max*nums[i];
            // pick min or max from prod with current, or just current elem
            local_min = Integer.min(Integer.min(newmin, newmax), nums[i]);
            local_max = Integer.max(Integer.max(newmin, newmax), nums[i]);
            ans = Integer.max(ans, Integer.max(local_min, local_max));
        }
        return (int)ans;
    }
    
    public static void main(String[] args)
    {
        System.out.println(maxProduct(new int[]{-3,0,1,-2}));
    }
}
