/*
 * find all subset
 */
package interviewbook;

import static java.lang.System.out;
import java.util.Stack;

public class Subset {
    
    Stack<Integer> s=new Stack<>();
    void recurse(int k, int n)
    {
        if (k>n)
            out.println(s.toString()+k);
        else {
            recurse(k+1, n);
            s.push(k);            
            recurse(k+1, n);
            s.pop();            
        }
    }
    
    public static void main(String[] args)
    {
        new Subset().recurse(1, 2);
    }
}
