/*
 * Given a positive integer n, find the least number of perfect square numbers 
 * (for example, 1, 4, 9, 16, ...) which sum to n
 */
package leetcode.dynamicprog;

public class PerfectSquare {
    public int numSquares(int n) {  // same as min coin change
        int MAX_VAL=n+1;
        int count[]=new int[MAX_VAL];
        count[0]=0;
        for (int i=1; i<=n; i++) {
            count[i]=MAX_VAL;
            for (int j=1; j<=i; j++) {
                int square=j*j;
                if (square>i)  // no more
                    break;
                count[i] = Integer.min(count[i], count[i-square]+1);
            }
        }
        return count[n];
    }
    
    private int numSquares(int n, int idx) {
        if (n<0)
            return Integer.MAX_VALUE;
        if (n==0)
            return 0;
        int count=1+numSquares(n-idx*idx, idx);
        count = Integer.min(count, 1+numSquares(n-idx*idx, idx+1));        
        count = Integer.min(count, numSquares(n, idx+1));
        return count;
    }
    public static void main(String[] args)
    {
        System.out.println(new PerfectSquare().numSquares(13)==2);
        System.out.println(new PerfectSquare().numSquares(12)==3);
        System.out.println(new PerfectSquare().numSquares(1000000));
    }
}
