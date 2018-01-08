/*
 * Annie oop test answer key
 */
package ooptest;

public class Quiz1 {
    
    // 1 True
    // 2 False, identical usually mean objects are the same. obj1=obj2. if we want to compare valaue, overrider equals method
    // 3 False
    // 4 True, ask teacher or studuent in class to confirm the exact definition of method header
    // 5 True
    // 10 public int getValue(String s)
    // 11 display -3   -2   -1   0  (4 lines)
    // 12  1)int   2) String  3) int  4) boolean  5)double  6) Object  (? discuss teacher or friend on the last one )

    // 13
    public void total (int i)  // missing return type, param type
    {
        int total=0; // missing type declaration
        for (int j=2; j<i; j++) {  // j++, not j+
            total = total+j;
        }
        // extra }
        // print total or return total
    }
    
    // 14 google it, mean none static data member of a class
    
    // 17 method don't set values of interval variables. discuss with your friend or teacher
    // recommend reading 
    // method modifiers, class modifiers
    // static variable vs instance variable
    // immutable object
    // pass by value vs pass by reference
    // constructor initilization
    // method overloading
    // method override
    // variable scope and hiding
    // inner class
}
