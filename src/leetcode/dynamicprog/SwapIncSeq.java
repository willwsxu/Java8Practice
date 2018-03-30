package leetcode.dynamicprog;

import java.util.Arrays;

// We are allowed to swap elements A[i] and B[i]
// return the minimum number of swaps to make both sequences strictly increasing
// A, B are arrays with the same length, and that length will be in the range [1, 1000].
// A[i], B[i] are integer values in the range [0, 2000].
//  It is guaranteed that the given input always makes it possible.
public class SwapIncSeq {

	void swap(int A[], int B[], int p)
	{
		A[p] ^= B[p];
		B[p] ^= A[p];
		A[p] ^= B[p];
	}
	int dp[][];
    public int minSwap(int[] A, int[] B, int pos, int swapprev) {
        if (pos==A.length)
        	return 0;
        if (dp[swapprev][pos]>=0)
        	return dp[swapprev][pos];
        if (A[pos]==B[pos]) {
        	dp[swapprev][pos] = minSwap(A, B, pos+1, 0);
        	return dp[swapprev][pos];
        }
        int v1=Integer.MAX_VALUE/2;
        int prevA=0;
        int prevB=0;
        if (pos>0 && swapprev>0 ) {
        	prevA=B[pos-1];
        	prevB=A[pos-1];
        } else if (pos>0) {
        	prevA=A[pos-1];
        	prevB=B[pos-1];
        }
        if (pos==0 || A[pos]>prevA&&B[pos]>prevB)
        	v1 = minSwap(A, B, pos+1, 0);
        //swap(A, B, pos);
        int v2=Integer.MAX_VALUE/2;
        if (pos==0 || A[pos]>prevB&&B[pos]>prevA)
        	v2=1+minSwap(A, B, pos+1, 1);
        //swap(A, B, pos);
        dp[swapprev][pos] = Integer.min(v1, v2);
        return dp[swapprev][pos];
    }
    public int minSwap(int[] A, int[] B) {
    	dp=new int[2][A.length];  // 0 no swap, 1 swap
    	Arrays.fill(dp[0], -1);
    	Arrays.fill(dp[1], -1);
    	int ans= minSwap(A, B, 0, 0);
    	//System.out.println(Arrays.toString(dp));
    	return ans;
    }
    public static void main(String[] args)
    {
    	int ans=new SwapIncSeq().minSwap(new int[] {1,3,5,4}, new int[] {1,2,3,7});
    	System.out.println(ans==1);
    	
    	ans=new SwapIncSeq().minSwap(new int[] {3,3,8,9,10}, new int[] {1,7,4,6,8});
    	System.out.println(ans==1);

    	ans=new SwapIncSeq().minSwap(new int[] {0,4,4,5,9}, new int[] {0,1,6,8,10});
    	System.out.println(ans);
    }
}
