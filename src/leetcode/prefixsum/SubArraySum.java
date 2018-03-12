/*
 * Given a list of non-negative numbers and a target integer k, write a function 
 * to check if the array has a continuous subarray of size at least 2 that sums 
 * up to the multiple of k, that is, sums up to n*k where n is also an integer.
 */
package leetcode.prefixsum;

import java.util.HashMap;
import java.util.Map;


public class SubArraySum {
    
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length<2)
            return false;
        if ( k==1)
            return true;
        if (k==0) { // find 2 consecutive 0
            for (int i=0; i<nums.length-1; i++) {
                if (nums[i]==0 && nums[i+1]==0)
                    return true;
            }
            return false;
        }
        Map<Integer, Integer> remainders=new HashMap<>();
        int prefixRem=0;
        remainders.put(prefixRem, 0);  //initial state
        for (int i=0; i<nums.length; i++) {
            prefixRem=(prefixRem+nums[i])%k;
            Integer found=remainders.get(prefixRem);
            if (found==null) {
                remainders.put(prefixRem, i+1);
                continue;
            }
            if (i-found>0)
                return true;
        }
        return false;
    }
    public static void main(String[] args)
    {
        System.out.println(new SubArraySum().checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6)==true);
        System.out.println(new SubArraySum().checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6)==true);
        System.out.println(new SubArraySum().checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 0)==false); // edge case
        System.out.println(new SubArraySum().checkSubarraySum(new int[]{23, 2, 6, 0, 0}, 0)==true);
        System.out.println(new SubArraySum().checkSubarraySum(new int[]{5,2,4}, 5)==false);
        System.out.println(new SubArraySum().checkSubarraySum(new int[]{1,1}, 2)==true);  // edge case
        System.out.println(new SubArraySum().checkSubarraySum(new int[]{7,1,3}, 4)==true);
        
        System.out.println(new SubArraySum().checkSubarraySum(new int[]{24, 2, 6, 5, 6}, 6)==false);  // new test case
    }
}
