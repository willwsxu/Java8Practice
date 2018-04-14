package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {	
	void perm(int[] nums, int pos, List<Integer> p, int mask, List<List<Integer>> ans) {
		int n=nums.length;
		if (pos==n) { // pos from 0 to n-1 are valid
			//System.out.println(s);
			ans.add(new ArrayList<>(p));
			return;
		}
		for (int i=0; i<n; i++) {
			int m=1<<i;
			if ( (m&mask)>0 )  // digit used in previous position
				continue;
			p.add(nums[i]);
			perm(nums, pos+1, p, mask|m, ans);
			p.remove(p.size()-1);// remove last one
		}
	}
    List<List<Integer>> permute(int[] nums) // beat 64%
    {
    	List<List<Integer>> ans=new ArrayList<>();
    	perm(nums,0,new ArrayList<>(), 0, ans);
    	return ans;
    }
    List<List<Integer>> permute2(int[] nums) // contain duplicate values
    {
    	Arrays.sort(nums);
    	List<List<Integer>> ans=new ArrayList<>();
    	perm(nums,0,new ArrayList<>(), 0, ans);
    	return ans;
    }

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

    	System.out.println(new Permutation().permute2(new int[] {1,1,3}));
    }
}
