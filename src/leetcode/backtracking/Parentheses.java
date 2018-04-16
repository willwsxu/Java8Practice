package leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parentheses {
	
	void generator(List<String> all, int n, int open, int close, String par) {
		if (n==open && close==n ) // done
		{
			all.add(par);
			return;
		}
		if (open<n)
			generator(all, n, open+1, close, par+"(");
		if (open>close)
			generator(all, n, open, close+1, par+")");
	}

    public List<String> generateParenthesis(int n) {//beat 88%
		List<String> ans=new ArrayList<>();
		generator(ans, n, 0, 0, "");
		return ans;
	}
    
	// Given a string of numbers and operators, return all possible results from computing all the different 
	// possible ways to group numbers and operators. The valid operators are +, - and *.
    public List<Integer> diffWaysToCompute(String input) { // beat 68% before adding memo
    	Map<String, List<Integer>> memo= new HashMap<>(); // hashmap storage overhead can negate performance savings if not enough overlapping substring
    	return diffWaysToCompute(input, memo);
    }
    private List<Integer> diffWaysToCompute(String input, Map<String, List<Integer>> memo) {// didn't see speed up after adding memo
    	List<Integer> ans=memo.get(input);
        if (ans!=null)
        	return ans;  // computed before
    	ans = new ArrayList<>();
        for (int i=0; i<input.length(); i++) {
        	if (!Character.isDigit(input.charAt(i))) {  // find an operator
            	String left=input.substring(0, i);  // divide into two parts
            	String right=input.substring(i+1, input.length());
        		List<Integer> ansL=diffWaysToCompute(left); // find answer left side
        		List<Integer> ansR=diffWaysToCompute(right);// find answer right side
        		for (int L: ansL) {		// combine answers from each side, applying the operator
        			for (int R: ansR) {
        				switch (input.charAt(i)) {
        				case '+':	ans.add(L+R);	break;
        				case '-':	ans.add(L-R);	break;
        				case '*':	ans.add(L*R);	break;
        				}
        			}
        		}
        	}
        }
        if (ans.isEmpty())  // terminate/base case, operand only
        	ans.add(Integer.parseInt(input));
        memo.put(input,  ans);  // save computed answer for a particular substring
        return ans;
    }
    public static void main(String[] args)
    {
    	System.out.println(new Parentheses().generateParenthesis(3));    	
    	
    	System.out.println(new Parentheses().diffWaysToCompute("3"));
    	System.out.println(new Parentheses().diffWaysToCompute("3+4"));
    	System.out.println(new Parentheses().diffWaysToCompute("2-1-1")); //2,0
    	System.out.println(new Parentheses().diffWaysToCompute("2*3-4*5"));//-34, -10, -14, -10, 10
    }
}
