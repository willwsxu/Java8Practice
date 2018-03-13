// Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get. 
// For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4). 
// Note: You may assume that n is not less than 2 and not larger than 58. 
package leetcode.dpn2;

import java.util.Arrays;

public class IntegerBreak {
	int prod[];
    public int integerBreak(int n) {
        prod = new int[n+1];
        prod[0]=0; prod[1]=1;
        for (int i=2; i<=n; i++) {
        	int maxp=0;
        	for (int p=1; p<i; p++) {
        		int v=p*Integer.max(i-p, prod[i-p] );  // split into 2 numbers, take advantage of previous calculation
        		if (v>maxp)
        			maxp=v;
        	}
        	prod[i]=maxp;
        }
        //System.out.println(Arrays.toString(prod));
        return prod[n];
    }

    public static void main(String[] args)
    {
    	System.out.println(new IntegerBreak().integerBreak(2)==1);
    	System.out.println(new IntegerBreak().integerBreak(10)==36);
    	System.out.println(new IntegerBreak().integerBreak(58)==1549681956);
    }
}
