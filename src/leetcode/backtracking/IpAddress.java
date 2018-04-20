package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

// Given a string containing only digits, restore it by returning all possible valid IP address combinations
// add more pruning to improve performance
//    not too long, not too short
//    no for loop in 4th part
public class IpAddress {

	void backtracking(List<String> ans, String ip, String src, int pos, int count)
	{
		int remaining=src.length()-pos;
		if (remaining>3*(4-count))  // validate length, optimization gain 1 ms (from 5 to 4)
			return;
		if (remaining<4-count) // not enough
			return;
		if (count==3) { // use all digits
			if (src.charAt(pos)=='0' && remaining>1) // don't allow 0##
				return;
			String p=src.substring(pos, src.length());
			if (Integer.parseInt(p)>255)  // range between 0 to 255
				return;
			ans.add(ip+p);
			return;
		}
		for (int i=1; i<=3 && pos+i<=src.length(); i++) { // at most 3 digits is used in IP
			if (src.charAt(pos)=='0' && i>1)  // don't allow 0##
				break;
			String p=src.substring(pos, pos+i);
			if (Integer.parseInt(p)>255)  // range between 0 to 255
				break;
			backtracking(ans, ip+p+".", src, pos+i, count+1);	
		}
	}
    public List<String> restoreIpAddresses(String s) {
    	List<String> ans=new ArrayList<>();
    	backtracking(ans, "", s, 0, 0);
    	return ans;
    }
    public static void main(String[] args)
    {
    	List<String> ans=new IpAddress().restoreIpAddresses("25525511135");
    	System.out.println(ans);
    	ans=new IpAddress().restoreIpAddresses("010010");
    	System.out.println(ans);
    }
}
