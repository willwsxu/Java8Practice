package leetcode.backtracking;

import leetcode.dynamicprog.EqualSubsetSum;

public class SubsetSumKPartition {

    int subsum[];
    boolean used[];
    boolean canPartitionKSubsets(int[] nums, int k, int target, int setIdx, int numIdx) 
    {
    	if (subsum[setIdx]==target) {
    		if (setIdx==k-2)
    			return true;
    		return canPartitionKSubsets(nums, k, target, setIdx+1, 0);// calc next subset sum
    	}
    	for (int i=numIdx; i<nums.length; i++) {
    		if (used[i] || subsum[setIdx]+nums[i]>target)
    			continue;
    		subsum[setIdx] += nums[i];
    		used[i] =true;
    		if (canPartitionKSubsets(nums, k, target, setIdx, i+1) )
    			return true;
    		subsum[setIdx] -= nums[i];
    		used[i] =false;
    	}
    	return false;
    }
    // Given an array of integers nums and a positive integer k, find whether it's possible 
    // to divide this array into k non-empty subsets whose sums are all equal
    // 1 <= k <= len(nums) <= 16
    // 0 < nums[i] < 10000
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k==1)
        	return true;
        if (k<1 || k> nums.length)
        	return false;
        int sum=0;
        for (int n: nums)
            sum += n;
        if (sum%k>0)
            return false;
        sum /= k;
        subsum=new int[k];
        used=new boolean[nums.length];
        return canPartitionKSubsets(nums, k, sum, 0, 0);
    }

    public static void main(String[] args)
    {        
    	System.out.println(new SubsetSumKPartition().canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4)==true);
    }

}
