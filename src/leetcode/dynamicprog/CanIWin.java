package leetcode.dynamicprog;

import java.util.Arrays;

// two players might take turns drawing from a common pool of numbers of 1 to maxChoosableInteger
// without replacement until they reach a total >= desiredTotal.
// determine if the first player to move can force a win
public class CanIWin {

	int fullmask;
	int dp[];
    public boolean canIWin(int maxChoosableInteger, int mask, int total, int desiredTotal) {
    	if (total>=desiredTotal || mask==fullmask) // not allowed to pick any number
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
    		return maxChoosableInteger%2>0; // odd wins
		if (desiredTotal<maxChoosableInteger)  // win with 1 move
			return true;
		
    	fullmask=(1<<maxChoosableInteger)-1;
    	dp=new int[fullmask+1];
    	Arrays.fill(dp,  -1);
    	return canIWin(maxChoosableInteger, 0, 0, desiredTotal);
    }

    // Given an array of scores that are non-negative integers. Player 1 picks one of the numbers 
    // from either end of the array followed by the player 2 and then player 1 and so on
    // predict whether player 1 is the winner.
    // 1 <= length of the array <= 20. 
    // Any scores in the given array are non-negative integers and will not exceed 10,000,000.
    // If the scores of both players are equal, then player 1 is still the winner.
    int memo[][];
    int PredictTheWinner(int[] nums, int begin, int end) {
        if (begin==end)
        	return nums[begin];
        if (memo[begin][end]==Integer.MIN_VALUE) { // add dp, beat 88% vs 12% without
		    int pick1=nums[begin]-PredictTheWinner(nums, begin+1, end);
		    int pick2=nums[end]-PredictTheWinner(nums, begin, end-1);
		    memo[begin][end]=Integer.max(pick1, pick2);
        }
        return memo[begin][end];
    }
    public boolean PredictTheWinner(int[] nums) {
    	memo=new int[nums.length][nums.length];
    	for (int[] r: memo)
    		Arrays.fill(r, Integer.MIN_VALUE);
        return PredictTheWinner(nums, 0, nums.length-1)>=0;
    }
    public static void main(String[] args)
    {
    	System.out.println(new CanIWin().canIWin(10, 11)==false);
    	System.out.println(new CanIWin().canIWin(10, 20)==true);
    	System.out.println(new CanIWin().canIWin(3, 7)==false);
    	System.out.println(new CanIWin().canIWin(3, 6)==true);
    	System.out.println(new CanIWin().canIWin(10, 0)==true);
    	System.out.println(new CanIWin().canIWin(10, 1)==true);
    	
    	System.out.println(new CanIWin().PredictTheWinner(new int[] {1,5,2})==false);
    	System.out.println(new CanIWin().PredictTheWinner(new int[] {1, 5, 233, 7})==true);
    	System.out.println(new CanIWin().PredictTheWinner(new int[] {0})==true);
    }
}
