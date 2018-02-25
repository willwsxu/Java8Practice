/*
 * Annie oop test answer key
 */
package imsaooptest;

import java.util.ArrayList;

public class Quiz {
    
    static void q1()
    {
        String s="*****";
        for (int i=0; i<s.length(); i++)
        {
            System.out.println(s.substring(0, i+1));
        }
    }
    public static void main(String []arg)
    {
        q1();
        
        // Q2
        String s="UPPER CASE";
        s.toLowerCase(); // immutable object
        System.out.println(s); // same
        
        // Q3
        ArrayList<Item> item100=new ArrayList<>(100);
        int count=0;
        for (Item it: item100) {
            if (it.getPrice()>25)
                count++;
        }
        System.out.println(count);
        
        
        for (Item it: item100) {
            if (it.getPrice()<=30 && it.getQuantity()>=50)
                System.out.println(it.getDescription());
        }
        
        // Q4
        // substring startindex 0 to 3, end index 2 to 5
        // [IM], [IMS], [IMSA]
        // [M], [MS], [MSA]
        // [],  [S],  [SA]
        // [A]
        String imsa="IMSA";
        System.out.println("["+imsa.substring(2,2)+"]");
        //System.out.println(imsa.substring(2,5)); exception
        //System.out.println(imsa.substring(3,2)); exception
        
        // Q5
        // a=true, b=false or a=false, b=true
        
        // Q6, 2^5-1=31
    }
}

class Item
{
    private String desc;
    private int quantity;
    private int price;
    
    public String getDescription()
    {
        return desc;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public int getPrice()
    {
        return price;
    }
}
