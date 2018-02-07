/*
 * Given a string, find the length of the longest substring without repeating characters
 */
package interviewbook;

import static java.lang.System.out;


public class LongestSubStrNoRept {
    
    static String solve(String s) //O(n *26)
    {
        int start=0;
        int maxStart=0;
        int maxLen=0;
        for (int i=0; i< s.length(); i++) {
            char cur=s.charAt(i);
            int len=i-start; // len of current substring, exclude current char
            // look for repeating char in current sub string
            for (int j=start; j<i; j++)  // loop in [start,i)
            {
                if (s.charAt(j)==cur) { // find repeat
                    if (maxLen<len) { // update longest substring
                        maxLen=len;
                        maxStart=start;
                    }
                    start=j+1;  // start of next sub string
                    //out.println("new start "+start+" i="+i);
                    break;
                }
            }
        }
        int len=s.length()-start;
        if (maxLen<len) {
            maxLen=len;
            maxStart=start;
        }
        return s.substring(maxStart, maxStart+maxLen);
    }
    
    
    public static void main(String[] args)
    {
        out.println(solve("abcd"));
        out.println(solve("aaabcd"));
        out.println(solve("aaabcdaxyzzz"));
    }
}
