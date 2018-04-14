
package leetcode.backtracking;

import static java.lang.System.out;
import java.util.Arrays;

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
        // calculate initial mask for each row and col
        computerRowColMasks(row_mask, col_mask);
        // initial mask of each square
        computerSquMasks(squ_mask);
    }
    boolean computerRowColMasks(int[] row_mask, int[] col_mask)
    {
        for (int i=0; i<SUDOKU_SIZE; i++) {
            // row mask
            if (!computerRowMasks(i, row_mask))
                return false;
            // col mask
            if (!computerColMasks(i, col_mask))
                return false;
        }       
        return true;
    }
    boolean computerRowMasks(int r, int[] row_mask)
    {
        for (int j=0; j<SUDOKU_SIZE; j++) {
            if (board[r][j]==0)
                continue;
            int bitmask=1<<(board[r][j]-1);
            if ( (bitmask&row_mask[r]) > 0 )  {// this digit already exist
                //out.println("computerRowMasks: row "+(r+1)+ " col "+(j+1)+" bad val "+board[r][j]);
                return false;
            }
            else
                row_mask[r] |= bitmask;
        }
        return true;
    }
    boolean computerColMasks(int c, int[] col_mask)
    {
        for (int r=0; r<SUDOKU_SIZE; r++) {
            if (board[r][c]==0)
                continue;
            int bitmask=1<<(board[r][c]-1);
            if ( (bitmask&col_mask[c]) > 0 ) { // this digit already exist
                //out.println("computerColMasks: row "+(r+1)+ " col "+(c+1)+" bad val "+board[r][c]);
                return false;
            }
            else
                col_mask[c] |= bitmask;
        }
        return true;   
    }
    boolean computerSquMasks(int[] squ_mask)
    {
        for (int s=0; s<SUDOKU_SIZE; s++) {
            for (int r=0; r<SUDOKU_SIZE/3; r++) {
                for (int c=0; c<SUDOKU_SIZE/3; c++) {
                    int r1=r+squareRow[s];
                    int c1=c+squareCol[s];
                    if (board[r1][c1]==0)
                        continue;
                    int bitmask=1<<(board[r1][c1]-1);
                    if ( (bitmask&squ_mask[s]) > 0 ) { // this digit already exist
                        //out.println("computerSquMasks: row "+(r1+1)+ " col "+(c1+1)+" bad val "+board[r1][c1]);
                        return false;
                    }
                    else
                        squ_mask[s] |= bitmask;
                }
            }
        }
        return true;
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
    boolean isCellValid(int val, int r, int c)
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
        
    void printMasks(int r, int c)
    {
        out.println("Row mask "+r+":"+Integer.toBinaryString(row_mask[r]));
        out.println("Col mask "+c+":"+Integer.toBinaryString(col_mask[c]));
        int s=getSquare(r,c);
        out.println("Squ mask "+s+":"+Integer.toBinaryString(squ_mask[c]));
    }
    void setMasks(int val, int r, int c)
    {
        int bitmask=1<<(val-1);
        row_mask[r] |= bitmask;
        col_mask[c] |= bitmask;
        int s=getSquare(r,c);
        squ_mask[s] |= bitmask;
    }
    void resetMasks(int val, int r, int c)
    {
        int bitmask=1<<(val-1);
        int fullMask=(1<<SUDOKU_SIZE)-1;  // all 1s, 111111111
        bitmask ^= fullMask;            // flip bit for val;
        //out.println("val "+val+" mask="+Integer.toBinaryString(bitmask)+" full="+Integer.toBinaryString(fullMask));
        int s=getSquare(r,c);
        //out.println("mask before: "+Integer.toBinaryString(row_mask[r])+", "+Integer.toBinaryString(col_mask[c])+", "+Integer.toBinaryString(squ_mask[s]));
        row_mask[r] &= bitmask;         // reset bit for val
        col_mask[c] &= bitmask;
        squ_mask[s] &= bitmask;
        //out.println("mask after : "+Integer.toBinaryString(row_mask[r])+", "+Integer.toBinaryString(col_mask[c])+", "+Integer.toBinaryString(squ_mask[s]));
    }
    boolean isBoardValid()
    {
        int []r_mask=new int[SUDOKU_SIZE];
        int []c_mask=new int[SUDOKU_SIZE];
        int []s_mask=new int[SUDOKU_SIZE];
        if ( !computerSquMasks(s_mask))
            return false;
        return computerRowColMasks(r_mask, c_mask);
    }
    
    public boolean isValidSudoku(char[][] board) {  //leetcode
        for (int i=0; i<SUDOKU_SIZE; i++) {
            for (int j=0; j<SUDOKU_SIZE; j++) {
                if ( board[i][j]=='.')
                    this.board[i][j]=0;
                else
                    this.board[i][j]=board[i][j]-'0';
            }
        }
        int []r_mask=new int[SUDOKU_SIZE];
        int []c_mask=new int[SUDOKU_SIZE];
        int []s_mask=new int[SUDOKU_SIZE];
        if ( !computerSquMasks(s_mask))
            return false;
        return computerRowColMasks(r_mask, c_mask);
        
    }
    boolean updateBoardMasks()
    {
        Arrays.fill(row_mask, 0);
        Arrays.fill(col_mask, 0);
        Arrays.fill(squ_mask, 0);
        if ( !computerSquMasks(squ_mask))
            return false;
        return computerRowColMasks(row_mask, col_mask);
    }
    boolean backtracking(int pos)
    {
        int blank=findNextOpenCell(pos);
        if (blank<0)
            return true;
        int r=blank/SUDOKU_SIZE;
        int c=blank%SUDOKU_SIZE;
        for (int i=1; i<=9; i++) {
            board[r][c]=i;
            if ( !updateBoardMasks() ) {  // invalid choice
                //out.println("Invalid backtracking: r="+r+" c="+c);
                //print();
                board[r][c]=0; // still want to know why this line is needed
                continue;
            }
            if ( backtracking(blank+1))
                return true;
            board[r][c]=0;
        }
        //out.println("r="+r+" c="+c+" fail");  can fail if previous choice is wrong
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
                out.print(" | ");
            }
            out.println();
        }
    }
    static void unittest()
    {
        String[] puzzle=new String[]{
            "...2.481.",
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
        out.println(sudo.isCellValid(2, 0, 3)==false);
        out.println(sudo.isCellValid(2, 0, 2)==false);
        out.println(sudo.isCellValid(3, 0, 2)==false);
        out.println(sudo.isCellValid(5, 0, 2)==false);
        out.println(sudo.isCellValid(6, 0, 2)==true);
        out.println(sudo.isBoardValid()==true);
        sudo.setMasks(6, 0, 2);
        sudo.board[0][2]=6;
        out.println(sudo.isBoardValid()==true);
        out.println(sudo.isCellValid(6, 0, 2)==false);
        sudo.resetMasks(6, 0, 2);
        sudo.board[0][2]=0;
        out.println(sudo.isBoardValid()==true);
        out.println(sudo.isCellValid(6, 0, 2)==true);
        sudo.setMasks(6, 0, 2);
        sudo.setMasks(3, 0, 4);
        sudo.board[0][2]=6;
        sudo.board[0][4]=3;
        sudo.setMasks(6, 6, 6);
        sudo.setMasks(5, 7, 0);
        sudo.board[6][6]=6;
        sudo.board[7][0]=5;
        out.println(sudo.isBoardValid()==true);
        out.println(sudo.isCellValid(6, 8, 8)==false);
        out.println(sudo.isCellValid(5, 8, 1)==false);
        out.println(sudo.isCellValid(5, 7, 5)==false);
        
        sudo.resetMasks(6, 0, 2);
        sudo.resetMasks(3, 0, 4);
        sudo.resetMasks(6, 6, 6);
        sudo.resetMasks(5, 7, 0);
        sudo.board[0][2]=0;
        sudo.board[0][4]=0;
        sudo.board[6][6]=0;
        sudo.board[7][0]=0;
        sudo.print();
        if (!sudo.backtracking(0))
            out.println("no solution");
        sudo.print();
        
    }
    static void test()
    {        
        String[] puzzle=new String[]{
            "2957..86.",
            ".31865.2.",
            "8.6......",
            "..7.5...6",
            "...387...",
            "5...167..",
            "...5..1.9",
            ".2.6..35.",
            ".54..8672"
        };
        Sudoku sudo=new Sudoku(puzzle);
        if (!sudo.backtracking(0))
            out.println("no solution");
        sudo.print();
        
        puzzle=new String[]{
            "3.65.84..",
            "52.......",
            ".87....31",
            "..3.1..8.",
            "9..863..5",
            ".5..9.6..",
            "13....25.",
            ".......74",
            "..52.63.."
        };
        out.println();
        sudo=new Sudoku(puzzle);
        if (!sudo.backtracking(0))
            out.println("no solution");
        sudo.print();
    }
    public static void main(String[] args)
    {
        test();
    }
}
