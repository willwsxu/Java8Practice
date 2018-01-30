/*
 * Given a string, print the longest repeating subseequence such that the two subsequence don’t have same string character at same position
 */
package interviewbook;

import static java.lang.Integer.max;
import static java.lang.System.out;

public class LongestReptSubSeq {
    
    int lrs_recurse(String s1, int cur1, int cur2)
    {
        if (cur1<0 || cur2<0)
            return 0;
//        if ( dp[cur1][cur2]>=0)  // add this two lines for dynamic programming
//            return dp[cur1][cur2];  // already computed, just use the value
        int ans=0;
        if (cur1 != cur2 && s1.charAt(cur1)==s1.charAt(cur2))    // value match at position cu1 and cur2
            ans = 1+lrs_recurse(s1,cur1-1, cur2-1); // adjust both position
        else {
            ans= max(lrs_recurse(s1,cur1-1, cur2), lrs_recurse(s1,cur1, cur2-1));  // choose max of two possibilities
        }
//        dp[cur1][cur2]=ans; // add this for dynamic programming, store answer for position cur1 and cur2
        return ans;
    }
    public static void main(String[] args)
    {
        out.println(new LongestReptSubSeq().lrs_recurse("AABEBCDD", 7,7)==3);
    }
}
