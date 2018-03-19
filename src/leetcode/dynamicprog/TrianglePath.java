/*
 * Given a triangle, find the minimum path sum from top to bottom. 
 * Each step you may move to adjacent numbers on the row below.
 */
package leetcode.dynamicprog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrianglePath {
    int dp[][];
    int minimumTotal(List<List<Integer>> triangle, int level, int pos) {
        if (level==triangle.size()-1)
            return triangle.get(level).get(pos);
        if (dp[level][pos]!=Integer.MIN_VALUE)
            return dp[level][pos];
        int left=minimumTotal(triangle, level+1, pos);
        int right=minimumTotal(triangle, level+1, pos+1);
        dp[level][pos] = triangle.get(level).get(pos)+Integer.min(left, right);
        return dp[level][pos];
    }
    public int minimumTotal_topdown(List<List<Integer>> triangle) {
        dp=new int[triangle.size()][triangle.size()];
        for (int[] r: dp)
            Arrays.fill(r, Integer.MIN_VALUE);
        return minimumTotal(triangle, 0, 0);
    }
    public int minimumTotal(List<List<Integer>> triangle) { // bottom up, slower than top down
        int memo[]=new int[triangle.size()];
        Arrays.fill(memo, 0);
        for (int i=triangle.size()-1; i>0; i--) {
            for (int j=0; j<i; j++) {
                memo[j]=Integer.min(memo[j]+triangle.get(i).get(j), memo[j+1]+triangle.get(i).get(j+1));
            }
        }
        return memo[0]+triangle.get(0).get(0);
    }
    public static void main(String[] args)
    {
        List<List<Integer>> triangle=new ArrayList<>();
        triangle.add(new ArrayList<>(Arrays.asList(2)));
        triangle.add(new ArrayList<>(Arrays.asList(3,4)));
        triangle.add(new ArrayList<>(Arrays.asList(6,5,7)));
        triangle.add(new ArrayList<>(Arrays.asList(4,1,8,3)));
        System.out.println(new TrianglePath().minimumTotal(triangle)==11);
    }
}
