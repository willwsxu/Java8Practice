/*
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T), 
 * find all unique combinations in C where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 */
package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComboSum {
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> combo=new ArrayList<>();
        List<List<Integer>> ans=new ArrayList<>();
        combinationSum(candidates, target, combo, ans, 0);
        return ans;
    }
    private void combinationSum(int[] candidates, int target, List<Integer> combo, List<List<Integer>> ans, int index) {
        if (target==0) {
            ans.add (new ArrayList<>(combo));
            //System.out.println(combo);
            return;
        }
        if (target<0)
            return;
        for (int i=index; i<candidates.length; i++) {
            combo.add(candidates[i]);
            combinationSum(candidates, target-candidates[i], combo, ans, i); // repeat try current index
            combo.remove(combo.size()-1); 
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> combo=new ArrayList<>();
        List<List<Integer>> ans=new ArrayList<>();
        combinationSum2(candidates, target, combo, ans, 0);
        return ans;        
    }
    private void combinationSum2(int[] candidates, int target, List<Integer> combo, List<List<Integer>> ans, int index) {
        if (target==0) {
            ans.add (new ArrayList<>(combo));
            return;
        }
        if (target<0 || index==candidates.length)
            return;
        for (int i=index; i<candidates.length; i++) {
            if (i>index && candidates[i]==candidates[i-1]) // same number only iterate once
                continue;
            combo.add(candidates[i]);
            combinationSum2(candidates, target-candidates[i], combo, ans, i+1);
            combo.remove(combo.size()-1); 
        }
    }
    public static void main(String[] args)
    {
        //List<List<Integer>> ans=new ComboSum().combinationSum(new int[]{2,3,6,7}, 7);
        //List<List<Integer>> ans=new ComboSum().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        List<List<Integer>> ans=new ComboSum().combinationSum2(new int[]{2,2,2,2,2,2,2}, 6);
        for (List<Integer> li: ans)
            System.out.println(li);
    }
}
