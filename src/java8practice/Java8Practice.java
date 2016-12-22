/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8practice;

import java.io.IOException;

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
        try {
        new Java8IO().splitFile("TomSawyer.txt", 4);
        }
        catch (IOException e)
        {
        }
        new Java8Lamda().testFlatMapper();
    }
    
}
