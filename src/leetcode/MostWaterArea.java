/*
 * n Point (i, ai), draw vertical line to (1,0), i from 1 to n
 * choose any two lines, together with x-axis form a 2D container
 * find most water held in such a container
 */
package leetcode;

public class MostWaterArea {
    static public int maxArea(int[] height) {
        int left=0;
        int right=height.length-1; // start with widest container
        int maxWater=0;
        while (left<right) {
            int area=(right-left)*Integer.min(height[left], height[right]);
            if (area>maxWater) // compute current area, and upsate max
                maxWater=area;
            // area can only get bigger if higher is larger
            if (height[left]> height[right]) // safe to choose the smaller end to narraow container
                right--;
            else
                left++;
        }
        return maxWater;
    }
    public static void main(String[] args)
    {
        int h[]=new int[]{1,3,5,7,6,4,2};
        System.out.println(maxArea(h)==12);
    }
}
