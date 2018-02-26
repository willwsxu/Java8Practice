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
    static private List<List<Integer>> threeSum(int[] nums, int start, int target) 
    {
        List<List<Integer>> ans=new ArrayList<>();
        for (int i=start; i<nums.length-2; i++) { // try each as first number of 3 sum
            //if (nums[i]>target)  // false assumption
            //    break;
            if (i>start && nums[i]==nums[i-1])  // same number, no need to try again
                continue;
            int second=i+1;  // pick second and third numbers from both ends
            int third=nums.length-1;
            while (second<third) {
                //System.out.println("second="+second+",third="+third);
                int sum=nums[i]+nums[second];
                //if (sum>target)  //false assumption
                //    break;
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
    static public int threeSumClosest(int[] nums, int target) 
    {
        Arrays.sort(nums);
        int ans=nums[0]+nums[1]+nums[2];
        int delta=Math.abs(ans-target);
        for (int i=0; i<nums.length-2; i++) { // try each as first number of 3 sum
            int sum3=nums[i]+nums[i+1]+nums[i+2];
            if (sum3==target) 
                return sum3;
            else if (sum3>target){
                if (sum3-target<delta)
                    return sum3; 
                return ans;    
            }
            if (i>0 && nums[i]==nums[i-1])  // same number, no need to try again
                continue;
            int second=i+1;  // pick second and third numbers from both ends
            int third=nums.length-1;
            while (second<third) {
                sum3 = nums[i]+nums[second]+nums[third];
                int diff=sum3-target;
                if (diff==0)
                    return sum3;
                else if (diff>delta)
                    third--;
                else if (diff<-delta) {
                    second++;
                }
                else {
                    ans=sum3;
                    delta=Math.abs(diff);
                    if (diff>0)
                        third--;
                    else
                        second++;
                }
            }
        }
        return ans;
    }
    
    static public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans=new ArrayList<>();
        for (int i=0; i<nums.length-3; i++) { // try each as first number of 4 sum
            //if (nums[i]>target)  // too big, done
            //    break;
            if (i>0 && nums[i]==nums[i-1])  // same number, no need to try again
                continue;
            List<List<Integer>> ans3 = threeSum(nums, i+1, target-nums[i]);
            for (List<Integer> li: ans3) {
                li.add(nums[i]);
                ans.add(li);
            }
        }
        return ans;
    }
    public static void main(String[] args)
    {
        List<List<Integer>> ans=threeSum(new int[]{-10,-10,-10,0,0,20,20});
        System.out.println(ans);
        System.out.println(threeSum(new int[]{0,0,0}));
                
        System.out.println(threeSumClosest(new int[]{-10,-10,-10,0,0,20,20}, 11)==10);
        System.out.println(threeSumClosest(new int[]{1,1,-1}, 2)==1);
        System.out.println(threeSumClosest(new int[]{0,2,1,-3}, 1)==0);
        
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0)); //[[-1, 1, 2, -2], [0, 0, 2, -2], [0, 0, 1, -1]]
        System.out.println(fourSum(new int[]{0,0,0,0}, 0));
        System.out.println(fourSum(new int[]{1,-2,-5,-4,-3,3,3,5}, -11)); //[[-4, -3, 1, -5]]
        System.out.println(fourSum(new int[]{-1,0,-5,-2,-2,-4,0,1,-2}, -9));//[[-5,-4,-1,1],[-5,-4,0,0],[-5,-2,-2,0],[-4,-2,-2,-1]]
        
        System.out.println(threeSum(new int[]{-4,-3,-2,1,3,3,5}, 0, -6));
        System.out.println(threeSum(new int[]{-2,-2,-2,-1,0,0,1}, 0, -5));
    }
}