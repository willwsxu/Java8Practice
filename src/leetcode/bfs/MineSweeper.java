package leetcode.bfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

// 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed
// blank square that has no adjacent (8 directions) mines, digit ('1' to '8') represents how many mines 
// are adjacent to this revealed square, and finally 'X' represents a revealed mine
public class MineSweeper {
    static public char[][] updateBoard(char[][] board, int[] click) {
    	int m=board.length;
    	int n=board[0].length;
    	Queue<int[]> q=new ArrayDeque<>();
        q.add(click);
        int[][] dir=new int[][] {{-1, -1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        while (!q.isEmpty()) {
        	int[] cur=q.poll();
        	switch(board[cur[0]][cur[1]]) {
        	case 'M':
        		board[cur[0]][cur[1]]='X';
        		break;
        	case 'E':
        		int mines=0;
        		for (int d[]: dir) {
        			int i=cur[0]+d[0];
        			int j=cur[1]+d[1];
        			if (i<0 || j<0 || i>=m || j>=n)
        				continue;
        			if (board[i][j]=='M' || board[i][j]=='X')
        				mines++;
        		}
        		if (mines==0) {
        			board[cur[0]][cur[1]]='B';
            		for (int d[]: dir) {
            			int i=cur[0]+d[0];
            			int j=cur[1]+d[1];
            			if (i<0 || j<0 || i>=m || j>=n)
            				continue;
            			q.add(new int[] {i,j});
            		}
        		} else
        			board[cur[0]][cur[1]]=(char)('0'+mines);
        	}
        }
        return board;
    }
    static void print(char [][]b)
    {
    	for(char [] r: b)
    		System.out.println(Arrays.toString(r));
    	System.out.println();
    }

    public static void main(String[] args)
    {
    	char[][]b=new char[][]{{'E', 'E', 'E', 'E', 'E'},
    		 {'E', 'E', 'M', 'E', 'E'},
    		 {'E', 'E', 'E', 'E', 'E'},
    		 {'E', 'E', 'E', 'E', 'E'}};
    	updateBoard(b, new int[] {3,0});
    	print(b);
    	    //{{'B', '1', 'E', '1', 'B'},
    		// {'B', '1', 'M', '1', 'B'},
    		// {'B', '1', '1', '1', 'B'},
    		// {'B', 'B', 'B', 'B', 'B'}};
    	updateBoard(b, new int[] {1,2});
    	print(b);
    }
}
