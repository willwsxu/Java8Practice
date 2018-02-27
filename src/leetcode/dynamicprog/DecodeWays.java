/*
 * 
 */
package leetcode.dynamicprog;

public class DecodeWays {
    int dp[];
    public int numDecodings(String s) {
        int n=s.length();
        if (n==0)
            return 0;
        dp=new int[n+1];
        dp[n]=1; // good initial value
        dp[n-1]=s.charAt(n-1)=='0'?0:1; // '0' is not decodable
        for (int i=n-2; i>=0; i--) {
            int d=s.charAt(i)-'0';
            if ( d>0 )  // not '0'
                dp[i]+=dp[i+1];
            d=d*10+(s.charAt(i+1)-'0'); // valid 2 digit code, from 10
            if (d<27 && d>=10) {
                dp[i] += dp[i+2];
            }
        }
        return dp[0];
    }
    public static void main(String[] args)
    {
        System.out.println(new DecodeWays().numDecodings("26")==2);
        System.out.println(new DecodeWays().numDecodings("127")==2);
        System.out.println(new DecodeWays().numDecodings("2")==1);
        System.out.println(new DecodeWays().numDecodings("")==0);
        System.out.println(new DecodeWays().numDecodings("0")==0);
        System.out.println(new DecodeWays().numDecodings("10")==1);
        System.out.println(new DecodeWays().numDecodings("100")==0);
        System.out.println(new DecodeWays().numDecodings("01")==0);
        System.out.println(new DecodeWays().numDecodings("30")==0);
    }
}
