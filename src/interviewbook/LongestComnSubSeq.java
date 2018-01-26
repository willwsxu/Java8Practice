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
            return dp[cur1][cur2];  // already computed, just use the value
        int ans=0;
        if (s1.charAt(cur1)==s2.charAt(cur2))    // value match at position cu1 and cur2
            ans = 1+lcs_recurse(s1,s2,cur1-1, cur2-1); // adjust both position
        else {
            ans= max(lcs_recurse(s1,s2,cur1-1, cur2), lcs_recurse(s1,s2,cur1, cur2-1));  // choose max of two possibilities
        }
        dp[cur1][cur2]=ans; // add this for dynamic programming, store answer for position cur1 and cur2
        return ans;
    }
    
    int lcs_recurse(String s1, String s2)
    {
        dp=new int[s1.length()][s2.length()];
        for (int [] r: dp)
            Arrays.fill(r, -1);
        // start from end of string, postion cur base 0
        return lcs_recurse(s1, s2, s1.length()-1, s2.length()-1);
    }
    
    
    int lcs_bottomup(String s1, String s2)
    {
        int m=s1.length();
        int n=s2.length();
        dp=new int[m+1][n+1];  // position of string base 1, 0 means no char
        for (int i=0; i<=m; i++) {
            for (int j=0; j<=n; j++) {
                if (i==0 || j==0)  // 0 mean no char in string
                    dp[i][j]=0;
                // neither i or j is 0
                else if (s1.charAt(i-1)==s2.charAt(j-1)) // value match at position cu1 and cur2
                    dp[i][j]=1+dp[i-1][j-1];
                else
                    dp[i][j]=max(dp[i-1][j], dp[i][j-1]); // try two possibilities
            }
        }
        print(s1, s2);
        return dp[m][n];  // result is in the bottom right corner
    }
    
    void print(String s1, String s2)  // reverse the process of bottom up solution
    {
        int m=s1.length();
        int n=s2.length();
        int p1=m, p2=n;  // print from first char
        //for (int[] r: dp)
        //    out.println(Arrays.toString(r));
        StringBuilder sb=new StringBuilder();
        while (p1>0 && p2>0) {
            if (dp[p1][p2]>dp[p1-1][p2-1]) {
                sb.append(s1.charAt(p1-1));
                p1--;
                p2--;
            } else if (dp[p1][p2]==dp[p1-1][p2]) {
                p1--;
            }
            else if (dp[p1][p2]==dp[p1][p2-1])
                p2--;
            else {
                p1--;
                p2--;
            }
        }
        out.println(sb.reverse().toString());
    }
            
    public static void main(String[] args)
    {
        out.println(new LongestComnSubSeq().lcs_recurse("ABCDGH", "AEDFHR")==3);
        out.println(new LongestComnSubSeq().lcs_recurse("AGGTAB", "GXTXAYB")==4);
        out.println(new LongestComnSubSeq().lcs_bottomup("ABCDGH", "AEDFHR")==3);
        out.println(new LongestComnSubSeq().lcs_bottomup("AGGTAB", "GXTXAYB")==4);
    }
}
