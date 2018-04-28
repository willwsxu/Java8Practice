package leetcode.sort;

import java.util.Arrays;

public class Partition {
	void shuffle(int[] nums)
	{
		
	}
	int partition(int[] nums, int start, int end) // partition values <nums[start] to left, [start, end)
	{
		int oriEnd=end;
		int pv=nums[start]; // pivot value
		while (start<end) {
			if (nums[end]<pv) {  // swap
				nums[start] ^= nums[end];
				nums[end] ^= nums[start];
				nums[start] ^= nums[end];
			}
			while (end>=0 && nums[end]>=pv)
				end--;
			while (nums[start]<pv)
				start++;
		}	
		int pivot=start;
		while (start<oriEnd) {  // move number same as pivot to pivot places
			while (oriEnd>=start && nums[oriEnd]!=pv)
				oriEnd--;
			while (nums[start]==pv && start<oriEnd)
				start++;
			if ( start<oriEnd) {
				nums[start] ^= nums[oriEnd];
				nums[oriEnd] ^= nums[start];
				nums[start] ^= nums[oriEnd];				
			}
		}
		return pivot;
	}
	void nthElement(int[]nums, int n)  // from low to high, for now. pivot on index n (0 based)
	{
		int start =0;
		int end=nums.length-1;
		while (start<=end) {
			int pv=nums[start];
			int pivot=partition(nums, start, end);
			//System.out.println("pivot="+pivot+" "+Arrays.toString(nums));
			if (pivot==n)
				return;
			if (pivot<n) {
				if (nums[pivot]==pv)
					start=pivot+1;  // prevent infinite loop
				else
					start=pivot;
			}
			else
				end=pivot;
		}
	}
	static void test()
	{
		int p=new Partition().partition(new int[] {2,5,7,1,3}, 0, 4);
		System.out.println(p==1);
		p=new Partition().partition(new int[] {1,5,7,1,3}, 0, 4);
		System.out.println(p==0);
		p=new Partition().partition(new int[] {9,5,7,1,3}, 0, 4);
		System.out.println(p==4);
		
		int[] nums=new int[] {3,3,7,1,3};
		new Partition().nthElement(nums, 1);
		System.out.println(nums[1]);
		new Partition().nthElement(nums, 2);
		System.out.println(nums[2]);
	}
	
    public void wiggleSort(int[] nums) {
    	int pivot=(nums.length+1)/2;
        nthElement(nums, pivot);
        int ans[]=new int[nums.length];
        for (int i=0; i<pivot; i++)  // first half to even slots
        	ans[2*i]=nums[pivot-1-i];         // reverse order
        for (int i=0; i<nums.length/2; i++)  // first half to even slots
        	ans[2*i+1]=nums[nums.length-i-1]; // reverse order
        	//ans[2*i+1]=nums[pivot+i];
        for (int i=0; i<nums.length; i++)
        	nums[i]=ans[i];
    }
    static void testWiggle()
    {
    	int [] nums=new int[] {1,5,1,1,6,4};
    	new Partition().wiggleSort(nums);
    	System.out.println(Arrays.toString(nums));
    	nums=new int[] {1,3,2,2,3,1};
    	new Partition().wiggleSort(nums);
    	System.out.println(Arrays.toString(nums));
    	nums=new int[] {4,5,5,6};
    	new Partition().wiggleSort(nums);
    	System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args)
    {
    	testWiggle();
    }
}
