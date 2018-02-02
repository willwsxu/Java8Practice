/*
 * Given a string, print the longest repeating subseequence such that the two subsequence don’t have same string character at same position
 */
package interviewbook;

import static java.lang.Integer.max;
import static java.lang.System.out;
import java.util.Arrays;

public class LongestReptSubSeq {
    int dp[][];
    
    int lrs_recurse(String s1, int cur1, int cur2)
    {
        if (cur1<0 || cur2<0)
            return 0;
        //if ( dp[cur1][cur2]>=0)  // add this two lines for dynamic programming
        //    return dp[cur1][cur2];  // already computed, just use the value
        int ans=0;
        if (cur1 != cur2 && s1.charAt(cur1)==s1.charAt(cur2))    // value match at position cu1 and cur2
            ans = 1+lrs_recurse(s1,cur1-1, cur2-1); // adjust both position
        else {
            ans= max(lrs_recurse(s1,cur1-1, cur2), lrs_recurse(s1,cur1, cur2-1));  // choose max of two possibilities
        }
        //dp[cur1][cur2]=ans; // add this for dynamic programming, store answer for position cur1 and cur2
        return ans;
    }
    int lrs_recurse(String s1)
    {
        dp=new int[s1.length()][s1.length()];
        for (int [] r: dp)
            Arrays.fill(r, -1);
        return lrs_recurse(s1, s1.length()-1, s1.length()-1);
    }
    int lrs_bottomup(String s1)
    {
        int n=s1.length();
        dp=new int[n+1][n+1];
        for (int [] r: dp)
            Arrays.fill(r, 0);
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (i!=j && s1.charAt(i-1)==s1.charAt(j-1))
                    dp[i][j]=1+dp[i-1][j-1];
                else
                    dp[i][j]=max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[n][n];
    }
    public static void main(String[] args)
    {
        // test failed, how to prevent second A in string to be used twice
        out.println(new LongestReptSubSeq().lrs_bottomup("AAABEBCDD")); //ABD
    }
}
