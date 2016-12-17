/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8practice;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.*;
import java.util.function.Predicate;
/**
 *
 * @author Andy
 */

interface Fly
{
    default public void speed()
    {
        System.out.println("Fly speed");        
    }
}
interface Run
{
    default public void speed()
    {
        System.out.println("Run speed");        
    } 
}
interface Walk
{
    public void speed();
}
interface Pace
{
    public void speed();
}
// cannot implement two inetrfaces with same method name if one has default implementation
abstract class Dual implements Pace, Walk
{
//    abstract public void speed();
}

class Base
{
    private void testOverride(){}
    protected void testOverride2(){} // override works for all except private
}
public class OcaTest extends Base implements Fly, Run {
    
    @Override
    public void speed()
    {
        System.out.println("OcaTest speed");
    }

    //@Override
    public  void testOverride(){}
    
    @Override
    public  void testOverride2(){}
    
    void testException()
    {
        System.out.println(LocalTime.now());
        try {
            String nil=null;
            char c=nil.charAt(2);
        }catch(ArithmeticException e)
        {
        }
        finally
        {
            System.out.println("Finally");
        }
        
        System.out.println("Not executed");
    }
    
    void testAutoBoxing()
    {
        List<Integer> ints=Arrays.asList(new Integer(1), null);
        for (Integer i: ints)  // no exception
            System.out.println(i);
        try {
            for (int i: ints)       // exception due to autoboxing
                System.out.println(i);
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args)
    {
        new OcaTest().testAutoBoxing();
        System.out.println("AutoBoxing End");
        Stream<Integer> stream = Stream.of(1,10,100,1000);
        stream.filter(i->i<1000) //do nothing
            .peek(System.out::println);  // do nothing
        // previous stream is closed
        System.out.println("stream 2 start");
        stream = Stream.of(1,10,100,1000);
        Predicate<Integer> p1 = i -> i <1000; // need import
        Predicate<Integer> p2= i -> i >1;
        Predicate<Integer> p3 = p1.and(p2);
        ArrayList<Integer> list=new ArrayList();
        stream.filter(p3)
            .peek(list::add)
            .peek(System.out::println)
            .forEach(list::add);  // final operation
        System.out.println("lise size="+list.size());
        list.stream().forEach(System.out::println);
    }
}
