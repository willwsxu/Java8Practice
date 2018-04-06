package leetcode.dynamicprog;

// Give stock price each day, you can buy and sell any time but cannot buy again without sell 
// each transaction must pay a fee
// 0 < prices.length <= 50000, which mean we need to better than O(n^2)
public class BuyStock {

	// prev i days ends with a buy or sell
	// dpBuy[i]=max(dpBuy[i-1], dpSell[i-1]-price), buy at i or before i
	// dpSell[i]=max(dpSell[i-1], dpBuy[i-1]+price-fee), sell at i or before i
    private int maxProfit(int[] prices, int day, int prevBuy, int fee) { // backtracking dp is too slow
        if (day==prices.length)
        	return 0;
    	int profit=maxProfit(prices, day+1, prevBuy, fee); // skip a day
    	if (prevBuy<0) {
    		profit = Integer.max(profit, maxProfit(prices, day+1, day, fee) );
    	} else {
    		int gain=prices[day]-prices[prevBuy]-fee;
    		if (gain>0)
    			profit = Integer.max(profit, gain+maxProfit(prices, day+1, -1, fee) );    		
    	}
    	return profit;
    }
    public int maxProfit(int[] prices, int fee) {//beat 38%, not difference to use O(n) or O(1) memory
        //return maxProfit(prices, 0, -1, fee);
    	int dpBuy=Integer.MIN_VALUE/2;  // use half max to avoid overflow
    	int dpSell=0;
    	for (int p : prices) {
    		int prevBuy=dpBuy;  // save it for later use
    		dpBuy = Integer.max(dpBuy, dpSell-p);
    		dpSell = Integer.max(dpSell, prevBuy+p-fee);
    	}
    	return Integer.max(dpBuy, dpSell);
    }
    
    public static void main(String[] args)
    {
    	int ans=new BuyStock().maxProfit(new int[] {1, 3, 2, 8, 4, 9}, 2);
    	System.out.println(ans==8);
    }
}
