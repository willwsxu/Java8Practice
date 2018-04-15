package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Permutation {	
	void perm(int[] nums, List<Integer> p, int mask, List<List<Integer>> ans) {
		int n=nums.length;
		if (p.size()==n) {  // got all numbers, copy to answer
			ans.add(new ArrayList<>(p));
			return;
		}
		int lastVal=Integer.MIN_VALUE;
		for (int i=0; i<n; i++) {
			int m=1<<i;        // bit mask of each position
			if ( (m&mask)>0 )  // element chosen previously
				continue;
			if (nums[i]==lastVal)  // ignore duplicate value
				continue;
			else
				lastVal=nums[i];
			p.add(nums[i]);
			perm(nums, p, mask|m, ans);
			p.remove(p.size()-1);// remove last one
		}
	}
    List<List<Integer>> permute(int[] nums) // beat 64%
    {
    	List<List<Integer>> ans=new ArrayList<>();
    	perm(nums,new ArrayList<>(), 0, ans);
    	return ans;
    }
    List<List<Integer>> permuteWithDup(int[] nums) // contain duplicate values beat 98%
    {
    	Arrays.sort(nums);
    	List<List<Integer>> ans=new ArrayList<>();
    	perm(nums,new ArrayList<>(), 0, ans);
    	return ans;
    }
    
    // choose k numbers from 1 to n
	void combine(int n, int k, List<Integer> p, List<List<Integer>> ans) {
		if (p.size()==k) {  // got all numbers, copy to answer
			ans.add(new ArrayList<>(p));
			return;
		}
		int s=p.size();
		int next=s==0?1:p.get(s-1)+1;  // start from 1, or next number in the list of chosen
		for (; next<=n-k+s+1; next++) {// carefully calculate the last number to choose to ensure K numbers
			p.add(next);
			combine(n, k, p, ans);
			p.remove(s);// remove last one
		}
	}
	List<List<Integer>> combine(int n, int k)  // beat 99.5%
	{
		List<List<Integer>> ans = new ArrayList<>();
		if (k>n)
			return ans;
		combine(n, k, new ArrayList<Integer>(), ans);
		return ans;
	}
    
    void arrayReverse(int[]arr, int s, int e) // beat 90% after switch from regular sort.
    {
    	e--;
    	while (s<e) {
    		arr[s]=arr[s]^arr[e];
    		arr[e]=arr[s]^arr[e];
    		arr[s]=arr[s]^arr[e];
    		s++;
    		e--;
    	}
    }
    public void nextPermutation(int[] nums) {
        for (int i=nums.length-2; i>=0; i--) {
        	if (nums[i]<nums[i+1]) { // e.g. 3,1,4,2, i=1 find first position whose value is smaller than next one
        		arrayReverse(nums, i+1, nums.length);  // reverse sort from i=2: 3,1,2,4
        		int next=Arrays.binarySearch(nums, i+1, nums.length, nums[i]+1); //find next where nums[next]>= nums[i]+1, next=-3
        		//System.out.println(i);
        		//System.out.println(next);
        		if (next<0)  // upperbound
        			next=(-next)-1;
        		else {  // find lowerbound if next num has duplicate value, wish Java has lowerbound and upperbound
        			while (nums[next]==nums[next-1])
        				next--;
        		}
        		// swap i with next larger value from i+1
        		nums[i] = nums[i]^nums[next];
        		nums[next] = nums[i]^nums[next];
        		nums[i] = nums[i]^nums[next];  // 3,2,1,4
        		return;
        	}
        }
        arrayReverse(nums, 0,nums.length);
    }
/*
	int count=0;
	String ans;
	void perm(String s, String target, int k) {
		if (s.length()==1) {
			//System.out.println(target+s);
			if (++count==k)
				ans=target+s;
			return;
		}
		for (int i=0; i<s.length(); i++) {
			perm(s.substring(0,i)+s.substring(i+1,s.length()), target+s.charAt(i), k);
		}
	}
    public String getPermutation_bruteforce(int n, int k) {
    	StringBuilder sb=new StringBuilder();
    	for (int i=1; i<=n; i++)
    		sb.append(i);
        perm(sb.toString(), "", k);
    	//perm(n, 0,"", 0, k);
        return ans;
    }
*/

	// The set [1,2,3,…,n] contains a total of n! unique permutations.
	// Given n and k, return the kth permutation sequence, n is [1..9]
    int fact[];
    public String getPermutation(String src, String target, int k) {
    	int n=src.length();
    	if (k==1 || n==1)
    		return target+src;
    	// if n=3, k=5 or 6, that mean we need to pick 3rd char in src, recursively call with n=2, k=5-2*fact[2]=1
    	int lead=(k-1)/fact[n-1];     	// pick leading char( take floor ), depend what multiple k is to fact[n-1]
    	//System.out.println(src+" "+target+" "+lead+" "+k);
    	return getPermutation(src.substring(0, lead)+src.substring(lead+1, n), target+src.charAt(lead), k-lead*fact[n-1]);
    }
    public String getPermutation(int n, int k) {
    	fact=new int[n+1];
    	fact[0]=1;
    	for (int i=1; i<=n; i++) // pre-claculate factorial
    		fact[i]=i*fact[i-1];
    	//System.out.println(Arrays.toString(fact));
    	StringBuilder sb=new StringBuilder();
    	for (int i=1; i<=n; i++)
    		sb.append(i);
    	return getPermutation(sb.toString(), "", k);
    }
    public static void testPermKth(String[] args)
    {
    	System.out.println(new Permutation().getPermutation(3,  1).equals("123"));
    	System.out.println(new Permutation().getPermutation(3,  2).equals("132"));
    	System.out.println(new Permutation().getPermutation(3,  3).equals("213"));
    	System.out.println(new Permutation().getPermutation(3,  4).equals("231"));
    	System.out.println(new Permutation().getPermutation(3,  5).equals("312"));
    	System.out.println(new Permutation().getPermutation(3,  6).equals("321"));
    	System.out.println(new Permutation().getPermutation(1,  1).equals("1"));
    	System.out.println(new Permutation().getPermutation(9,  199269).equals("594738216"));
    	System.out.println(new Permutation().getPermutation(9,  135401).equals("439157826"));
    }
    public static void main(String[] args)
    {
    	System.out.println(new Permutation().permute(new int[] {1,2,3}));

    	System.out.println(new Permutation().permuteWithDup(new int[] {1,1,3}));
    	System.out.println(new Permutation().permuteWithDup(new int[] {1,1,1,3,3,3,5}));
    	
    	int[] nums=new int[] {3,1,4,2};
    	new Permutation().nextPermutation(nums);
    	System.out.println(Arrays.toString(nums));
    	nums=new int[] {3,2,1};
    	new Permutation().nextPermutation(nums);
    	System.out.println(Arrays.toString(nums));
    	nums=new int[] {2,3,1};   // binary search to find next value
    	new Permutation().nextPermutation(nums);
    	System.out.println(Arrays.toString(nums));
    	nums=new int[] {1,5,1};  // next value must be larger
    	new Permutation().nextPermutation(nums);
    	System.out.println(Arrays.toString(nums));
    	nums=new int[] {2,2,7,5,4,3,2,2,1};  // java has no upper bound method, search nums[i]+1
    	new Permutation().nextPermutation(nums);
    	System.out.println(Arrays.toString(nums));
    	nums=new int[] {2,1,2,2,2,2,2,1};
    	new Permutation().nextPermutation(nums);
    	System.out.println(Arrays.toString(nums));
    	
    	List<List<Integer>> ans=new Permutation().combine(3, 2);
    	System.out.println(ans);
    }
}
