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
    boolean dp[];
    public boolean wordBreak(String s, List<String> wordDict) {
        int n=s.length();
        dp=new boolean[n+1]; // need extra to store last word break
        dp[0]=true;  //
        for (int pos=1; pos<=n; pos++) {
            for (int start=pos-1; start>=0; start--) {
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
    
    // backtracking too slow
    private boolean wordBreak(String s, List<String> wordDict, int pos) {
        if (pos==s.length()) {
            return true;
        }
        for (String word : wordDict) {
            if (s.startsWith(word, pos)) {
                //System.out.println(word+" at "+pos);
                if (wordBreak(s, wordDict, pos+word.length()))
                    return true;
            }
        }
        return false;
    }
    public static void main(String[] args)
    {
        String s="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        ArrayList<String> dict=new ArrayList<>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"));
        System.out.println(new WordBreak().wordBreak(s, dict));
        s="leetcode";
        dict=new ArrayList<>(Arrays.asList("leet","code"));
        System.out.println(new WordBreak().wordBreak(s, dict));
    }
}
