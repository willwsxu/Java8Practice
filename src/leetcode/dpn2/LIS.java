/*
 * Given an unsorted array of integers, find the length of longest increasing subsequence
 */
package leetcode.dpn2;

import java.util.Arrays;

public class LIS {
    static public int lengthOfLIS(int[] nums) {
        int ans=1;
        if ( nums.length<=1 )
            return nums.length;
        int lis[]=new int[nums.length];
        Arrays.fill(lis, 1);
        for (int i=1; i<nums.length; i++) {
            for (int j=0; j<i; j++) {
                if (nums[j]<nums[i]) {
                    lis[i]=Integer.max(lis[i], lis[j]+1);
                    if (ans<lis[i])
                        ans=lis[i];
                }
            }
        }
        //System.out.println(Arrays.toString(lis));
        return ans;
    }
    
    static public int findNumberOfLIS(int[] nums) {
        int ans=1;
        int maxLen=1;
        if ( nums.length<=1 )
            return nums.length;
        int lis[]=new int[nums.length];
        int count[]=new int[nums.length];
        Arrays.fill(lis, 1);
        Arrays.fill(count, 1);
        for (int i=1; i<nums.length; i++) {
            for (int j=0; j<i; j++) {
                if (nums[j]<nums[i]) {
                	if (lis[i]<lis[j]+1) {
                		lis[i]=lis[j]+1;
                		count[i]=count[j];
                	}else if (lis[i]==lis[j]+1)
                		count[i] +=count[j];
                }
            }
            if (maxLen<lis[i]) {
            	maxLen=lis[i];
            	ans=count[i];
            }
            else if (maxLen==lis[i])
            	ans += count[i];
        }
        //System.out.println(Arrays.toString(lis));
        return ans;        
    }
    
    public static void main(String[] args)
    {
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18})==4);
        System.out.println(lengthOfLIS(new int[]{2,2})==1);
        System.out.println(lengthOfLIS(new int[]{})==0);
        System.out.println(lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6})==6);        

        System.out.println(findNumberOfLIS(new int[]{1,3,5,4,7}));
        System.out.println(findNumberOfLIS(new int[]{2,2,2,2,2}));
    }
}
