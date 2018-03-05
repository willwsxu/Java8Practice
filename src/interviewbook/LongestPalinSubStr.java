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
        dp=new int[n][n];  // len, start index 0
        int count=s.length();
        Arrays.fill(dp[0], 1);
        for (int len=2; len<=n; len++) {
            for (int j=0; j<n-len+1; j++) {
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
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args)
    {
        out.println(new LongestPalinSubStr().bottomup("aaabccbdadd"));

        out.println(new LongestPalinSubStr().countSubstrings("aaa")==6);
        out.println(new LongestPalinSubStr().countSubstrings("abc")==3);
    }
}
