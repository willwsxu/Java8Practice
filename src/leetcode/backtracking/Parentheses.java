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

    public List<String> generateParenthesis(int n) {
		List<String> ans=new ArrayList<>();
		generator(ans, n, 0, 0, "");
		return ans;
	}
    public static void main(String[] args)
    {
    	System.out.println(new Parentheses().generateParenthesis(3));
    }
}
