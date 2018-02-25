/*
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 */
package leetcode.binarySearch;

public class RotatedSortedArray {
    static public int search(int[] nums, int target) {
        int start=0;
        int end=nums.length-1;
        if (end<0)
            return -1;
        if (nums[start]==target)
            return start;
        if (nums[end]==target)
            return end;
        while (end-start>1 ) {
            int mid=(start+end)/2;
            if (nums[mid]==target)
                return mid;
            if (nums[mid+1]==target)
                return mid+1;
            int range=0;
            // check range [start, mid]
            if ( nums[start]<=nums[mid]) {
                if (target<nums[mid] && target>=nums[start]) {
                    end=mid;
                    continue;
                }
                range |=2; // pick second range
            } 
            // check [mid+1, end], one or both can be true
            if ( nums[mid+1]<=nums[end])
            {
                if (target<=nums[end] && target>nums[mid+1]) {
                    start=mid+1;
                    continue;
                }
                range |=1; // pick first range
            }
            if (range==3)
                return -1;
            else if (range==1)
                end=mid;
            else
                start=mid+1;
        } 
        if (nums[start]==target)
            return start;
        if (nums[end]==target)
            return end;
        return -1;
    }
    public static void main(String[] args)
    {
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 7)==3);
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0)==4);
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 2)==6);
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 4)==0);
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 8)==-1);
        System.out.println(search(new int[]{}, 8)==-1);
        System.out.println(search(new int[]{5,1,2,3,4}, 1));
    }
}
