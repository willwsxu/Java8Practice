/*
 * Given an integer, convert it to a roman numeral. 
 * Input is guaranteed to be within the range from 1 to 3999
 */
package leetcode;

public class Integer2Roman {
    static public String intToRoman(int num) {
        StringBuilder sb=new StringBuilder();
        if (num>=1000) {
            String[] thousands=new String[]{"","M","MM","MMM"};
            sb.append(thousands[num/1000]);
            num %=1000;
        }
        if (num>=100) {
            String[] hundreds=new String[]{"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
            sb.append(hundreds[num/100]);
            num %=100;
        }
        if (num>=10) {
            String[] tens=new String[]{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
            sb.append(tens[num/10]);
            num %=10;
        }
        if (num>0){
            String[] units=new String[]{"", "I", "II","III","IV", "V","VI","VII", "VIII", "IX"};
            sb.append(units[num]);
        }
        return sb.toString();
    }
    public static void main(String[] args)
    {
        System.out.println(intToRoman(3999));
        System.out.println(intToRoman(1001));
        System.out.println(intToRoman(5));
    }
}
