package leetcode.dpn2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz"
// find out how many unique non-empty substrings of p are present in s
// p consists of only lowercase English letters and the size of p might be over 10000
public class WrapAroundSubstr {
    static public int findSubstringInWraproundString_v1(String p) { // TLE
        Set<String> ans=new HashSet<>();
        int start=0; // continueous substring
        for (int i=0; i<p.length(); i++) {
        	ans.add(p.substring(i,1+i));
        	if (i-start>=1) {
        		if ((26+p.charAt(i)-p.charAt(i-1))%26==1) {
        			for (int j=start; j<i; j++)
        				ans.add(p.substring(j, i+1));  // new substring ends at i
        		}
        		else
        			start=i;  // start of new substring
        	}
        }
        return ans.size();
    }

    static public int findSubstringInWraproundString(String p) {
    	int count[]=new int[26]; // max len of substring starting from a to z
    	Arrays.fill(count, 0);
        int start=0; // continueous substring
        for (int i=0; i<p.length(); i++) {
        	if (i-start>=1) {
        		if ((26+p.charAt(i)-p.charAt(i-1))%26!=1) { // start of new substring
        			int idx=p.charAt(start)-'a';
        			count[idx] = Integer.max(count[idx], i-start);
        			start=i;
        		}
        	}
        }
        if (!p.isEmpty()) {
        	int idx=p.charAt(start)-'a';
        	count[idx] = Integer.max(count[idx], p.length()-start);
        }
        int sum=0;
        for (int n: count)
        	sum += n*(n+1)/2;
        //System.out.println(Arrays.toString(count));
        return sum;
    }
    public static void main(String[] args)
    {
    	System.out.println(findSubstringInWraproundString("a")==1);
    	System.out.println(findSubstringInWraproundString("")==0);
    	System.out.println(findSubstringInWraproundString("cac"));
    	System.out.println(findSubstringInWraproundString("zab"));
    }
}
