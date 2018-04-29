package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Intersection {
	static int[] unique(int nums[])  // input is sorted
	{
		if (nums.length<2)
			return nums;
		int last=nums[0]/2+1;
		int count=0;
		for (int n: nums) {
			if (n==last)
				continue;
			nums[count++]=n; // cluster unique values to left
		}
		int [] ans=new int[count];
		for (int i=0; i<count; i++)
			ans[i]=nums[i];
		return ans;
	}
	
	static Set<Integer> convert(int[] nums)
	{
		Set<Integer> s=new HashSet<>();
		for (int n: nums)
			s.add(n);
		return s;
	}
    static public int[] intersection(int[] nums1, int[] nums2) {
    	Set<Integer> s1=convert(nums1);
    	Set<Integer> s2=convert(nums2);
    	if (s1.size()<s2.size()) {
    		Set<Integer> temp=s2;
    		s2=s1;
    		s1=temp;
    	}
    	int count=0;
    	for (int n: s2)  // iterate short one
    		if (s1.contains(n))
    			nums1[count++]=n;
    	int[] ans=new int[count];
		for (int i=0; i<count; i++)
			ans[i]=nums1[i];
    	return ans;
    }
    
    static void testUnique()
    {
    	System.out.println(unique(new int[] {1,1,2,3,4,4,5}).length==5);
    }
    public static void main(String[] args)
    {
    	int[] ans=intersection(new int[] {1,2,2,1}, new int[] {2,2});
    	System.out.println(Arrays.toString(ans));
    }

}
