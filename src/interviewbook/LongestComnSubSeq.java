/*
 * Given two sequences, find the length of longest subsequence present in both of them
 */
package interviewbook;

import static java.lang.Integer.max;
import static java.lang.System.out;
import java.util.Arrays;

public class LongestComnSubSeq {
    int dp[][];
    int lcs_recurse(String s1, String s2, int cur1, int cur2)
    {
        if (cur1<0 || cur2<0)
            return 0;
        if ( dp[cur1][cur2]>=0)  // add this two lines for dynamic programming
            return dp[cur1][cur2];
        int ans=0;
        if (s1.charAt(cur1)==s2.charAt(cur2))
            ans = 1+lcs_recurse(s1,s2,cur1-1, cur2-1);
        else {
            ans= max(lcs_recurse(s1,s2,cur1-1, cur2), lcs_recurse(s1,s2,cur1, cur2-1));
        }
        dp[cur1][cur2]=ans; // add this for dynamic programming
        return ans;
    }
    
    int lcs_recurse(String s1, String s2)
    {
        dp=new int[s1.length()][s2.length()];
        for (int [] r: dp)
            Arrays.fill(r, -1);
        return lcs_recurse(s1, s2, s1.length()-1, s2.length()-1);
    }
    
    
    int lcs_bottomup(String s1, String s2)
    {
        int m=s1.length();
        int n=s2.length();
        dp=new int[m+1][n+1];
        for (int i=0; i<=m; i++) {
            for (int j=0; j<=n; j++) {
                if (i==0 || j==0)  // 0 mean no char in string
                    dp[i][j]=0;
                // neither i or j is 0
                else if (s1.charAt(i-1)==s2.charAt(j-1))
                    dp[i][j]=1+dp[i-1][j-1];
                else
                    dp[i][j]=max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m][n];
    }
            
    public static void main(String[] args)
    {
        out.println(new LongestComnSubSeq().lcs_recurse("ABCDGH", "AEDFHR")==3);
        out.println(new LongestComnSubSeq().lcs_recurse("AGGTAB", "GXTXAYB")==4);
        out.println(new LongestComnSubSeq().lcs_bottomup("ABCDGH", "AEDFHR")==3);
        out.println(new LongestComnSubSeq().lcs_bottomup("AGGTAB", "GXTXAYB")==4);
    }
}
