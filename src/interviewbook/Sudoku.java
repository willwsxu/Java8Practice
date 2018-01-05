
package interviewbook;

import static java.lang.System.out;

public class Sudoku {
    // mask for each row, col and square
    // each bit represent if a number exsit
    static final int SUDOKU_SIZE=9;
    int row_mask[]=new int[SUDOKU_SIZE];
    int col_mask[]=new int[SUDOKU_SIZE];
    int squ_mask[]=new int[SUDOKU_SIZE];
    int board[][]=new int[SUDOKU_SIZE][SUDOKU_SIZE];
    // top left corner of each square
    int squareRow[]=new int[]{0, 0, 0, 3, 3, 3, 6, 6, 6};
    int squareCol[]=new int[]{0, 3, 6, 0, 3, 6, 0, 3, 6};
    Sudoku(String[] b)
    { // a string for each row, use . for blank cell
        for (int i=0; i<b.length; i++) {
            for (int j=0; j<b[i].length(); j++) {
                if (b[i].charAt(j)<='9' &&b[i].charAt(j)>='1')
                    board[i][j]=b[i].charAt(j)-'0';
            }
        }
        // calculate initial mask
        for (int i=0; i<SUDOKU_SIZE; i++) {
            // row mask
            for (int j=0; j<SUDOKU_SIZE; j++) {
                if (board[i][j]==0)
                    continue;
                int bitmask=1<<(board[i][j]-1);
                if ( (bitmask&row_mask[i]) > 0 ) // this digit already exist
                    out.println("row "+(i+1)+ " col "+(j+1)+" bad val "+board[i][j]);
                else
                    row_mask[i] |= bitmask;
            }
            // col mask
            for (int r=0; r<SUDOKU_SIZE; r++) {
                if (board[r][i]==0)
                    continue;
                int bitmask=1<<(board[r][i]-1);
                if ( (bitmask&col_mask[i]) > 0 ) // this digit already exist
                    out.println("row "+(r+1)+ " col "+(i+1)+" bad val "+board[r][i]);
                else
                    col_mask[i] |= bitmask;
            }
        }
        // initial mask of each square
        for (int s=0; s<SUDOKU_SIZE; s++) {
            for (int r=0; r<SUDOKU_SIZE/3; r++) {
                for (int c=0; c<SUDOKU_SIZE/3; c++) {
                    int r1=r+squareRow[s];
                    int c1=c+squareCol[s];
                    if (board[r1][c1]==0)
                        continue;
                    int bitmask=1<<(board[r1][c1]-1);
                    if ( (bitmask&squ_mask[s]) > 0 ) // this digit already exist
                        out.println("row "+(r1+1)+ " col "+(c1+1)+" bad val "+board[r1][c1]);
                    else
                        squ_mask[s] |= bitmask;
                }
            }
        }
    }
    // convert (r,c) into single value pos= r*9+c;
    static final int CELLS=SUDOKU_SIZE*SUDOKU_SIZE;
    int findNextOpenCell(int start)
    {
        for (int p=start; p<CELLS; p++) {
            int r=p/SUDOKU_SIZE;
            int c=p%SUDOKU_SIZE;
            if (board[r][c]==0)
                return p;
        }
        return -1;  // no more
    }
    // from (r,c) of a cell, find the square index
    int getSquare(int r, int c)
    {
        int s=r/3; // starting value of big row of squares
        return s+c/3;
    }
    boolean isValid(int val, int r, int c)
    {
        int bitmask=1<<(val-1);
        if ( (row_mask[r]&bitmask) > 0)
            return false;
        if ( (col_mask[c]&bitmask) > 0)
            return false;
        int s=getSquare(r,c);
        if ( (squ_mask[s]&bitmask) > 0)
            return false;
        return true;
    }
    boolean backtracking(int pos)
    {
        int blank=findNextOpenCell(pos);
        if (blank<0)
            return true;
        int r=blank/SUDOKU_SIZE;
        int c=blank%SUDOKU_SIZE;
        for (int i=1; i<=9; i++) {
            if ( !isValid(i, r,c) )
                continue;
            board[r][c]=i;
            if ( backtracking(blank+1))
                return true;
            board[r][c]=0;
        }
        return false;
    }
    void print()
    {
        for (int i=0; i<SUDOKU_SIZE; i++) {
            for (int j=0; j<SUDOKU_SIZE; j++) {
                if( board[i][j]>0)
                    out.print(board[i][j]);
                else
                    out.print(" ");
                out.print(" ");
            }
            out.println();
        }
    }
    public static void main(String[] args)
    {
        String[] puzzle=new String[]{
            "...2.4.81.",
            ".4...8263",
            "3..16...4",
            "1...4.58.",
            "63582...7",
            "2..59.1..",
            "91.7...4.",
            "...68.7.1",
            "8..4.3.5."
        };
        Sudoku sudo=new Sudoku(puzzle);
        sudo.print();
        if (!sudo.backtracking(0))
            out.println("no solution");
        sudo.print();
    }
}
