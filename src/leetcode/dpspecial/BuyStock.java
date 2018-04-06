package leetcode.dpspecial;

// Give stock price each day, you can buy and sell any time but cannot buy again without sell 

import java.util.Arrays;

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
    
    //Design an algorithm to find the maximum profit. You may complete at most two transactions
    public int maxProfit(int[] prices) { //O(n) beat 84%
        int n=prices.length-1;
        if (n<1)
            return 0;
        int diff[]=new int[n];
        for (int i=0; i<n; i++)
            diff[i]=prices[i+1]-prices[i];  // create difference array
        //System.out.println(Arrays.toString(diff));
        int maxLeft[]=new int[n+1];  // compute max from left
        int sum1=0;
        maxLeft[0]=0;
        for (int i=0; i<n; i++) {
            sum1 +=diff[i];
            if (sum1<0)
                sum1=0;  // start over
            maxLeft[i+1]=Integer.max(maxLeft[i], sum1);
        }
        //System.out.println(Arrays.toString(maxLeft));
        int maxRight[]=new int[n+1]; // compute max from right
        int sum2=0;
        maxLeft[n]=0;
        for (int i=n-1; i>=0; i--) {
            sum2 +=diff[i];
            if (sum2<0)
                sum2=0;  // start over
            maxRight[i]=Integer.max(maxRight[i+1], sum2);
        }
        //System.out.println(Arrays.toString(maxRight));
        int ans=0;
        for (int partition=0; partition<=n; partition++) {  // partition difference array in 2 parts
            ans=Integer.max(ans, maxLeft[partition]+maxRight[partition]);
            //System.out.println("p="+partition+": "+sum1+" "+sum2);
        }
        return ans;
    }
    
    public static void main(String[] args)
    {
    	int ans=new BuyStock().maxProfit(new int[] {1, 3, 2, 8, 4, 9}, 2);
    	System.out.println(ans==8);
        
        ans=new BuyStock().maxProfit(new int[] {8,6,4,3,3,2,3,5,8,3,8,2,6});
    	System.out.println(ans==11);
        
        ans=new BuyStock().maxProfit(new int[] {2,1,4,5,2,9,7});
    	System.out.println(ans==11);
       
        ans=new BuyStock().maxProfit(new int[] {1});
    	System.out.println(ans==0);
        
        ans=new BuyStock().maxProfit(new int[] {});
    	System.out.println(ans==0);
        
        ans=new BuyStock().maxProfit(new int[] {1,9});
    	System.out.println(ans==8);
    }
}
