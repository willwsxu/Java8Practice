package leetcode.binarySearch;

import java.util.Arrays;

public class TargetSum {
	// Given an array of integers that is already sorted in ascending order, 
	// find two numbers such that they add up to a specific target number.
    public int[] twoSum_bs(int[] numbers, int target) { // beat 26%
        for (int i=0; i<numbers.length-1; i++)
        {
        	if (numbers[i]>target)
        		break;
        	int next=Arrays.binarySearch(numbers, i+1, numbers.length, target-numbers[i]);
        	if (next>=0)
        		return new int[] {i+1, next+1};
        }
        return new int[0];
    }
    public int[] twoSum(int[] numbers, int target) { // two pointers fast 1 ms, vs 3ms, beat 99%
    	int start=0;
    	int end=numbers.length-1;
    	while (start<end) {
    		int sum=numbers[start]+numbers[end];
    		if (sum==target)
    			return new int[] {start+1, end+1};
    		if (sum>target)
    			end--;
    		else
    			start++;
    	}
        return new int[0];
    }
}
