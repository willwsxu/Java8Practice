/*
 * Given a non negative integer number num. For every numbers i in the range 
 * 0 ≤ i ≤ num calculate the number of 1's in their binary representation
 */
package leetcode.dpspecial;

import java.util.Arrays;

public class CountBits {
    static public int[] countBits(int num) {
        int ans[]=new int[num+1];
        ans[0]=0;
        for (int i=1; i<=num; i++) {
            ans[i]=ans[i>>1]+(i&1);   // find last bit, add to a previous number
            
            //ans[i]=ans[i&(i-1)]+1;  // equally nice idea
        }
        return ans;
    }
    
    public static void main(String[] args)
    {
        System.out.println(Arrays.toString(countBits(5)));
    }
}
