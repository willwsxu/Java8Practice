package leetcode.backtracking;

import java.util.Arrays;

public class TicTacToe {
	
	boolean win(String[] board, int r, int c, int dR, int dC) // check 3 in a direction
	{
		char side=board[r].charAt(c);
		if (side==' ')
			return false;
		r+=dR;
		c+=dC;
		if (board[r].charAt(c)!=side)
			return false;
		return board[r+dR].charAt(c+dC)==side;
	}

    public boolean validTicTacToe(String[] board) {
    	int x=0;  // count X and O
    	int o=0;
        for (int i=0; i<board.length; i++) {
        	for (int j=0; j<board[0].length(); j++) {
        		switch (board[i].charAt(j)) {
        		case 'X':	x++; break;
        		case 'O':	o++; break;
        		}
        	}
        }
    	if (o>x || o<x-1)
    		return false;
    	// 8 winning starting position and direction
        int[][] winPositions=new int[][] {{0,0,1,0},{0,0,1,1},{0,0,0,1},{0,1,1,0},{0,2,1,0},{0,2,1,-1},{1,0,0,1},{2,0,0,1}};
        boolean xwin=false, owin=false;
        for (int[] pos: winPositions) { 
        	if (win(board, pos[0],pos[1],pos[2],pos[3])) {
        		if (board[pos[0]].charAt(pos[1])=='X')
        			xwin=true;
        		else
        			owin=true;
                //System.out.println(board[pos[0]].charAt(pos[1])+" win at "+Arrays.toString(pos));
        	}
        }
        if (o==x && xwin)
        	return false;
        if (o==x-1 && owin)
        	return false;
        return true;
    }
    public static void main(String[] args)
    {
    	System.out.println(new TicTacToe().validTicTacToe(new String[] {"O  ", "   ", "   "})==false);
    	System.out.println(new TicTacToe().validTicTacToe(new String[] {"XOX", " X ", "   "})==false);
    	System.out.println(new TicTacToe().validTicTacToe(new String[] {"XXX", "   ", "OOO"})==false);
    	System.out.println(new TicTacToe().validTicTacToe(new String[] {"XOX", "O O", "XOX"}));
    	System.out.println(new TicTacToe().validTicTacToe(new String[] {"XO ", "   ", "X  "}));
    }
}
