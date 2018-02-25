/*
 * Annie oop test answer key
 */
package imsaooptest;

public class Quiz2 {
    
    public static void main(String []arg)
    {
        // find even numbers
        int count=0; 
        for (int i=1; i<=100; i++) {
            if (i%2==0) {
                count++;
                System.out.println(i);
            }
        }
        System.out.println("total even #"+count);
        
        // Q3
        // The value of Z -> -256
        
        // Q4
        Circle c=new Circle();
        
        // Q5
        MyClass m= new MyClass();
        if (m.getValue()<-5)
            m.setValue(0);
        else if (m.getValue()>10)
            m.setValue(10);
        
        // Q6
        // 0 -1 -2
        // -1 0 -1
        // 0 -1 0
        
        // Q7
        // count how many time b occurs in a
    }
}

// Q1
class StockItem
{
    private String description;
    private int idNum;
    private double price;
    private int numOnShelf;
    
    public StockItem(String description, int idNum, double price, int numOnShelf)
    {
        this.description=description;
        this.idNum = idNum;
        this.price = price;
        this.numOnShelf = numOnShelf;
    }
        
    public String getDescription()
    {
        return description;
    }
    
    public void setPrice(double p)
    {
        price=p;
    }
}

class Circle
{
    
}

class MyClass
{
    private int number;
    
    public int getValue()
    {
        return number;
    }
    public void setValue(int k)
    {
        number=k;
    }
}