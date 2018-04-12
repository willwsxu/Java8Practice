package leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class ConnectedComponents {

    static public int numIslands(char[][] grid) {
    	int count=0;
    	if (grid==null||grid.length==0)
    		return 0;
    	int m=grid.length; 
    	int n=grid[0].length;
    	int[][] dir=new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
        for (int i=0; i<m; i++) {
        	for (int j=0; j<n; j++) {
        		if (grid[i][j] !='1')
        			continue;
        		//fill all connected
        		count++;
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
                //for (char [] line: grid)
                //	System.out.println(line);
        	}
        }
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
    	System.out.println(numIslands(g));
    }
}
