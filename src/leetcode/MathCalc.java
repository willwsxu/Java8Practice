/*
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT
 */
package leetcode;

public class MathCalc {
    int divideLong(long dividend, long divisor) {
        // need to deal with sign separately
        int sign=1;
        if (dividend<0) {
            dividend = -dividend;
            sign = -sign;
        }
        if (divisor<0) {
            divisor = -divisor;
            sign = -sign;
        }
        int q=0;
        while (dividend>=divisor) {
            long prod=divisor;
            int multiple=1;
            while (prod+prod<=dividend) { // include =
                multiple += multiple;
                prod += prod;
            }
            q += multiple;
            dividend -= prod;
        }
        return sign*q;
    }
    int divide(int dividend, int divisor) {
        if (divisor==0)
            return Integer.MAX_VALUE;
        else if (divisor==1)
            return dividend;
        else if (divisor==-1) {
            if (dividend==Integer.MIN_VALUE)
                return Integer.MAX_VALUE;
            return -dividend;
        }
        return divideLong(dividend, divisor);
    }
    public static void main(String[] args)
    {
        System.out.println(new MathCalc().divide(30,4)==7);
        System.out.println(new MathCalc().divide(30,0));
        System.out.println(new MathCalc().divide(0,4)==0);
        System.out.println(new MathCalc().divide(1,4)==0);
        System.out.println(new MathCalc().divide(1000000000,2)==500000000);
        System.out.println(new MathCalc().divide(4,4)==1);
        System.out.println(new MathCalc().divide(3,-2)==-1);
        System.out.println(new MathCalc().divide(-2147483648,-1)==2147483647);
        System.out.println(new MathCalc().divide(-2147483648,1)==-2147483648);
        System.out.println(new MathCalc().divide(-2147483648,-2));
        System.out.println(new MathCalc().divide(-2147483648,-2147483648)==1);
    }
}
