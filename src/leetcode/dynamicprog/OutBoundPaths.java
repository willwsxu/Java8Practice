// There is an m by n grid with a ball.Given the start coordinate (i, j) of the ball, you can move the ball to adjacent cell or 
// cross the grid boundary in four directions(up, down, left, right). However, you can at most move N times.Find out the number 
// of paths to move the ball out of grid boundary.The answer may be very large, return it after mod 109 + 7.
package leetcode.dynamicprog;

public class OutBoundPaths {
    public int FindPaths(int m, int n, int N, int i, int j, String dir)
    {
        if (N < 0)
            return 0;
        if (i < 0 || i >= m || j < 0 || j >= n)
        {
            System.out.println(dir +" N="+N+" i="+i+" j="+j);
            return 1;
        }
        if (N == 0)
            return 0;
        int count = FindPaths(m, n, N - 1, i-1, j, "up"); // up
        count  +=   FindPaths(m, n, N - 1, i+1, j, "down"); // down
        count  +=   FindPaths(m, n, N - 1, i, j-1, "left"); // left
        count  +=   FindPaths(m, n, N - 1, i, j+1, "right"); // right
        return count;
    }
    public static void main(String[] args)
    {
        System.out.println(new OutBoundPaths().FindPaths(1, 3, 3, 0, 1,"start") );
    }
}
