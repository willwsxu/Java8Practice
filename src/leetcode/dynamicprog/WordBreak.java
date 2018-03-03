/*
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words. 
 * You may assume the dictionary does not contain duplicate words.
 */
package leetcode.dynamicprog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n=s.length();
        boolean dp[]=new boolean[n+1]; // need extra to store last word break
        dp[0]=true;  //
        for (int pos=1; pos<=n; pos++) {
            for (int start=pos-1; start>=0; start--) { // go backwards to find good word
                if (dp[start]) { //previous work break
                    if (wordDict.contains(s.substring(start, pos))) {
                        dp[pos]=true; // find a word from last break
                        break;
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    public boolean wordBreakRecur(String s, List<String> wordDict) {
    	dpBreak=new int[s.length()+1];
    	Arrays.fill(dpBreak,  -1);
    	dpBreak[s.length()]=1;
    	return wordBreak(s, wordDict, 0);
    }

    int dpBreak[];
    private boolean wordBreak(String s, List<String> wordDict, int pos) {
        if (pos>s.length())
            return false;
        if (dpBreak[pos]>=0)
        	return dpBreak[pos]>0;
        boolean good=false;
        for (String word : wordDict) {
            if (s.startsWith(word, pos)) {
                if (wordBreak(s, wordDict, pos+word.length())) {
                    good = true;
                    break;
                }
            }
        }
        dpBreak[pos]=good?1:0;
        return good;
    }
    public static void main(String[] args)
    {
        String s="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        ArrayList<String> dict=new ArrayList<>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"));
        System.out.println(new WordBreak().wordBreak(s, dict));
        System.out.println(new WordBreak().wordBreakRecur(s, dict));
        
        s="leetcode";
        dict=new ArrayList<>(Arrays.asList("leet","code"));
        System.out.println(new WordBreak().wordBreak(s, dict));
        System.out.println(new WordBreak().wordBreakRecur(s, dict));
    }
}
