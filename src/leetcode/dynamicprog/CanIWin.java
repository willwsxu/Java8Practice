package leetcode.dynamicprog;

// two players might take turns drawing from a common pool of numbers of 1 to maxChoosableInteger
// without replacement until they reach a total >= desiredTotal.
// determine if the first player to move can force a win
public class CanIWin {

	int fullmask;
    public boolean canIWin(int maxChoosableInteger, int mask, int total, int desiredTotal) {
    	if (total>=desiredTotal || mask==fullmask)
    		return false;
    	
    	for (int i=1; i<=maxChoosableInteger; i++) {
    		int m=1<<(i-1);
    		if ((m&mask)>0) // number is used
    			continue;
    		if (!canIWin(maxChoosableInteger, mask | m, total+i, desiredTotal))
    			return true; // you win when other player lose
    	}
        return false;
    }
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    	fullmask=(1<<maxChoosableInteger)-1;
    	return canIWin(maxChoosableInteger, 0, 0, desiredTotal);
    }

    public static void main(String[] args)
    {
    	System.out.println(new CanIWin().canIWin(10, 11));
    	System.out.println(new CanIWin().canIWin(10, 20));
    }
}
