package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

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
    
	void generator(List<String> all, String input, int idx, int n, int open, int close, boolean canClose, String output) {
		//System.out.println(idx+" "+open+" "+close +" "+canClose+" "+output);
		if (n==open && close==n ) // done
		{
			all.add(output);
			return;
		}
		if (idx==input.length()) {
			all.add(output+")");
			return;
		}
		if (open<n) {
			if (Character.isDigit(input.charAt(idx)))
				generator(all, input, idx, n, open+1, close, false, output+"(");
			else
				generator(all, input, idx+1, n, open+1, close, false, output+input.charAt(idx)+"(");
		}
		if (open>close) {
			if (!Character.isDigit(input.charAt(idx))) 
				generator(all, input, idx+1, n, open, close, true, output+input.charAt(idx));
			else if (idx<input.length())  {
				//System.out.println("before "+output);
				while (idx<input.length() && Character.isDigit(input.charAt(idx))) {
					output += input.charAt(idx++);
				}
				//System.out.println("after "+output);
				if (canClose)
					generator(all, input, idx, n, open, close+1, true, output+")");
				else
					generator(all, input, idx, n, open, close, false, output);
			}
		}
	}
	// Given a string of numbers and operators, return all possible results from computing all the different 
	// possible ways to group numbers and operators. The valid operators are +, - and *.
    public List<Integer> diffWaysToCompute(String input) { // incorrect
    	int operators=0;
        for (int i=0; i<input.length(); i++)
        	if (!Character.isDigit(input.charAt(i)))
        		operators++;
        List<Integer> ans=new ArrayList<>();
        if (operators==0) {
        	ans.add(Integer.parseInt(input));
        	return ans;
        }

        List<String> formula=new ArrayList<>();
        generator(formula, input,0,operators, 0,0,false, new String());
        System.out.println(formula);
        return ans;
    }
    public static void main(String[] args)
    {
    	System.out.println(new Parentheses().generateParenthesis(3));    	

    	System.out.println(new Parentheses().diffWaysToCompute("3"));
    	System.out.println(new Parentheses().diffWaysToCompute("3+4"));
    	System.out.println(new Parentheses().diffWaysToCompute("2-1-1"));
    }
}
