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
    public String getPermutation(int n, int k) {
    	StringBuilder sb=new StringBuilder();
    	for (int i=1; i<=n; i++)
    		sb.append(i);
        perm(sb.toString(), "", k);
    	//perm(n, 0,"", 0, k);
        return ans;
    }
    public static void main(String[] args)
    {
    	System.out.println(new Permutation().getPermutation(3,  5).equals("312"));
    	System.out.println(new Permutation().getPermutation(1,  1).equals("1"));
    	//for (int i=0; i<100; i++) {
    	System.out.println(new Permutation().getPermutation(9,  199269).equals("594738216"));
    	System.out.println(new Permutation().getPermutation(9,  135401).equals("439157826"));
    	//}
    }
}
