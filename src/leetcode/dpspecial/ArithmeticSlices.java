/*
 * A sequence of number is called arithmetic if it consists of at least three 
 * elements and if the difference between any two consecutive elements is same
 * a slice is continuous segment of an array
 */
package leetcode.dpspecial;

public class ArithmeticSlices {
    static public int numberOfArithmeticSlices(int[] A) {
        int sum=0;
        int addedSlice=0;
        for (int i=2; i<A.length; i++) {
            if (A[i]-A[i-1]==A[i-1]-A[i-2]) // extend continuous sequence 
            {
                addedSlice += 1;  // extend one elem, create additional slices
                sum += addedSlice;        
            }
            else
                addedSlice=0;
        }
        return sum;
    }
    
    public static void main(String[] args)
    {
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3, 4}));
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3, 5}));
    }
}
