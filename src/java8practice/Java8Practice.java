/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 *
 * @author Andy
 */
public class Java8Practice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Java8IO().testPathWalk("c:\\users\\Andy\\OneDrive");
        new Java8IO().readMapfile("test.txt");
        new Java8Lamda().sortPerson();
        new Java8Lamda().testMap();
        new Java8Lamda().testStreamMapReduce();
        new Java8Lamda().testFlatMapper();
        try {
            new Java8IO().splitFile("TomSawyer.txt", 4);
            new Java8Lamda().scrabbleScore();
        }
        catch (IOException e)
        {
        }
        Initialization.arrayInit();
        TestRegEx();
    }
    
    static public void TestRegEx()
    {
        String scrabble="bizzare";
        boolean bMatch = scrabble.matches("[xbizare]+");
        System.out.println(bMatch);
        System.out.println("tax".matches("[xbizare]+"));  //false as t is not matched
        System.out.println("taxation".matches(".*[ab+].*")); // match a at least once
        System.out.println("taxation".matches(".*a.*")); // match a at least once
        System.out.println("tx".matches(".*[a+].*"));
        
        Set<String> dictionary = new HashSet<>();
        try (BufferedReader reader = 
                new BufferedReader(
                new FileReader(
                new File("ospd.txt"))); ) 
        {
            String word=null;
            while ( (word = reader.readLine()) != null)
                dictionary.add(word.toLowerCase());
        }
        catch (IOException e)
        {
            
        }  
        for (String word: dictionary) {
            if (word.matches("[bizare]"))
                System.out.println("Match :"+word);
        }        
    }
}
