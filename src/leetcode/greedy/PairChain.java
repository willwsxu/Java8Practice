package leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// You are given n pairs of numbers. In every pair, the first number is always smaller than the second number. 
// we define a pair (c, d) can follow another pair (a, b) if and only if b < c
// similar to interval scheduling problem
public class PairChain {
    static public int findLongestChain(int[][] pairs) {
        //Comparator<int[]> cmp=(c1,c2)->c1[1]-c2[1];
        Arrays.sort(pairs, (c1,c2)->c1[1]-c2[1]);
        int ans=1;
        int prev=pairs[0][1];
        for (int i=1; i<pairs.length; i++)
        {
        	if (pairs[i][0]>prev) {
        		ans++;
        		prev=pairs[i][1];
        	}
        }
        return ans;
    }
    
    static public int findLongestChain2(int[][] pairs) {  // sort by first elem in par is more complicated
        Arrays.sort(pairs, (p1, p2)->p1[0]-p2[0]);
        int len = 0;
        int pre = Integer.MIN_VALUE;
        for(int[] pair : pairs){
            if(pair[0] > pre){  // not overlap
                len++;
                pre = pair[1];
             }else if(pair[1] < pre){ // overlap but with smaller second element
                pre = pair[1];
            }
        }
        return len;
    }
    public static void main(String[] args)
    {
    	int[][] pairs=new int[][] {{1,2},{2,3},{3,4}};
    	System.out.println(findLongestChain(pairs)==2);
    	
    	pairs=new int[][] {{1,6},{2,3},{4,5}};
    	System.out.println(findLongestChain(pairs)==2);
    	System.out.println(findLongestChain2(pairs)==2);
    }
}
