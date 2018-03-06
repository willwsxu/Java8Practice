package leetcode.dpspecial;

import java.util.ArrayList;

/* Write a program to find the n-th ugly number. 
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers. 
Note that 1 is typically treated as an ugly number, and n does not exceed 1690. 
*/
public class UglyNumber {
	// any number in sequence must be some number before * 2, or 3 or 5
    static public int nthUglyNumber(int n) {
        ArrayList<Integer> ugly=new ArrayList<>();
        ugly.add(1);
        int i=0, j=0, k=0;
        while (ugly.size()<n) {
        	int next=Integer.min(ugly.get(i)*2, ugly.get(j)*3);
        	next = Integer.min(next,  ugly.get(k)*5);
        	// one or more of following can be true
        	if (next==ugly.get(i)*2)
        		i++;
        	if (next==ugly.get(j)*3)
        		j++;
        	if (next==ugly.get(k)*5)
        		k++;
        	ugly.add(next);
        }
        //System.out.println(ugly);
        return ugly.get(n-1);
    }
    public static void main(String[] args)
    {
    	System.out.println(nthUglyNumber(10)==12);
    }

}
