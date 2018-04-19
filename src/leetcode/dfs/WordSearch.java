package leetcode.dfs;

import java.util.Arrays;

// The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those 
// horizontally or vertically neighboring. The same letter cell may not be used more than once
// Notes: word from board must be same sequence as target word
// idea: dfs the word from each cell
public class WordSearch {

	void print(char[][] board)
	{
		for (char[] b: board)
			System.out.println(Arrays.toString(b));
	}
	static int dir[][]= {{-1,0},{1,0},{0,-1},{0,1}};
	//boolean[][] visiting;
	boolean dfs(char[][] board, int r, int c, String word, int idx) {
		if (idx==word.length())
			return true;
		if (r<0 || c<0 || r==board.length || c==board[0].length)
			return false;
		if (board[r][c] !=word.charAt(idx))
			return false;
		//visiting[r][c]=true; // mark true so it is not used again in the same search
		board[r][c] ^= 0xFF;  // trick to avoid extra memory of visiting, but see no performance gain
		for (int[]d: dir) {
			if (dfs(board, r+d[0], c+d[1], word, idx+1)) { // next move in 4 directions
				board[r][c] ^= 0xFF;
				return true;
			}
		}
		board[r][c] ^= 0xFF;
		//visiting[r][c]=false;
		return false;
	}
    public boolean exist(char[][] board, String word) {
    	if (board.length==0) // word.toCharArray is bad for performance
    		return false;
    	//visiting=new boolean[board.length][board[0].length];
    	for (int i=0; i<board.length; i++) {
    		for (int j=0; j<board[0].length; j++) {
    	        if ( dfs(board, i, j, word, 0) )  //check work from each cell
    	        	return true;
    		}
    	}
    	return false;
    }
    public static void main(String[] args)
    {
    	char [][]board =
    		{
    			  {'A','B','C','E'},
    			  {'S','F','C','S'},
    			  {'A','D','E','E'}
    		};
    	System.out.println(new WordSearch().exist(board, "ABCCED"));
    	System.out.println(new WordSearch().exist(board, "SEE"));
    	System.out.println(new WordSearch().exist(board, "ABCB")==false);
    	System.out.println(new WordSearch().exist(board, "CCESEE"));
    	System.out.println(new WordSearch().exist(board, "CCSEEE")==false);
    }
}
