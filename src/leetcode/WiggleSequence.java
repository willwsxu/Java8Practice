/*
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative
 */
package leetcode;

public class WiggleSequence {
    
    static private int wiggle(int[] nums, boolean up) 
    {
        int count1=1;
        for (int i=1; i<nums.length; i++) {
            if (up) {
                if (nums[i]>nums[i-1]) {
                    up=false;
                    count1++;
                }
            } else {
                if (nums[i]<nums[i-1]) {
                    up=true;
                    count1++;
                }            
            }
        }
        return count1;        
    }
    static public int wiggleMaxLength(int[] nums) {
        if (nums.length<2)
            return nums.length;
        int count1=wiggle(nums, true); // first two numbers goes up
        int count2=wiggle(nums, false);
        return Integer.max(count1, count2);
    }
    
    public static void main(String[] args)
    {
        System.out.println(wiggleMaxLength(new int[]{1,7,4,9,2,5})==6);
        System.out.println(wiggleMaxLength(new int[]{1,2,3,4,5,6,7,8,9})==2);
        System.out.println(wiggleMaxLength(new int[]{1,17,5,10,13,15,10,5,16,8})==7);
        System.out.println(wiggleMaxLength(new int[]{2,2,2})==1);
    }
}
