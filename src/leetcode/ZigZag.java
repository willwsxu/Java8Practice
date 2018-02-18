/*
 * print string into n rows, 
 */
package leetcode;

public class ZigZag {
    static public String convert_x(String s, int numRows) {
        int toprow=numRows/2;
        int bottomrow=numRows-toprow-1;
        int step=numRows+1; // step for rows above or below
        StringBuilder sb=new StringBuilder();
        for (int i=0; i<toprow; i++) {
            for (int j=i; j<s.length(); j+=step)
                sb.append(s.charAt(j));
        }
        // middle row
        for (int i=toprow; i<s.length(); i += step) {
            sb.append(s.charAt(i));
            if (i+1+bottomrow<s.length()) // must check string length
                sb.append(s.charAt(i+1+bottomrow));
        }
        for (int i=0; i<bottomrow; i++) {
            for (int j=i+1+toprow; j<s.length(); j+=step)
                sb.append(s.charAt(j));
        }
        return sb.toString();
    }
    static public String convert(String s, int numRows) {
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
    public static void main(String[] args)
    {
        System.out.println(convert("PAYPALISHIRING", 3).equals("PAHNAPLSIIGYIR"));
        System.out.println(convert("PAYPALISHIRING", 1).equals("PAYPALISHIRING"));
        System.out.println(convert("ABC", 2).equals("ACB"));
    }
}
