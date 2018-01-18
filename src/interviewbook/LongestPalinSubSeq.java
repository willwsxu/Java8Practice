/*
 * Given a sequence, find the length of the longest palindromic subsequence in it
 */
package interviewbook;

import static java.lang.Integer.max;
import static java.lang.System.out;
import java.util.Arrays;

public class LongestPalinSubSeq {
    final static int MAX_LEN=2000;
    int dp[][]=new int[MAX_LEN][MAX_LEN];
    {
        for (int[] r: dp)
            Arrays.fill(r, -1);
    }
    
    int recurse(String seq, int start, int end)
    {
        if ( end<start)
            return 0;
        if (end==start)
            return 1;
        if (dp[start][end]>=0)     // dp sub problem is already solved
            return dp[start][end];
        if (seq.charAt(start) == seq.charAt(end)) {
            dp[start][end] = 2+recurse(seq, start+1, end-1);
        }
        else {
            dp[start][end] = max(recurse(seq, start, end-1), recurse(seq, start+1, end));
        }
        return dp[start][end];
    }
   
    // start with sub string len 1 to n
    int bottomup(String seq)
    {
        for (int i=0; i<seq.length(); i++)
            dp[i][i]=1;  // Strings of length 1 are palindrome of lentgh 1
        for (int len=2; len<=seq.length(); len++) {
            for (int i=0; i<seq.length()-len+1; i++) {
                int j=i+len-1;  // len is distance between i and j
                if (seq.charAt(i) == seq.charAt(j)) {
                    if ( len==2)  // special case
                        dp[i][j]=2;
                    else
                        dp[i][j]=2+dp[i+1][j-1];
                } else
                    dp[i][j]=max(dp[i+1][j], dp[i][j-1]);
            }
        }
        return dp[0][seq.length()-1];
    }
    
    public static void main(String[] args)
    {
        out.println(new LongestPalinSubSeq().recurse("GEEKSFORGEEKS", 0, 12)==5);
        out.println(new LongestPalinSubSeq().recurse("BBABCBCAB", 0, 8)==7);
        
        out.println(new LongestPalinSubSeq().bottomup("GEEKSFORGEEKS")==5);
        out.println(new LongestPalinSubSeq().bottomup("BBABCBCAB")==7);        
    }
}
