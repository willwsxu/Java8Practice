package leetcode;

public class Duplicate {
	// Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive)
	// find the duplicate
    public int findDuplicate_modify(int[] nums) { // 
        int n=nums.length-1;
        int place=nums[0]; // put this number in place
        while (nums[place]!=place) {
        	nums[0]=nums[place];
        	nums[place]=place;
        	place=nums[0];
        }
        return place;
    }
    // You must not modify the array (assume the array is read only).
    //  You must use only constant, O(1) extra space.
    public int findDuplicate(int[] nums) {  // linked list cycle detection
        int n=nums.length-1;
        int slow=nums[0]; // put this number in place
        int fast=nums[slow];
        while (slow !=fast) {
        	slow=nums[slow];
        	fast=nums[nums[fast]];
        }
        return nums[slow];    
    }
}
