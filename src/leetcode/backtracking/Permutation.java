package leetcode.backtracking;

public class Permutation {

	// The set [1,2,3,…,n] contains a total of n! unique permutations.
	// Given n and k, return the kth permutation sequence, n is [1..9]
	
	int count=0;
	String ans;
	void perm(int n, int pos, String s, int mask, int k) {
		if (pos==n) { // pos from 0 to n-1 are valid
			//System.out.println(s);
			if (++count==k)
				ans=s;
			return;
		}
		for (int i=1; i<=n; i++) {
			int m=1<<(i-1);
			if ( (m&mask)>0 )  // digit used in previous position
				continue;
			perm(n, pos+1, s+i, mask|m, k);
		}
	}
    public String getPermutation(int n, int k) {
        perm(n, 0, "", 0, k);
        return ans;
    }
    public static void main(String[] args)
    {
    	System.out.println(new Permutation().getPermutation(3,  5));
    	System.out.println(new Permutation().getPermutation(1,  1));
    }
}
