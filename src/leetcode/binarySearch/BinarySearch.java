package leetcode.binarySearch;

public class BinarySearch {

	static int lowerBound(int[] arr, int val, int low, int high) 
	{
		if (low<high)
		{
			int mid=(low+high)/2;
			if (arr[mid]<val)
				return lowerBound(arr, val, mid+1, high);
			else
				return lowerBound(arr, val, low, mid);
		}
		else
			return low;
	}
	static int upperBound(int[] arr, int val, int low, int high) 
	{
		if (low<high)
		{
			int mid=(low+high)/2;
			if (arr[mid]<=val)
				return upperBound(arr, val, mid+1, high);
			else
				return upperBound(arr, val, low, mid);
		}
		else
			return low;
	}
	// lower bound and upper bound, same if not found
	static int[] equal_range(int[] arr, int val)
	{
		int low=lowerBound(arr, val, 0, arr.length);
		if (low==arr.length || arr[low]>val)
			return new int[] {low, low};
		int high=upperBound(arr, val, low+1, arr.length);
		return new int[] {low, high};
	}

    public static void main(String[] args)
    {
    	int ans[]=equal_range(new int[] {1,2,3,5,10}, 10);
    	System.out.println(ans[0]+" "+ans[1]);
    	ans=equal_range(new int[] {2,2,2,5,10}, 2);
    	System.out.println(ans[0]+" "+ans[1]);
    	ans=equal_range(new int[] {2,2,2,5,5}, 1);
    	System.out.println(ans[0]+" "+ans[1]);
    	ans=equal_range(new int[] {2,2,2,5,5}, 10);
    	System.out.println(ans[0]+" "+ans[1]);
    	ans=equal_range(new int[] {2,2,2,5,5}, 5);
    	System.out.println(ans[0]+" "+ans[1]);
    }
}
