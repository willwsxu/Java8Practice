/*
 * find all subset from 1 to n
 */
package interviewbook;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Subset {
    
    Stack<Integer> s=new Stack<>();
    long count=0;
    void recurse(int k, int n)
    {
        //out.println("<<<in "+k);
        if (k>n) {
            count++;
            out.println(s.toString()+" count "+count);
        }
        else {
            recurse(k+1, n);
            s.push(k);    
            //out.println("push "+s);
            recurse(k+1, n);
            //out.println("pop "+s);       
            s.pop();     
        }
        //out.println(">>out "+k);
    }
    public List<List<Integer>> subsets(int[] nums) {
        
        List<Integer> combo=new ArrayList<>();
        List<List<Integer>> ans=new ArrayList<>();
        subsets(nums, 0, ans, combo);
        return ans;        
    }
    private void subsets(int[] nums, int k, List<List<Integer>> ans, List<Integer> set) {
        if (k==nums.length) {
            ans.add(new ArrayList<>(set));
            return;
        }
        set.add(nums[k]);
        subsets(nums, k+1, ans, set);
        set.remove(set.size()-1);
        subsets(nums, k+1, ans, set);
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<Integer> combo=new ArrayList<>();
        List<List<Integer>> ans=new ArrayList<>();
        subsetsWithDup(nums, 0, ans, combo);
        return ans;        
    }
    
    private void subsetsWithDup(int[] nums, int k, List<List<Integer>> ans, List<Integer> set) {
        ans.add(new ArrayList<>(set));
        for (int i=k; i<nums.length; i++) {
            if (i>k && nums[i]==nums[i-1])  // skip duplicate
                continue;
            set.add(nums[i]);
            subsetsWithDup(nums, i+1, ans, set);
            set.remove(set.size()-1);
        }
    }
    public static void main(String[] args)
    {
        new Subset().recurse(1, 2);
        new Subset().recurse(1, 3);
        
        //List<List<Integer>> ans=new Subset().subsets(new int[]{1,2,3});
        List<List<Integer>> ans=new Subset().subsetsWithDup(new int[]{1,2,2});
        for (List<Integer> li: ans)
            System.out.println(li);
    }
}
