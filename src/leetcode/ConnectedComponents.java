package leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class ConnectedComponents {

    static public int numIslands(char[][] grid) {
    	int id=0;
    	if (grid==null||grid.length==0)
    		return 0;
    	int m=grid.length; 
    	int n=grid[0].length;
    	int[][] dir=new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
        for (int i=0; i<grid.length; i++) {
        	for (int j=0; j<grid[0].length; j++) {
        		if (grid[i][j] !='1')
        			continue;
        		//fill all connected
        		id++;
        		Queue<int[]> q=new ArrayDeque<>();
        		q.add(new int[] {i,j});
        		while (!q.isEmpty()) {
        			int[] loc=q.poll();
        			grid[loc[0]][loc[1]] += id;
        			for (int [] d: dir) {
        				int r=loc[0]+d[0];
        				int c=loc[1]+d[1];
        				if (r<0 || c<0 || r>=m || c>=n) 					
        					continue;
        				if (grid[r][c]=='1')
        					q.add(new int[] {r,c});
        			}
        		}
        	}
        }
        return id;
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
    }
}
