/*
 * Given a sequence, find the length of the longest palindromic subsequence in it
 */
package interviewbook;

import static java.lang.Integer.max;
import static java.lang.System.out;

public class LongestPalinSubSeq {
    final static int MAX_LEN=2000;
    int dp[][]=new int[MAX_LEN][MAX_LEN];
    
    int recurse(String seq, int start, int end)
    {
        if ( end<start)
            return 0;
        if (end==start)
            return 1;
        if (seq.charAt(start) == seq.charAt(end)) {
            return 2+recurse(seq, start+1, end-1);
        }
        else
            return max(recurse(seq, start, end-1), recurse(seq, start+1, end));
    }
    public static void main(String[] args)
    {
        out.println(new LongestPalinSubSeq().recurse("GEEKSFORGEEKS", 0, 12));
        out.println(new LongestPalinSubSeq().recurse("BBABCBCAB", 0, 8));
    }
}
