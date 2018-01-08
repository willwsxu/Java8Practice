/*
 * Annie oop test answer key
 */
package ooptest;

import java.util.ArrayList;
import java.util.HashMap;

public class Cabinet extends ArrayList<Shelf>{
    
    public Cabinet(int s) {  //Q2
        for (int i=0; i<s;i++)
            add(new Shelf());
    }
    public static void main(String []arg)
    {
        Cabinet cabinet;  // Q1
        cabinet =new Cabinet(4);
        Shelf shelf = cabinet.get(3);  //Q3
        Book book=shelf.get(24);
        
        //Q4
        int product = (3%2) * (5%4) * (3%7);  // 3
        
        // Q5
        // 97531
        // 7531
        // 531
        // 31
        // 1
        
        // Q6: all of them
        System.out.println(12*100+34);
        System.out.println("12"+34);
        System.out.println(12+"34");
        System.out.println("123"+4);
        
        // Q7: 4 items, A B C C
        ArrayList<String> list=new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        for (String s: list){
            String t=list.get(list.size()-1);
            list.set(list.size()-1, s);
            s=t;
        }
        System.out.println(list);
        
        //Q8
        // 7, 4, 3, 0, 0
        
        // Q9
        Book[] books=new Book[50];
        HashMap<String, Book> bookHash=new HashMap<>();
        for (Book b:books)
            bookHash.put(b.getTitle(), b);
        
        Book b=bookHash.get("title");
        System.out.println("Book title "+b.getTitle()+" Author "+b.getAuthor());
    }
}

class Shelf extends ArrayList<Book>
{    
    {
    for (int i=0; i<50;i++)
        add(new Book());
    }
}

class Book
{
    String t;
    String a;
    String getTitle()
    {
        return t;
    }
    String getAuthor()
    {
        return a;
    }
}