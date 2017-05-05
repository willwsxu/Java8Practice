
package interviewbook;

import static java.lang.System.out;
import java.util.Arrays;


public class StairWays {
    
    long dp[];
    int N;
    StairWays(int n)
    {
        this.N=n;
        dp =new long[n+1];
    }
    long topdown()
    {
        return stairWays(N);
    }
    long stairWays(int n)
    {
        if (n<0)
            return 0;
        if (n==0)
            return 1;
        if ( dp[n]>0)
            return dp[n];
        dp[n]=stairWays(n-1)+stairWays(n-2)+stairWays(n-3);
        return dp[n];
    }
    long bottomup()
    {
        //Arrays.fill(dp, 0);
        dp[0]=1;
        dp[1]=1;
        dp[2]=2;
        for (int i=3; i<=N; i++)
            dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
        return dp[N];
    }
    
    public static void main(String[] args)
    {
        out.println(new StairWays(3).topdown());
        out.println(new StairWays(4).topdown());
        out.println(new StairWays(10).topdown());
        out.println(new StairWays(40).topdown());
        
        out.println(new StairWays(3).bottomup());
        out.println(new StairWays(4).bottomup());
        out.println(new StairWays(10).bottomup());
        out.println(new StairWays(40).bottomup());
    }
}
