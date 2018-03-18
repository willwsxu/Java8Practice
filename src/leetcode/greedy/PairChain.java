package leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// You are given n pairs of numbers. In every pair, the first number is always smaller than the second number. 
// we define a pair (c, d) can follow another pair (a, b) if and only if b < c
public class PairChain {
    static public int findLongestChain(int[][] pairs) {
        Comparator<int[]> cmp=(c1,c2)->c1[1]-c2[1];
        Arrays.sort(pairs, cmp);
        int ans=1;
        int[] prev=pairs[0];
        for (int i=1; i<pairs.length; i++)
        {
        	if (pairs[i][0]>prev[1]) {
        		ans++;
        		prev=pairs[i];
        	}
        }
        return ans;
    }
    public static void main(String[] args)
    {
    	int[][] pairs=new int[][] {{1,2},{2,3},{3,4}};
    	System.out.println(findLongestChain(pairs)==2);
    }
}
