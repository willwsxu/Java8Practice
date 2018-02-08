/*
 * Implement atoi to convert a string to an integer
 */
package leetcode;

public class MyAtoi {
    static public int myAtoi(String str) {
        int val=0;
        int i=0;
        int sign=1;
        boolean signonce=false;
        for (; i<str.length(); i++) {
            if (Character.isWhitespace(str.charAt(i)))
                continue;
            if (str.charAt(i)=='+') {
                if (signonce)
                    return 0;
                signonce=true;
                continue;
            }
            if (str.charAt(i)=='-') {
                if (signonce)
                    return 0;
                signonce=true;
                sign=-1;
                continue;
            }
            break;
        }
        int len=Integer.min(10+i, str.length());  // read up to 10 digits
        int overflowcheck=Integer.MAX_VALUE/10;
        for (; i<len; i++) {
            if (!Character.isDigit(str.charAt(i)))
                break;
            if (val>overflowcheck) {
                //System.out.println("overflowcheck1 "+val);
                return sign>0?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }
            if (val==overflowcheck) {
                //System.out.println("overflowcheck2 "+val);
                if (sign>0 && str.charAt(i)>'7')
                    return Integer.MAX_VALUE;
                if (sign<0 && str.charAt(i)>='8')
                    return Integer.MIN_VALUE;
            }
            val *=10;
            val += str.charAt(i)-'0';
        }
        val *= sign;
        return val;
    }
    public static void main(String[] args)
    {
        System.out.println(myAtoi("- 123 3x")==-123);
        System.out.println(myAtoi(""));
        System.out.println(myAtoi("    "));
        System.out.println(myAtoi("+-2")==0);
        System.out.println(myAtoi(" 2147483648")==Integer.MAX_VALUE);
        System.out.println(myAtoi("-2147483648")==Integer.MIN_VALUE);
        System.out.println(myAtoi("-2147483647"));
        System.out.println(myAtoi("-2147483657")==Integer.MIN_VALUE);
        System.out.println(myAtoi(" 2147483657")==Integer.MAX_VALUE);
    }
}
