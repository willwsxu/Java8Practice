/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8practice;

/**
 *
 * @author Andy
 */
public class Initialization {
    
    static public void arrayInit()
    {
        int []ints = new int[4];  // primitive type initialize to 0
        for (int i=0; i<ints.length; i++)
            ints[i]=i+1;
        int ints2[] = {1,2,3,4};
        Integer []intergers = new Integer[4];
        for (int i=0; i<intergers.length; i++)
            intergers[i]=i+1;  // auto boxing
        Integer []intergers2 = {new Integer(1), new Integer(2), new Integer(3), new Integer(4)};
        class Test{
            public String name;
        }
        Test []tests = new Test[4];  // referference type initialize to null
        for (int i=0; i<tests.length; i++) {
            tests[i] = new Test();  // program crash with this line
            tests[i].name=""+(i+1);    
        }        
    }
}
