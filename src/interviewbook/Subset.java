/*
 * find all subset from 1 to n
 */
package interviewbook;

import static java.lang.System.out;
import java.util.Stack;

public class Subset {
    
    Stack<Integer> s=new Stack<>();
    long count=0;
    void recurse(int k, int n)
    {
        //out.println("<<<in "+k);
        if (k>n) {
            count++;
            out.println(s.toString()+" count "+count);
        }
        else {
            recurse(k+1, n);
            s.push(k);    
            //out.println("push "+s);
            recurse(k+1, n);
            //out.println("pop "+s);       
            s.pop();     
        }
        //out.println(">>out "+k);
    }
    
    public static void main(String[] args)
    {
        new Subset().recurse(1, 2);
        new Subset().recurse(1, 3);
    }
}
