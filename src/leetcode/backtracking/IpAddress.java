package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

// Given a string containing only digits, restore it by returning all possible valid IP address combinations
public class IpAddress {

	void backtracking(List<String> ans, String ip, String src, int pos, int count)
	{
		if (count==4) {
			if (pos==src.length())  // all digits used
				ans.add(ip);
			return;
		}
		for (int i=1; i<=3 && pos+i<=src.length(); i++) { // at most 3 digits is used in IP
			if (src.charAt(pos)=='0' && i>1)  // don't allow 0##
				break;
			String p=src.substring(pos, pos+i);
			if (Integer.parseInt(p)>255)  // range between 0 to 255
				break;
			if (!ip.isEmpty()) 
				p='.'+p;
			backtracking(ans, ip+p, src, pos+i, count+1);	
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
