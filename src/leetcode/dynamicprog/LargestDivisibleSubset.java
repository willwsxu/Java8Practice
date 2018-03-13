package leetcode.dynamicprog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given a set of distinct positive integers, find the largest subset such that 
// every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0
public class LargestDivisibleSubset {
    static public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> ans=new ArrayList<>();
    	if (nums.length==0)
    		return ans;
        Arrays.sort(nums);
        int count[]=new int[nums.length];
        Arrays.fill(count, 1);
        int maxpos=0;
        int maxCount=0;
        for (int i=1; i<nums.length; i++) {
        	for (int j=i-1; j>=0; j--) {
        		if (nums[i]%nums[j]==0) {
        			count[i]=Integer.max(count[i], 1+count[j]);
        			if (maxCount<count[i]) {
        				maxCount=count[i];
        				maxpos=i;
        			}
        		}
        	}
        }
        //System.out.println("max pos="+maxpos);
        ans.add(nums[maxpos]);
        int val=nums[maxpos];
        maxCount--;
        for (int j=maxpos-1; j>=0; j--) {
        	if (val%nums[j]==0 && count[j]==maxCount) {
        		val=nums[j];
        		ans.add(val);
        		maxCount--;
        	}
        }
        return ans;
    }

    public static void main(String[] args)
    {
    	System.out.println(largestDivisibleSubset(new int[] {1,2,4,8,6}));
    	System.out.println(largestDivisibleSubset(new int[] {4,8,10,240}));
    }
    

}
