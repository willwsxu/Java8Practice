package leetcode.math;

import java.util.Arrays;

public class Counting {
	
	// Given a non-negative integer n, count all numbers with unique digits, x, where 0 <= x < 10^n
    static public int countNumbersWithUniqueDigits(int n) {
    	int count[]=new int[11]; // n from 0 to 10
    	count[0]=1;
    	int ans=9;
    	count[1]=count[0]+ans;
        int digits=9;
        for (int i=2; i<11; i++) {
        	ans *= digits--;  			// count of i digits
        	count[i] = count[i-1]+ans;  // dp method
        }    	
        //System.out.println(Arrays.toString(count));
        if (n>10)
        	n=10;
        return count[n];
    }

    public static void main(String[] args)
    {
    	System.out.println(countNumbersWithUniqueDigits(2)==91);
    	System.out.println(countNumbersWithUniqueDigits(3)==739);
    }
}
