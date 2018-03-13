/*
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined 
 * by its upper left corner (row1, col1) and lower right corner (row2, col2)
 */
package leetcode.prefixsum;

import java.util.Arrays;

public class NumMatrix {
    int [][]matrix;
    int [][]sum;
    public NumMatrix(int[][] matrix) {
        this.matrix=matrix;
        System.out.println("row="+matrix.length);
        if (matrix.length==0 || matrix[0].length==0)
            return;
        System.out.println("col="+matrix[0].length);
        sum=new int[matrix.length+1][matrix[0].length+1];
        
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                sum[i+1][j+1]=sum[i][j+1]+sum[i+1][j]-sum[i][j]+matrix[i][j];
            }
        }
    }
    void print()
    {
        for (int[]r: sum) {
            System.out.println(Arrays.toString(r));
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (sum==null)
            return 0;
        return sum[row2+1][col2+1]-sum[row1][col2+1]-sum[row2+1][col1]+sum[row1][col1];
    }
    
    public static void main(String[] args)
    {
        NumMatrix m=new NumMatrix(new int[][]{{3, 0, 1, 4, 2},{5, 6, 3, 2, 1},{1, 2, 0, 1, 5},{4, 1, 0, 1, 7},{1, 0, 3, 0, 5}});
        m.print();
        System.out.println(m.sumRegion(2, 1, 4, 3)==8);
        System.out.println(m.sumRegion(1, 1, 2, 2)==11);
        System.out.println(m.sumRegion(1, 2, 2, 4)==12);
    
        m=new NumMatrix(new int[][]{{}});  // 1 row, 0 col
        m=new NumMatrix(new int[][]{{1}});           // 1 row 1 col
        m.print();
        System.out.println(m.sumRegion(0, 0, 0, 0)==1);
        
        m=new NumMatrix(new int[][]{{1},{-7}});  // 2 row, 1 col
    }
}
