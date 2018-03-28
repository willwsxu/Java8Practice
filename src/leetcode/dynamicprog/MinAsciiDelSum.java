package leetcode.dynamicprog;

import java.util.Arrays;

// Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal
// 0 < s1.length, s2.length <= 1000.
// All elements of each string will have an ASCII value in [97, 122].
public class MinAsciiDelSum {
	int dp[][];
    public int minimumDeleteSum(String s1, String s2, int p1, int p2) {
    	if (p1==s1.length() || p2==s2.length()) {
    		int val=0;
    		for (int i=p1; i<s1.length(); i++)
    			val += s1.charAt(i);
    		for (int i=p2; i<s2.length(); i++)
    			val += s2.charAt(i);
    		return val;
    	}
    	if (dp[p1][p2]>=0)
    		return dp[p1][p2];
        int v1=s1.charAt(p1)+minimumDeleteSum(s1, s2, p1+1, p2);
        int v2=s2.charAt(p2)+minimumDeleteSum(s1, s2, p1, p2+1);
        int v3=s1.charAt(p1)+s2.charAt(p2)+minimumDeleteSum(s1, s2, p1+1, p2+1); // delete both
        int v=Integer.min(v1, v2);
        v = Integer.min(v, v3);
        if (s1.charAt(p1)==s2.charAt(p2)) {
        	v=Integer.min(v, minimumDeleteSum(s1, s2, p1+1, p2+1));
        }
        dp[p1][p2]=v;
        return v;
    }
    public int minimumDeleteSum(String s1, String s2) {
    	if ( !s1.isEmpty() && !s2.isEmpty()) {
			dp=new int[s1.length()][s2.length()];
			for (int r[]: dp)
				Arrays.fill(r, -1);
    	}
    	return minimumDeleteSum(s1, s2, 0, 0);        
    }

    public static void main(String[] args)
    {
    	int ans=new MinAsciiDelSum().minimumDeleteSum("sea", "eat");
    	System.out.println(ans==231);
    	ans=new MinAsciiDelSum().minimumDeleteSum("delete", "leet");
    	System.out.println(ans==403);
    	ans=new MinAsciiDelSum().minimumDeleteSum("deee", "");
    	System.out.println(ans==403);
    }
}
