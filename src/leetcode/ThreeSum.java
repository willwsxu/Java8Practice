/*
 * 
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 
public class ThreeSum {
    static public List<List<Integer>> threeSum(int[] nums) 
    {
        Arrays.sort(nums);
        final int target=0;
        List<List<Integer>> ans=new ArrayList<>();
        for (int i=0; i<nums.length-2; i++) { // try each as first number of 3 sum
            if (nums[i]>target)  // too big, done
                break;
            if (i>0 && nums[i]==nums[i-1])  // same number, no need to try again
                continue;
            int second=i+1;  // pick second and third numbers from both ends
            int third=nums.length-1;
            while (second<third) {
                int sum=nums[i]+nums[second];
                if (sum>target)
                    break;
                sum += nums[third];
                if ( sum>target) // sum too big, move third number left
                    third--;
                else if (sum<target) // sum too small, move second right
                    second++;
                else {
                    List<Integer> triplet=new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[second]);
                    triplet.add(nums[third]);
                    int num2=nums[second];
                    int num3=nums[third];
                    while (second<third && nums[second]==num2) // remove left dup
                        second++;
                    while (second<third && nums[third]==num3)  // remove right dup
                        third--;
                    ans.add(triplet);
                }
            }
        }
        return ans;
    }
    public static void main(String[] args)
    {
        List<List<Integer>> ans=threeSum(new int[]{-10,-10,-10,0,0,20,20});
        System.out.println(ans);
    }
}
