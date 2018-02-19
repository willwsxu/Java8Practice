/*
 * Given an integer, convert it to a roman numeral. 
 * Input is guaranteed to be within the range from 1 to 3999
 */
package leetcode;

public class Integer2Roman {
    static public String intToRoman(int num) {
        StringBuilder sb=new StringBuilder();
        if (num>=1000) {
            //String[] thousands=new String[]{"","M","MM","MMM"};
            //sb.append(thousands[num/1000]);
            switch(num/1000) {
                case 1:     sb.append("M");     break;
                case 2:     sb.append("MM");     break;
                case 3:     sb.append("MMM");     break;
            }
            num %=1000;
        }
        if (num>=100) {
            //String[] hundreds=new String[]{"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
            //sb.append(hundreds[num/100]);
            switch(num/100) {
                case 1:     sb.append("C");     break;
                case 2:     sb.append("CC");     break;
                case 3:     sb.append("CCC");     break;
                case 4:     sb.append("CD");     break;
                case 5:     sb.append("D");     break;
                case 6:     sb.append("DC");     break;
                case 7:     sb.append("DCC");     break;
                case 8:     sb.append("DCCC");     break;
                case 9:     sb.append("CM");     break;
            }
            num %=100;
        }
        if (num>=10) {
            //String[] tens=new String[]{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
            //sb.append(tens[num/10]);
            switch(num/10) {
                case 1:     sb.append("X");     break;
                case 2:     sb.append("XX");     break;
                case 3:     sb.append("XXX");     break;
                case 4:     sb.append("XL");     break;
                case 5:     sb.append("L");     break;
                case 6:     sb.append("LX");     break;
                case 7:     sb.append("LXX");     break;
                case 8:     sb.append("LXXX");     break;
                case 9:     sb.append("XC");     break;
            }
            num %=10;
        }
        if (num>0){
            //String[] units=new String[]{"", "I", "II","III","IV", "V","VI","VII", "VIII", "IX"};
            //sb.append(units[num]);
            switch(num) {
                case 1:     sb.append("I");     break;
                case 2:     sb.append("II");     break;
                case 3:     sb.append("III");     break;
                case 4:     sb.append("IV");     break;
                case 5:     sb.append("V");     break;
                case 6:     sb.append("VI");     break;
                case 7:     sb.append("VII");     break;
                case 8:     sb.append("VIII");     break;
                case 9:     sb.append("IX");     break;
            }
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
