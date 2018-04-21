package leetcode.bfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/*
 * On a 2x3 board, there are 5 tiles from 1 through 5, and an empty square represented by 0
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]]
 * return -1 if no solution
 */
public class Puzzle {

	class PuzzleBoard
	{
		int r,c;
		int[][]board;
		@Override
		public String toString() {
			StringBuilder sb=new StringBuilder();
	    	for (int r=0; r<board.length; r++)
	    		for (int c=0; c<board[r].length; c++)
	    			sb.append((char)(board[r][c]+'0'));
	    	return sb.toString();		
		}
	
		PuzzleBoard(int[][] b)
		{
	    	board=b;
	    	for (r=0; r<b.length; r++)
	    		for (c=0; c<b[r].length; c++)
	    			if (b[r][c]==0)
	    				return;
		}
		PuzzleBoard(PuzzleBoard b)
		{
			r=b.r;
			c=b.c;
			board=clone(b.board);
		}
		private int[][] clone(int[][]b)
		{
			int [][]clone=new int[b.length][b[0].length];
	    	for (int r=0; r<b.length; r++)
	    		for (int c=0; c<b[r].length; c++)
	    			clone[r][c]=b[r][c];
	    	return clone;
		}
		boolean won()
		{
			if ( r==0 || c!=2)
				return false;
			if (board[0][0]!=1 || board[0][1]!=2 || board[0][2]!=3 || board[1][0] !=4) {
				return false;
			}
			return true;
		}
		void swap(int nr, int nc) // new 0 position
		{
			board[r][c] ^= board[nr][nc];
			board[nr][nc] ^= board[r][c];
			board[r][c] ^= board[nr][nc];
			r=nr;
			c=nc;
		}
		void test()
		{
	    	int[][]b=new int[][] {{1,2,3},{4,0,5}};
	    	PuzzleBoard pb=new PuzzleBoard(b);
			System.out.println(pb.r+" "+pb.c);
			System.out.println(pb.toString() );
			System.out.println(pb.won());
			pb.swap(1, 2);
			System.out.println(new PuzzleBoard(pb).toString() );
			System.out.println(pb.won());
			pb.swap(1, 2);
			System.out.println(pb.toString() );
		}
	}
	int[][]dir=new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
    public int slidingPuzzle(int[][] board) {
    	Queue<PuzzleBoard> q=new ArrayDeque<>();
    	Set<String> seen = new HashSet<>();
    	q.add(new PuzzleBoard(board));
    	seen.add(q.peek().toString());
    	int ans=0;
    	while (!q.isEmpty()) {
    		int oldSize=q.size();
    		for (int i=0; i<oldSize; i++) {
    			PuzzleBoard pb=q.poll();
    			if (pb.won())
    				return ans;
    			for (int[] d: dir) {
    				int nr=pb.r+d[0];
    				int nc=pb.c+d[1];
    				if (nr<0 || nc<0 || nr==2 || nc==3)
    					continue;
    				pb.swap(nr, nc);
    				// check if this configuration is seen before
    				if (!seen.contains(pb.toString())) {
    					q.add(new PuzzleBoard(pb));
    	    			seen.add(pb.toString());
        				//System.out.println("add+" +pb.toString());
    				}
    				pb.swap(nr-d[0], nc-d[1]); // swap back
    			}
    		}
    		//System.out.println();
    		ans++;
    	}
    	return -1;
    }

    public static void main(String[] args)
    {
    	//PuzzleBoard.test();
    	int[][]b=new int[][] {{1,2,3},{4,0,5}};
    	System.out.println(new Puzzle().slidingPuzzle(b));
    	b=new int[][] {{1,2,3},{5,4,0}};
    	System.out.println(new Puzzle().slidingPuzzle(b));
    	b=new int[][] {{4,1,2},{5,0,3}};
    	System.out.println(new Puzzle().slidingPuzzle(b));
    	b=new int[][] {{3,2,4},{1,5,0}};
    	System.out.println(new Puzzle().slidingPuzzle(b));
    }
}
