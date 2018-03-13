package leetcode.dpn2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given a set of distinct positive integers, find the largest subset such that 
// every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0
public class LargestDivisibleSubset {
    static public List<Integer> largestDivisibleSubset(int[] nums) { // O(n^2)
        List<Integer> ans=new ArrayList<>();
    	if (nums.length==0)
            return ans;
        Arrays.sort(nums);
        int count[]=new int[nums.length];  // count of a valid subset including at this pos
        Arrays.fill(count, 1);
        int parent[]=new int[nums.length]; // maintain a link of previous item in the subset
        Arrays.fill(parent, -1);
        int maxpos=0;  // keep track of location and count of max subset
        int maxCount=0;
        for (int i=1; i<nums.length; i++) {
        	for (int j=i-1; j>=0; j--) {
        		if (nums[i]%nums[j]==0) {
                            if ( count[i] < 1+count[j] ) {
        			count[i]=1+count[j];
                                parent[i]=j;
        			if (maxCount<count[i]) {
        				maxCount=count[i];
        				maxpos=i;
        			}
                            }
        		}
        	}
        }
        //System.out.println("max pos="+maxpos);
        //System.out.println(Arrays.toString(nums));
        //System.out.println(Arrays.toString(parent));
        do {
            ans.add(nums[maxpos]);
            maxpos=parent[maxpos];
        } while (maxpos>=0);
        return ans;
    }

    public static void main(String[] args)
    {
    	System.out.println(largestDivisibleSubset(new int[] {1,2,4,8,6}));
    	System.out.println(largestDivisibleSubset(new int[] {4,8,10,240}));
    }
    

}
