/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8practice;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.stream.*;

/**
 *
 * @author Andy
 */
public class Java8IO {
    
    // key=value
    // ;comment
    // #comment
    public void testBufferRead(String file)
    {
        try (BufferedReader reader = 
                new BufferedReader(
                new FileReader(
                new File(file))); ) 
        {
            Stream<String> stream = reader.lines();
            stream.filter(line->!line.startsWith(";"))
                   .findFirst()
                   .ifPresent(System.out::println);
        }
        catch (IOException e)
        {
            
        }        
    }
        
    public void testPathRead(String file)
    {
        Path path = Paths.get(file);
        try (Stream<String> stream = Files.lines(path) ) 
        {
            stream.filter(line->!line.startsWith(";"))
                   .findFirst()
                   .ifPresent(System.out::println);
        }
        catch (IOException e)
        {
            
        }        
    }
    public void testPathWalk(String p)
    {
        Path path = Paths.get(p);
        try (Stream<Path> stream = Files.walk(path, 2) ) 
        {
            stream.filter(p2->p2.toFile().isDirectory())
                   .forEach( System.out::println);
        }
        catch (IOException e)
        {
            
        }           
    }
    public void readMapfile(String file)
    {  
        Path path = Paths.get(file);
        try (Stream<String> stream = Files.lines(path) ) 
        {
            stream.filter(line->!line.startsWith(";"))
                   .forEach(line->{
                       String[] elems =line.split("=");
                       for (String str : elems)
                           System.out.println(str);
                   });
        }
        catch (IOException e)
        {
            
        }      
    }
}
