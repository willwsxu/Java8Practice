/*
 * Given a digit string, return all possible letter combinations that the number could represent.
 */
package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

// simple backtracking
public class phoneLetter {
    void recurse(String digits, int i, String letters, List<String> ans)
    {
        if (digits.length()==i) {
            if (!letters.isEmpty())
                ans.add(letters);
            return;
        }
        switch (digits.charAt(i)) {
            case '0': recurse(digits, i+1,letters+" ", ans); break;
            case '1': recurse(digits, i+1,letters+"1", ans); break;
            case '2': 
                recurse(digits, i+1,letters+"a", ans); 
                recurse(digits, i+1,letters+"b", ans); 
                recurse(digits, i+1,letters+"c", ans); 
                break;
            case '3': 
                recurse(digits, i+1,letters+"d", ans); 
                recurse(digits, i+1,letters+"e", ans); 
                recurse(digits, i+1,letters+"f", ans); 
                break;
            case '4': 
                recurse(digits, i+1,letters+"g", ans); 
                recurse(digits, i+1,letters+"h", ans); 
                recurse(digits, i+1,letters+"i", ans); 
                break;
            case '5': 
                recurse(digits, i+1,letters+"j", ans); 
                recurse(digits, i+1,letters+"k", ans); 
                recurse(digits, i+1,letters+"l", ans); 
                break;
            case '6': 
                recurse(digits, i+1,letters+"m", ans); 
                recurse(digits, i+1,letters+"n", ans); 
                recurse(digits, i+1,letters+"o", ans); 
                break;
            case '7': 
                recurse(digits, i+1,letters+"p", ans); 
                recurse(digits, i+1,letters+"q", ans); 
                recurse(digits, i+1,letters+"r", ans); 
                recurse(digits, i+1,letters+"s", ans); 
                break;
            case '8': 
                recurse(digits, i+1,letters+"t", ans); 
                recurse(digits, i+1,letters+"u", ans); 
                recurse(digits, i+1,letters+"v", ans); 
                break;
            case '9': 
                recurse(digits, i+1,letters+"w", ans); 
                recurse(digits, i+1,letters+"x", ans); 
                recurse(digits, i+1,letters+"y", ans); 
                recurse(digits, i+1,letters+"z", ans); 
                break;
                
        }
    }
    public List<String> letterCombinations(String digits) {
        List<String> ans=new ArrayList<>();
        recurse(digits, 0, "", ans);
        return ans;
    }
    public static void main(String[] args)
    {
        System.out.println(new phoneLetter().letterCombinations("23"));
        System.out.println(new phoneLetter().letterCombinations(""));
    }
}
