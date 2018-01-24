/*
 * Given a sequence, find the length of the longest palindromic subsequence in it
 */
package interviewbook;

import static java.lang.Integer.max;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        int n=seq.length();
        for (int i=0; i<n; i++)
            dp[i][i]=1;  // Strings of length 1 are palindrome of lentgh 1
        for (int len=2; len<=n; len++) { // palindrome len from 2 to n
            for (int i=0; i<n-len+1; i++) { // check palindrome start from i, with len
                int j=i+len-1;  // pos of last char of string with len 
                if (seq.charAt(i) == seq.charAt(j)) {
                    if ( len==2)  // special case
                        dp[i][j]=2;
                    else
                        dp[i][j]=2+dp[i+1][j-1];
                } else
                    dp[i][j]=max(dp[i+1][j], dp[i][j-1]);
            }
        }
        return dp[0][n-1];
    }
    void print(String seq)
    {
        List<Character> out=new ArrayList<>();
        int left=0;
        int right=seq.length()-1;
        StringBuilder sb=new StringBuilder();
        while (left<=right) {
            if ( dp[left][right]<=2) {
                if (dp[left][right]==2)
                    out.add(seq.charAt(left));
                for (char ch: out)
                    sb.append(ch);
                if (dp[left][right]==1)
                    sb.append(seq.charAt(left)); // odd len
                Collections.reverse(out);
                for (char ch: out)
                    sb.append(ch);
                break;
            }
            else {
                if (dp[left][right]-2==dp[left+1][right-1]) {
                    out.add(seq.charAt(left));
                    left++;
                    right++;
                } else if (dp[left][right]>dp[left+1][right])
                    left++;
                else
                    right--;
            }
        }
        System.out.println(sb.toString());
    }
    static void test1(String target, int expect)
    {
        LongestPalinSubSeq pal=new LongestPalinSubSeq();
        out.println(pal.recurse(target, 0, target.length()-1)==expect);
        out.println(pal.bottomup(target)==expect);
        //pal.print(target);   // doest not work yet
    }
    
    public static void main(String[] args)
    {
        test1("GEEKSFORGEEKS", 5);
        test1("BBABCBCAB", 7);    
    }
}
