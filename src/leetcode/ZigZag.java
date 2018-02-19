/*
 * print string into n rows, 
 */
package leetcode;

public class ZigZag {
        // pattern repeats [1, 2n-1), 1 to n go down same column
        // n-1 to 2n-2 go up like staircase
        if (numRows<=1)
            return s;
        int period=2*numRows-2;
        int cycles=(s.length()+period-1)/period; // ceiling
        StringBuilder sb[]=new StringBuilder[numRows];
        for (int i=0; i<numRows; i++)
            sb[i]=new StringBuilder();
        for (int i=0; i<cycles; i++) {
            int from = i*period;
            int end=s.length()-from;
            end = end>numRows?numRows:end;
            for (int down=0; down<end; down++) {
                sb[down].append(s.charAt(from+down));
            }
            if (s.length()-from<=numRows)
                break;
            from += numRows;
            end = s.length()-from;            
            end = end>numRows-2?numRows-2:end;
            for (int up=0; up<end; up++) {
                sb[numRows-2-up].append(s.charAt(from+up));
            }
        }
        for (int i=1; i<numRows; i++)
            sb[0].append(sb[i].toString());
        return sb[0].toString();
    }
    static public String convert(String s, int numRows) {
        if (numRows<=1)
            return s;
        StringBuilder sb[]=new StringBuilder[numRows];
        for (int i=0; i<numRows; i++)
            sb[i]=new StringBuilder();
        int row=0;
        boolean down=true;
        for (int i=0; i<s.length(); i++)
        {
            sb[row].append(s.charAt(i));
            if (down) {
                if (row==numRows-1) { // last row, flip to go up
                    row--;
                    down=false;
                }
                else
                    row++;
            } else {
                if (row==0) { // first row
                    row++;
                    down=true;
                }
                else
                    row--;
            }
        }
        for (int i=1; i<numRows; i++)
            sb[0].append(sb[i].toString());
        return sb[0].toString();
    }
    public static void main(String[] args)
    {
        System.out.println(convert("PAYPALISHIRING", 3).equals("PAHNAPLSIIGYIR"));
        System.out.println(convert("PAYPALISHIRING", 1).equals("PAYPALISHIRING"));
        System.out.println(convert("ABC", 2).equals("ACB"));
    }
}
