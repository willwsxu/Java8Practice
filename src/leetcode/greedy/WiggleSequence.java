/*
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative
 */
package leetcode.greedy;

public class WiggleSequence {
    static public int wiggleMaxLength(int[] nums) {  // beat 100%
        if (nums.length<2)
            return nums.length;
        int i=1;
        for (; i<nums.length; i++) {
            if (nums[i]!=nums[i-1])
                break;
        }
        if (i==nums.length)
            return 1;
        boolean up=nums[i]>nums[i-1];  // greedy method, find direction of first 2 numbers
        int count1=1;
        for (; i<nums.length; i++) {
            if (up) {
                if (nums[i]>nums[i-1]) {  // compare to last number
                    up=false;  // flip direction
                    count1++;
                }  // else number is smaller, use it for next compare
            } else {
                if (nums[i]<nums[i-1]) {
                    up=true;
                    count1++;
                }            
            }
        }
        return count1;
    }
    
    public static void main(String[] args)
    {
        System.out.println(wiggleMaxLength(new int[]{1,7,4,9,2,5})==6);
        System.out.println(wiggleMaxLength(new int[]{1,2,3,4,5,6,7,8,9})==2);
        System.out.println(wiggleMaxLength(new int[]{1,17,5,10,13,15,10,5,16,8})==7);
        System.out.println(wiggleMaxLength(new int[]{2,2,2})==1);
    }
}
