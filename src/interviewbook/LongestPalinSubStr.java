/*
 * Given a string, find longest substring which is palindrome
 */
package interviewbook;

import static java.lang.System.out;

import java.util.Arrays;

public class LongestPalinSubStr {
    int dp[][];
    // test palindrome from len 1 to n, only check char at each end
    String bottomup(String s)
    {
        int n=s.length();
        dp=new int[n][n];  // len, start index
        int maxlen=1;
        int start=0;
        for (int len=1; len<=n; len++) {
            for (int j=0; j<n-len+1; j++) {
                if (len==1) {
                    dp[len-1][j]=1;
                    continue;
                }
                int end = j+len-1;
                if (s.charAt(end)!=s.charAt(j)) {
                    dp[len-1][j]=0;
                    continue;
                }
                if (len==2)
                    dp[len-1][j]=2;
                else {
                    if (dp[len-3][j+1]==0) {
                        dp[len-1][j]=0;
                        continue;
                    }
                    else
                        dp[len-1][j] =2+dp[len-3][j+1];
                }
                maxlen=len;
                start=j;
            }
        }
        return s.substring(start, start+maxlen);
    }

    public int countSubstrings(String s) {
        int n=s.length();
        dp=new int[2][n];  // only need to keep previous 2 len
        int count=s.length();
        Arrays.fill(dp[0], 1); // len 1
        
        for (int j=0; j<n-1; j++) { // len=2
            if (s.charAt(j+1)!=s.charAt(j)) {
                dp[1][j]=0;
            }
            else {
                dp[1][j]=2;
                count++;
            }
        }
        int prevprev=0; // prev=1-prevprev
        for (int len=3; len<=n; len++) {
            for (int j=0; j<n-len+1; j++) {
                int end = j+len-1;
                if (s.charAt(end)!=s.charAt(j)) {
                    dp[prevprev][j]=0;  // reuse prevprev for current
                    continue;
                }
                if (dp[prevprev][j+1]==0) {  // check value at len-2
                    dp[prevprev][j]=0;
                    continue;
                }
                else
                    dp[prevprev][j] =2+dp[prevprev][j+1];
                count++;
            }
            prevprev = 1-prevprev; // prev is now preprev
        }
        return count;
    }
    public static void main(String[] args)
    {
        out.println(new LongestPalinSubStr().bottomup("aaabccbdadd"));

        out.println(new LongestPalinSubStr().countSubstrings("aaa")==6);
        out.println(new LongestPalinSubStr().countSubstrings("abc")==3);
        out.println(new LongestPalinSubStr().countSubstrings("a")==1);
        out.println(new LongestPalinSubStr().countSubstrings("aa")==3);
    }
}
