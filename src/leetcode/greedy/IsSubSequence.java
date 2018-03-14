/*
 * Given a string s and a string t, check if s is subsequence of t
 */
package leetcode.greedy;

import java.util.Arrays;

public class IsSubSequence {
    
    int dp[][];
    boolean isSubsequence(String s, String t, int i, int j)
    {
        if (s.length()==i)  // find all
            return true;        
        if (t.length()==j)  // no more char in t to find
            return false;
        if (dp[i][j]>=0)
            return dp[i][j]>0;
        for (int k=j; k<t.length(); k++)
            if (t.charAt(k)==s.charAt(i)) {
                boolean ans = isSubsequence(s, t, i+1, k+1);  // find a match, chech next char in s
                if (ans) {
                    dp[i][j]=1;
                    return true;
                }                
            }
        dp[i][j]=0;
        return false;
    }
    public boolean isSubsequence_v1(String s, String t) { // TLE
        if (s.isEmpty())
            return true;
        if (t.isEmpty())
            return false;
        dp = new int[s.length()][t.length()];
        for (int [] r: dp)
            Arrays.fill(r, -1);
        for (int j=0; j<t.length(); j++) {
            if (isSubsequence(s,t,0,j))
                return true;
        }
        return false;
    }
    public boolean isSubsequence(String s, String t) {  // beat 87%
        if (s.isEmpty())
            return true;
        int j=0;
        char target=s.charAt(j);
        int n=t.length();
        for (int i=0; i<n; i++) {
            if (target==t.charAt(i)) {
                j++;
                if (j==s.length())
                    return true;
                target=s.charAt(j);
            }
        }
        return false;
    }
    
    public static void main(String[] args)
    {
        System.out.println(new IsSubSequence().isSubsequence("abc", "ahbgdc"));
        System.out.println(new IsSubSequence().isSubsequence("acb", "ahbgdc")==false);
    }
}
