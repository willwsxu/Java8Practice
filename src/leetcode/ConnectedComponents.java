package leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class ConnectedComponents {

	static int[][] dir=new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
	static void bfs(char[][] grid, int i, int j, int count)
	{
		Queue<int[]> q=new ArrayDeque<>();
		q.add(new int[] {i,j});
		grid[i][j] += count; // mark it first!!
		while (!q.isEmpty()) {
			int[] loc=q.poll();
			//System.out.println(loc[0]+","+loc[1]+":"+grid[loc[0]][loc[1]]);
			for (int [] d: dir) {
				int r=loc[0]+d[0];
				int c=loc[1]+d[1];
				if (r<0 || c<0 || r>=m || c>=n) 					
					continue;
				if (grid[r][c]=='1') {
					grid[r][c] += count; // mark it when add to queue, so it won't be added again
					q.add(new int[] {r,c});
				}
			}
		}
	}
	// dfs is faster than bfs, 9 vs 13 ms on leetcode
	static void dfs(char[][] grid, int r, int c, int count)
	{
		if (r<0 || c<0 || r>=m || c>=n) 
			return;
		if (grid[r][c]!='1')
			return;
		grid[r][c] += count;
		for (int [] d: dir)
			dfs(grid, r+d[0], c+d[1], count);
	}
	static int m,n;
    static public int numIslands(char[][] grid) {
    	int count=0;
    	if (grid==null||grid.length==0)
    		return 0;
    	m=grid.length; 
    	n=grid[0].length;
        for (int i=0; i<m; i++) {
        	for (int j=0; j<n; j++) {
        		if (grid[i][j] !='1')
        			continue;
        		//fill all connected
        		dfs(grid, i, j, ++count);
        	}
        }
        //for (char [] line: grid)
        //	System.out.println(line);		
        return count;
    }

    public static void main(String[] args)
    {
    	char [][]g=new char[][] {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
    	System.out.println(numIslands(g)==3);
    	g=new char[][] {};
    	System.out.println(numIslands(g)==0);
    	g=new char[][] {{'1'}};
    	System.out.println(numIslands(g)==1);
    	g=new char[][] {{'0'}};
    	System.out.println(numIslands(g)==0);
    	
    	g=new char[][] {{'1'},{'0'},{'1'},{'0'},{'1'},{'1'}};
    	System.out.println(numIslands(g)==3);
    }
}
