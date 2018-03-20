package leetcode.dynamicprog;

import java.util.Arrays;

// two players might take turns drawing from a common pool of numbers of 1 to maxChoosableInteger
// without replacement until they reach a total >= desiredTotal.
// determine if the first player to move can force a win
public class CanIWin {

	int fullmask;
	int dp[];
    public boolean canIWin(int maxChoosableInteger, int mask, int total, int desiredTotal) {
    	if (total>=desiredTotal || mask==fullmask)
    		return false;
    	if (dp[mask]>=0)
    		return dp[mask]>0;
    	boolean ans=false;
    	for (int i=1; i<=maxChoosableInteger; i++) {
    		int m=1<<(i-1);
    		if ((m&mask)>0) // number is used
    			continue;
    		if (!canIWin(maxChoosableInteger, mask | m, total+i, desiredTotal))
    		{
    			ans=true; // you win when other player lose
    			break;
    		}
    	}
    	dp[mask]=ans?1:0;
        return ans;
    }
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) { // beat 90%
    	int sumAll=(1+maxChoosableInteger)*maxChoosableInteger/2; // special cases
    	if (sumAll<desiredTotal)
    		return false;
    	if (sumAll==desiredTotal)
    		return maxChoosableInteger%2>0;
		if (desiredTotal<1)
			return true;
		
    	fullmask=(1<<maxChoosableInteger)-1;
    	dp=new int[fullmask+1];
    	Arrays.fill(dp,  -1);
    	return canIWin(maxChoosableInteger, 0, 0, desiredTotal);
    }

    public static void main(String[] args)
    {
    	System.out.println(new CanIWin().canIWin(10, 11)==false);
    	System.out.println(new CanIWin().canIWin(10, 20)==true);
    	System.out.println(new CanIWin().canIWin(3, 7)==false);
    	System.out.println(new CanIWin().canIWin(3, 6)==true);
    	System.out.println(new CanIWin().canIWin(10, 0)==true);
    	System.out.println(new CanIWin().canIWin(10, 1)==true);
    }
}
