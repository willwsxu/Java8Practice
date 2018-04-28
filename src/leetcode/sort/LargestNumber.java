package leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//Given a list of non negative integers, arrange them such that they form the largest number
public class LargestNumber {
    static public String largestNumber(int[] nums) {
    	String[] strs=new String[nums.length];
    	boolean zeroall=true;
    	for (int i=0; i<nums.length; i++) {
    		strs[i]=Integer.toString(nums[i]);
    		if (nums[i]!=0)
    			zeroall=false;
    	}
    	if (zeroall)  // special case
    		return "0";
    	Comparator<String> cmp=(s1,s2)->{
    		return (s2+s1).compareTo(s1+s2);  // sort as s2, s1 if s2+s1 is greater
    	};
    	Arrays.sort(strs, cmp);
    	StringBuilder sb = new StringBuilder();
    	for (String s: strs)
    		sb.append(s);
    	return sb.toString();
    }
    public static void main(String[] args)
    {
    	System.out.println(largestNumber(new int[] {3,30,34,5,9}).equals("9534330"));
    	System.out.println(largestNumber(new int[] {10,2}).equals("210"));
    	System.out.println(largestNumber(new int[] {303,343,30,34,5,9}).equals("953434330330"));
    	System.out.println(largestNumber(new int[] {0,0}).equals("0"));
    }
}
